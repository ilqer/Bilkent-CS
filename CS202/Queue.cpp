//
// 22203024 Altay İlker Yiğitel sec1
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
    temp->next = nullptr;
}
void Queue::enqueue(string name, int state) {
    if(isEmpty()) {
        front = rear = new Block(name, state);
        return;
    }
    rear->next = new Block(name, state);
    rear = rear->next;
}
void Queue::enqueue(Block* block) {
    if(isEmpty()) {
        front = rear = block;
        return;
    }
    rear->next = block;
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


