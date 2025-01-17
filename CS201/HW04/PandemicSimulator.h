//
// 22203024 Altay İlker Yiğitel sec3
//
#pragma once

#include <string>
#include "Block.h"
#include "Queue.h"
#include <fstream>
#include <iostream>
using namespace std;
class PandemicSimulator {
    int** cityGrid;
    Block** cityGridBlocks;
    int rows{};
    int columns{};
    Queue* queue = nullptr;
     public :
     PandemicSimulator ( const string cityGridFile );
     ~ PandemicSimulator ();

     void displayCityState ( const int time );
     void simulateBlock ( const int row , const int col );
     void simulatePandemic ();
     int** cityStateChanger(int time);
     void gridDisplay(int** grid,int time);
     };
