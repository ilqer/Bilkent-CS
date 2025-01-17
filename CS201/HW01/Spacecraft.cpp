//
// Created by ilker on 29.10.2024.
//Altay İlker Yiğitel
//cs201 section 03
//22203024

#include "Spacecraft.h"
#include "Mission.h"
#include <utility>

Spacecraft::Spacecraft() {


}


string Spacecraft::getName() const{
    return name;
}
string Spacecraft::getType() const{
    return type;
}
bool Spacecraft::isAvailable() const {
    return available;
}
Mission Spacecraft::getMission() const {
    return *assignedMission;
}


void Spacecraft::setName(string name) {
    this->name = std::move(name);
}

void Spacecraft::setType(string type) {
    this->type = std::move(type);
}

void Spacecraft::setAvaliablity(bool available) {
    this->available = available;
}
void Spacecraft::setMission(Mission &mission) {
    this->assignedMission = &mission;
}
