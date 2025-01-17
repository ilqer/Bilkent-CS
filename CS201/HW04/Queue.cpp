//
// 22203024 Altay İlker Yiğitel sec3
//

#include "Queue.h"
void Queue::dequeue() {
    if (this->isEmpty()) {
        return;
    }

    // Store previous front and move front one node
    // ahead
    Block* temp = front;
    front = front->next;

    // If front becomes nullptr, then change rear also
    // to nullptr
    if (front == nullptr)
        rear = nullptr;

    // Deallocate memory of the old front node
    delete temp;
}
void Queue::enqueue(int row, int column, int state) {
    if(isEmpty()) {
        front = rear = new Block(row, column, state);
        return;
    }
    rear->next = new Block(row, column, state);
    rear = rear->next;
}
bool Queue::isEmpty() {
    if (front == nullptr) {
        return true;
    }
    return false;
}
Queue::~Queue() {
    while(front!= nullptr && front!=rear) {
        dequeue();
    }
    delete rear;
}


