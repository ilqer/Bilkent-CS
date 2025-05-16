//
// 22203024 Altay İlker Yiğitel sec1
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
    void enqueue(string name, int state);
    void enqueue(Block* block);
    void dequeue();
    bool isEmpty();


};
