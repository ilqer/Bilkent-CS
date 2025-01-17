//
// Created by ilker on 29.10.2024.
//Altay İlker Yiğitel
//cs201 section 03
//22203024
#pragma once


#include <string>
class Mission ;

using namespace std;

class Spacecraft {
    string type;
    string name;
    bool available;



public:
    Spacecraft();
    string getType()const;
    string getName()const;
    bool isAvailable() const;
    Mission getMission()const;
    void setType(string type);
    void setName(string name);
    void setAvaliablity(bool available);
    void setMission(Mission& mission);
    Mission* assignedMission;

};
