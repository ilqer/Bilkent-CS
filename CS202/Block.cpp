//
// 22203024 Altay İlker Yiğitel sec1
//
#include "Block.h"

Block::Block() {
    name = "";
    pos = -1;
    state = -1;
    next = nullptr;
}
Block::Block(string name, int state) {
    this->name = name;
    this->state = state;
    next = nullptr;
}
Block::Block(string name,int pos, int state) {
    this->name = name;
    this->pos = pos;
    this->state = state;
    this->state = state;
    next = nullptr;
}
string Block::getName() {
    return name;
}
Block::~Block() = default;
