//
// 22203024 Altay İlker Yiğitel sec3
//
#pragma once

#include <string>
#include "Block.h"
using namespace std;

class Queue {

public:

    Block* front;
    Block* rear;
    Queue() { front = rear = nullptr; }
    ~Queue();
    void enqueue(int row, int column, int state);
    void dequeue();
    bool isEmpty();


};
