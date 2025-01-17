//
// Created by ilker on 29.10.2024.
//Altay İlker Yiğitel
//cs201 section 03
//22203024
#pragma once

#include <string>

#include "Spacecraft.h"

using namespace std;

class Mission {
    string  launchDate;
    string name;
    string destination;
    int spacecraftCount;

public:
    Mission();
    string getDate() const;
    string getMissionName() const;
    string getMissionDestination() const;
    void setDate(string launchDate);
    void setMissionName(string name);
    void setDestination(string destination);
    int getSpacecraftCount() const;
    Spacecraft* getSpacecraftsOnMission() const ;
    void addSpacecraftToMission(Spacecraft& spacecraft);

    void removeSpacecraftFromMission(Spacecraft &spacecraft);

    void increaseSpacecraftCount();
    void decreaseSpacecraftCount();
    Spacecraft* spacecraftsOnMission;
};