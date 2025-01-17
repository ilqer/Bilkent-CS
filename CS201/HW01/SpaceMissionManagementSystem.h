//
// Created by ilker on 29.10.2024.
//Altay İlker Yiğitel
//cs201 section 03
//22203024

#pragma once

#include <iostream>
#include <string>
#include "Mission.h"
#include "Spacecraft.h"
using namespace std;

class SpaceMissionManagementSystem {
public:
    SpaceMissionManagementSystem();
    ~SpaceMissionManagementSystem();
    void addMission( const string name, const string launchDate, const string
    destination );
    void removeMission( const string name );
    void addSpacecraft( const string name, const string type );
    void removeSpacecraft( const string name );
    void assignSpacecraftToMission( const string spacecraftName, const string
    missionName );
    void dropSpacecraftFromMission( const string spacecraftName );
    void showAllMissions() const;
    void showAllSpacecrafts() const;
    void showMission( const string name ) const;
    void showSpacecraft( const string name ) const;

    Mission* findMission(const string &name) const;
    Spacecraft* findSpacecraft(const string &name) const;

    int findMissionIndex(const string &name) const;

    int findSpacecraftIndex(const string &name) const;

private:
    Mission* missionList;
    Spacecraft* spacecraftList;
    int numberOfSpacecrafts;
    int numberOfMissions;
};



