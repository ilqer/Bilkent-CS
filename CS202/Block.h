//
// 22203024 Altay İlker Yiğitel sec1
//

#pragma once
using namespace std;
#include <string>
class Block {

public:
    string name;
	int pos;
    int state;
    Block* next;
    Block();
    Block(string name, int state);

    Block(string name,int pos, int state);

    ~Block();
    string getName();

private:


};
