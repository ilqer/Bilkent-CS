//
// 22203024 Altay İlker Yiğitel sec3
//
#include "PandemicSimulator.h"


PandemicSimulator::PandemicSimulator(const string cityGridFile) {
    ifstream file;
    file.open(cityGridFile);

    if(!file.is_open())
    {
        std::cout << "File could not be opened." << std::endl;
        exit(1);
    }

    file >> rows;
    file >> columns;


    cityGrid = new int*[rows];
    for (int i = 0; i < rows; i++) {
        cityGrid[i] = new int[columns];
        string line;
        file >> line;
        for (int j = 0; j < columns; j++) {
            cityGrid[i][j] = line[j] - '0'; // Convert character to integer
        }
    }




    cityGridBlocks = new Block*[rows];
    for (int i = 0; i < rows; i++) {
        cityGridBlocks[i] = new Block[columns];
        for (int j = 0; j < columns; j++) {
            cityGridBlocks[i][j] = Block(i, j, cityGrid[i][j]);
        }
    }
    file.close();
}
PandemicSimulator::~PandemicSimulator() {
    for (int i = 0; i < rows; i++) {
        delete[] cityGrid[i];
    }
    delete[] cityGrid;
    for (int i = 0; i < rows; i++) {
        delete[] cityGridBlocks[i];
    }
    delete[] cityGridBlocks;

}
void PandemicSimulator::displayCityState(const int time) {
    int** cityInfection = cityStateChanger(time);
    gridDisplay(cityInfection,time);
    for (int i = 0; i < rows; i++) {
        delete[] cityInfection[i];
    }
    delete[] cityInfection;
}

void PandemicSimulator::simulateBlock(const int row, const int col) {
    if(cityGrid[row][col] == 0) {
        cout << "Time for block ("<<row<<", "<<col<<") to be infected: -1 days."<<endl;
        return;
    }
    if(cityGrid[row][col] == 2) {
        cout << "Time for block ("<<row<<", "<<col<<") to be infected: 0 days."<<endl;
        return;
    }
    int time = 0;
    int** cityInfection = cityStateChanger(time);
    int** cityInfectionNext = cityStateChanger(time+1);
    bool same = true;
    while(cityInfection[row][col] != 2) {
        for(int i = 0; i<rows;i++) {
            for(int j = 0; j<columns;j++) {
                if(cityInfection[i][j] != cityInfectionNext[i][j]) {
                    same = false;
                }
            }
        }
        if(same) {
            cout << "Time for block ("<<row<<", "<<col<<") to be infected: -1 days."<<endl;
            for(int i = 0; i<rows;i++) {
                delete[] cityInfection[i];
                delete[] cityInfectionNext[i];
            }
            delete[] cityInfection;
            delete[] cityInfectionNext;
            return;
        }

        for(int i = 0; i<rows;i++) {
            delete[] cityInfection[i];
        }
        delete[] cityInfection;
        cityInfection = cityInfectionNext;
        time++;
        cityInfectionNext = cityStateChanger(time+1);
        if(cityInfection[row][col]==2) {
            cout<<"Time for block ("<<row<<", "<<col<<") to be infected: "<<time<<" days."<<endl;
            for(int i = 0; i<rows;i++) {
                delete[] cityInfection[i];
                delete[] cityInfectionNext[i];
            }
            delete[] cityInfection;
            delete[] cityInfectionNext;
            return;
        }
        same = true;
    }
    for(int i = 0; i<rows;i++) {
        delete[] cityInfection[i];
        delete[] cityInfectionNext[i];
    }
    delete[] cityInfection;
    delete[] cityInfectionNext;
}


void PandemicSimulator::simulatePandemic() {
    int time = 0;
    int** cityInfection = cityStateChanger(time);
    int** cityInfectionNext = cityStateChanger(time+1);
    bool same = true;
    while(true) {
        for(int i = 0; i<rows;i++) {
            for(int j = 0; j<columns;j++) {
                if(cityInfection[i][j] != cityInfectionNext[i][j]) {
                    same = false;
                }
            }
        }
        if(same) {
            for(int i = 0; i<rows;i++) {
                for (int j = 0; j<columns;j++) {
                    if(cityInfection[i][j] == 1) {
                        cout<<"Pandemic cannot spread to all blocks."<<endl;
                        for(int i = 0; i<rows;i++) {
                            delete[] cityInfection[i];
                            delete[] cityInfectionNext[i];
                        }
                        delete[] cityInfection;
                        delete[] cityInfectionNext;
                        return;
                    }
                }
            }
            cout << "Minimum time for pandemic to spread to all blocks: "<<time<<" days." << endl;
            if(cityInfection != nullptr) {
                for(int i = 0; i<rows;i++) {
                    delete[] cityInfection[i];
                    delete[] cityInfectionNext[i];
                }
                delete[] cityInfection;
                delete[] cityInfectionNext;
            }
            return;
        }
        for(int i = 0; i<rows;i++) {
            delete[] cityInfection[i];
        }
        delete[] cityInfection;

        cityInfection = cityInfectionNext;
        time++;
        cityInfectionNext = cityStateChanger(time+1);
        same = true;
    }
    // fix here with checking if pandemic can spread to all blocks
    cout<<"Pandemic cannot spread to all blocks ."<<endl;
}








int** PandemicSimulator::cityStateChanger(int time) {
    int** cityState = new int*[rows];
    for (int i = 0; i < rows; i++) {
        cityState[i] = new int[columns];
        for (int j = 0; j < columns; j++) {
            cityState[i][j] = cityGrid[i][j];
        }
    }
    if(time == 0) {
        return cityState;
    }

    queue = new Queue();

    for(int i = 0; i<rows;i++) {
        for(int j = 0; j< columns; j++) {
            if(cityState[i][j] == 2) {
                    queue->enqueue(i, j, 2);
            }
        }
    }
    Block* temp;
    for(int i = 0; i<time;i++) {

        while(queue->front!=nullptr && queue->front->state == 2) {
            temp = queue->front;
            if(temp->row - 1 >= 0 && cityState[temp->row - 1][temp->column] == 1) {
                queue->enqueue(temp->row - 1,temp->column, 1);
            }
            if(temp->row + 1 < rows && cityState[temp->row + 1][temp->column] == 1) {
                queue->enqueue(temp->row + 1,temp->column, 1);
            }
            if(temp->column - 1 >= 0 && cityState[temp->row][temp->column - 1] == 1) {
                queue->enqueue(temp->row,temp->column - 1, 1);
            }
            if(temp->column + 1 < columns && cityState[temp->row][temp->column + 1] == 1) {
                queue->enqueue(temp->row,temp->column + 1, 1);
            }
            queue->dequeue();
        }
        if(queue->isEmpty()) {
            break;
        }
        temp = queue->front;
        if(temp!= nullptr) {
            cityState[temp->row][temp->column] = 2;
            temp->state = 2;
        }
        while(temp!=nullptr && temp->next!= nullptr) {
            cityState[temp->next->row][temp->next->column] = 2;
            temp->next->state = 2;
            temp = temp->next;
        }
    }
    delete queue;
    return cityState;
}

void PandemicSimulator::gridDisplay(int** grid, int time) {
    cout << "City state at day " << time << ":" << endl;
    for (int i = 0; i < rows; i++) {
        for (int j = 0; j < columns; j++) {
            cout << grid[i][j];
        }
        cout << endl;
    }
}









