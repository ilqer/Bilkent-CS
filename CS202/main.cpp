#include <iostream>

#include "CyberAttackContainment.h"
using namespace std;

int main()
{
    CyberAttackContainment cac("network.txt");
    cout<<endl;
    cac.nodeStatuses();
    cout<<endl;
    cac.infectionSimulation();
    cout<<endl;
    cac.nodeStatuses();
    cac.computeConnectedZones();
    return 0;
}
