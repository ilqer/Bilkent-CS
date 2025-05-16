//
// Created by ilker yiğitel on 15.05.2025.
//

#include "CyberAttackContainment.h"
#include <iostream>
#include <fstream>

CyberAttackContainment::CyberAttackContainment(const string inputFile) {
    ifstream file;
    file.open(inputFile);

    if(!file.is_open())
    {
        std::cout << "File could not be opened." << std::endl;
        exit(1);
    }
    
    file >> numNodes;


    string name;
    string stateString;
    int state;
    nodes = new Block[numNodes];
    malwareTime = new int[numNodes];
    dispatcherTime = new double[numNodes];
    for (int i = 0; i < numNodes; i++) {
        malwareTime[i] = -1;
        dispatcherTime[i] = -1;
    }
    connectionsMatrix = new double*[numNodes];
    for(int i = 0; i < numNodes; i++) {
        connectionsMatrix[i] = new double[numNodes];
        for(int j = 0; j < numNodes; j++) {
            connectionsMatrix[i][j] = -1;
        }
    }
    for (int i = 0; i < numNodes; i++) {
        file >> name;
        file >> stateString;
        if (stateString == "normal") {
            state = 0;
        } else if (stateString == "dispatcher") {
            state = 1;
        }else if (stateString == "malware") {
            state = 2;
        } else {
            cout << "Invalid state: " << stateString << endl;
            exit(1);
        }
        nodes[i] = Block(name, state);
        cout << "Node: " << name << ", State: " << state << endl;
    }
    while (file) {
        string node1, node2;
        double weight;
        file >> node1 >> node2 >> weight;
        if (file) {
            int index1 = findIndex(node1);
            int index2 = findIndex(node2);
            if (index1 != -1 && index2 != -1) {
                connectionsMatrix[index1][index2] = weight;
                connectionsMatrix[index2][index1] = weight; // Assuming undirected graph
            }
        }
    }
    file.close();
}
void CyberAttackContainment::infectionSimulation() {
    Queue malwareQueue;
    for (int i = 0; i < numNodes; i++) {
        if (nodes[i].state == 2) {

            malwareQueue.enqueue(&nodes[i]);
            malwareTime[i] = 0; // Malware starts at time 0
        }
    }

    while (!malwareQueue.isEmpty()) {
        Block* current = malwareQueue.front;
        malwareQueue.dequeue();
        int currentIndex = findIndex(current->getName());

        for (int i = 0; i < numNodes; i++) {
            if (connectionsMatrix[currentIndex][i] > 0 && malwareTime[i] == -1) { // Unvisited
                malwareTime[i] = malwareTime[currentIndex] + 1;
                malwareQueue.enqueue(&nodes[i]);
            }
        }
    }

    // Dijkstra's algorithm
    bool* visited = new bool[numNodes]();
    for (int i = 0; i < numNodes; i++) {
        visited[i] = false;
    }

    for (int i = 0; i < numNodes; i++) {
        if (nodes[i].state == 1) {
            dispatcherTime[i] = 0; // Dispatcher time 0
        }
    }

    for (int i = 0; i < numNodes; i++) {
        if (dispatcherTime[i] == 0) { // Start Dijkstra from each dispatcher
            for (int j = 0; j < numNodes; j++) {
                visited[j] = false;
            }

            for (int j = 0; j < numNodes; j++) {
                int minIndex = -1;
                double minTime = -1;
                for (int k = 0; k < numNodes; k++) {
                    if (!visited[k] && dispatcherTime[k] != -1 &&(minTime == -1 || dispatcherTime[k] < minTime)) {
                        minTime = dispatcherTime[k];
                        minIndex = k;
                    }
                }

                if (minIndex == -1) break;
                visited[minIndex] = true;

                for (int k = 0; k < numNodes; k++) {
                    if (connectionsMatrix[minIndex][k] > 0 && !visited[k]) {
                        double newTime = dispatcherTime[minIndex] + connectionsMatrix[minIndex][k];
                        if (dispatcherTime[k] == -1 || newTime < dispatcherTime[k]) {
                            dispatcherTime[k] = newTime;
                        }
                    }
                }
            }
        }
    }

    for(int i = 0; i < numNodes; i++) {
        if(i == 1) {
            cout<< "dispatcherTime["<<i<<"] = "<<dispatcherTime[i]<<endl;
            cout<< "malwareTime["<<i<<"] = "<<malwareTime[i]<<endl;
        }
        if(dispatcherTime[i]> malwareTime[i]) {
            nodes[i].state = 2;
        }
        else {
            nodes[i].state = 1;
        }
    }


    delete[] visited;
}
void CyberAttackContainment::nodeStatuses() {
    //bubbleSort by names
    for(int i = 0; i<numNodes;i++) {
        for(int j = 0; j<numNodes-1; j++) {
            if(nodes[j].getName() > nodes[j+1].getName()) {
                Block temp = nodes[j];
                nodes[j] = nodes[j+1];
                nodes[j+1] = temp;
            }
        }
    }
    cout << "Node statuses:" << endl;
    for (int i = 0; i < numNodes; i++) {
        cout << "Node: " << nodes[i].getName() << ", State: ";
        if (nodes[i].state == 0) {
            cout << "normal";
        } else if (nodes[i].state == 1) {
            cout << "dispatcher";
        } else if (nodes[i].state == 2) {
            cout << "malware";
        }
        cout << endl;
    }
}

void CyberAttackContainment::computeConnectedZones() {
    bool* visited = new bool[numNodes]();
    for(int i = 0; i < numNodes; i++) {
        visited[i] = false;
    }
    Queue connectedZones;
    int zoneState;
    for(int i = 0; i < numNodes; i++) {
        if(!visited[i]) {
            connectedZones.enqueue(&nodes[i]);

            visited[i] = true;
            cout<< "Connected Zone "<<i<<": ";
            cout<<nodes[i].getName()<<" ";
            zoneState = nodes[i].state;
            while(!connectedZones.isEmpty()) {
                Block* current = connectedZones.front;
                connectedZones.dequeue();
                int currentIndex = findIndex(current->getName());
                for (int j = 0; j < numNodes; j++) {
                    if (connectionsMatrix[currentIndex][j] > 0 && !visited[j] && zoneState == nodes[j].state) { // Unvisited neighbor
                        connectedZones.enqueue(&nodes[j]);
                        visited[j] = true;
                        cout<<nodes[j].getName()<<" ";
                    }
                }
            }
            cout << " - " << zoneState << endl;
        }
    }
    delete[] visited;

}






int CyberAttackContainment::findIndex(const string &name) {
    for(int i = 0; i < numNodes; i++) {
        if(nodes[i].getName() == name) {
            return i;
        }
    }
    return -1;
}
