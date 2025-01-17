//
// 22203024 Altay İlker Yiğitel sec3
//


#include "Block.h"

Block::Block() {
    row = -1;
    column = -1;
    this->state = -1;
    next = nullptr;
}
Block::Block(int row, int column) {
    this->row = row;
    this->column = column;
    this->state = -1;
    next = nullptr;
}
Block::Block(int row, int column, int state) {
    this->row = row;
    this->column = column;
    this->state = state;
    next = nullptr;
}
Block::~Block() = default;

