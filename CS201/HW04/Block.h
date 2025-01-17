//
// 22203024 Altay İlker Yiğitel sec3
//

#pragma once
#include <string>
using namespace std;

class Block {

public:
    int row;
    int column;
    int state;
    Block* next;
    Block();
    Block(int row, int column);

    Block(int row, int column, int state);

    ~Block();

private:


};
