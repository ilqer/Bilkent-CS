//
// Created by ilker yiğitel on 15.05.2025.
//

#ifndef CYBERATTACKCONTAINMENT_H
#define CYBERATTACKCONTAINMENT_H
#include <string>
#include "Block.h"
#include "Queue.h"
using namespace std;



class CyberAttackContainment {
public:
    CyberAttackContainment(const string inputFile);
    //~CyberAttackContainment();
    // Prints the infection status (Secured or Infected) for each node
    void nodeStatuses();
    // Creates and prints connected components, labeling them as Secured or
    //Infected Zones
    void computeConnectedZones();
    int findIndex(const string& name);
    void infectionSimulation();
private:
    double** connectionsMatrix;
    int numNodes;
    Block* nodes;
    int* malwareTime;
    double* dispatcherTime;
};



#endif //CYBERATTACKCONTAINMENT_H
