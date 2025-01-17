//
// Created by ilker on 29.10.2024.
//Altay İlker Yiğitel
//cs201 section 03
//22203024

#include "Mission.h"

Mission::Mission(): spacecraftCount(0) {

}

string Mission:: getDate() const {
return launchDate;
}

string Mission::getMissionName() const {
    return name;
}
string Mission::getMissionDestination() const {
    return destination;
}
int Mission::getSpacecraftCount() const {
    return spacecraftCount;
}
Spacecraft *Mission::getSpacecraftsOnMission() const {
    return spacecraftsOnMission;
}



void Mission::setMissionName(string name) {
    this->name = std::move(name);
}
void Mission::setDestination(string destination) {
    this->destination = std::move(destination);
}
void Mission::setDate(string launchDate) {
    this->launchDate = std::move(launchDate);
}
void Mission::addSpacecraftToMission(Spacecraft &spacecraft) {

    if (spacecraftCount == 0) {
        spacecraftsOnMission = new Spacecraft[1];
        spacecraftCount++;
        spacecraftsOnMission[spacecraftCount-1] = spacecraft;
    }
    else {

        auto* temp = new Spacecraft[spacecraftCount+1];
        for (int i = 0; i < spacecraftCount; i++) {
            temp[i] = spacecraftsOnMission[i];
        }
        delete[] spacecraftsOnMission;
        spacecraftsOnMission = temp;
        spacecraftsOnMission[spacecraftCount] = spacecraft;
        spacecraftCount++;
    }


}
void Mission::removeSpacecraftFromMission(Spacecraft &spacecraft) {
    int index = -1;
    for(int i = 0; i < spacecraftCount; i++) {
        if(spacecraftsOnMission[i].getName() == spacecraft.getName()) {
            index = i;
        }
    }
    if (spacecraftCount <= 1) {
        spacecraftCount = 0;
        delete[] spacecraftsOnMission;
        spacecraftsOnMission = nullptr;
    }
    else {

        auto* temp = new Spacecraft[spacecraftCount-1];

        for(int i = 0; i < index; i++) {
            temp[i] = spacecraftsOnMission[i];
        }
        for(int i = index; i < spacecraftCount-1; i++) {
            temp[i] = spacecraftsOnMission[i+1];
        }
        delete[] spacecraftsOnMission;
        spacecraftsOnMission = temp;
        delete[] temp;
        spacecraftCount--;
    }
}









