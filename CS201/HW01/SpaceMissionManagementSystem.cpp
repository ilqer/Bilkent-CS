//
// Created by ilker on 29.10.2024.
//Altay İlker Yiğitel
//cs201 section 03
//22203024
#include "SpaceMissionManagementSystem.h"


SpaceMissionManagementSystem::SpaceMissionManagementSystem() {

    missionList = nullptr;
    spacecraftList = nullptr;

    numberOfSpacecrafts = 0;
    numberOfMissions = 0;

    //transaction happened?
}

SpaceMissionManagementSystem::~SpaceMissionManagementSystem() {

    delete[] missionList;
    delete[] spacecraftList;
}

void SpaceMissionManagementSystem::addMission( const string name, const string launchDate, const string
    destination ) {

    if(findMission(name) != nullptr) {
        cout << "Cannot add mission. Mission "<< name << " already exists." << endl;
        return;
    }

    if(missionList == nullptr) {
        missionList = new Mission[1];
        numberOfMissions++;
    }
    else {
        numberOfMissions++;
        auto* temp = new Mission[numberOfMissions];
        for(int i = 0; i < numberOfMissions-1; i++) {
            temp[i] = missionList[i];
        }
        delete[] missionList;
        missionList = temp;
    }

    Mission newMission;
    newMission.setMissionName(name);
    newMission.setDate(launchDate);
    newMission.setDestination(destination);

    missionList[numberOfMissions-1] = newMission;
    cout<<"Added mission "<<name<<"."<< endl;

}
void SpaceMissionManagementSystem::removeMission(const string name) {
    int index = findMissionIndex(name);

    if(index == -1) {
        cout<<"Cannot remove mission. Mission "<<name<<" does not exist."<<endl;

        return;

    }
    /*if(missionList[index].getSpacecraftCount() != 0) {
        for(int i = 0; i<missionList[index].getSpacecraftCount(); i++) {
            missionList[index].spacecraftsOnMission[i].setAvaliablity(true);
        }

        //delete or null [] missionList[index].spacecraftsOnMission
    }*/
    for(int i = 0; i < numberOfSpacecrafts; i++) {
        if(spacecraftList[i].assignedMission !=nullptr && spacecraftList[i].assignedMission->getMissionName() == name){
            spacecraftList[i].assignedMission = nullptr;
            spacecraftList[i].setAvaliablity(true);

            }
    }

    numberOfMissions--;
    if(numberOfMissions == 0) {
        missionList = nullptr;
    }
    else {
        auto* temp = new Mission[numberOfMissions];
        for(int i = 0; i < index; i++) {
            temp[i] = missionList[i];

        }
        for(int i = index; i < numberOfMissions; i++) {
            temp[i] = missionList[i+1];
        }
        delete[]missionList;
        missionList = temp;
    }


    cout<<"Removed mission "<<name <<"."<<endl;

}

void SpaceMissionManagementSystem::addSpacecraft( const string name, const string type ) {
    if(findSpacecraftIndex(name) != -1) {
        cout<<"Cannot add spacecraft. Spacecraft "<<name<<" already exists."<<endl;
        return;
    }
    if(numberOfSpacecrafts == 0) {
        spacecraftList = new Spacecraft[1];
        numberOfSpacecrafts++;
    }
    else {
        numberOfSpacecrafts++;
        auto* temp = new Spacecraft[numberOfSpacecrafts];
        for(int i = 0; i < numberOfSpacecrafts-1; i++) {
            temp[i] = spacecraftList[i];
        }
        delete[] spacecraftList;
        spacecraftList = temp;
    }

    Spacecraft newSpacecraft;
    newSpacecraft.setName(name);
    newSpacecraft.setType(type);
    newSpacecraft.setAvaliablity(true);

    spacecraftList[numberOfSpacecrafts-1] = newSpacecraft;
    cout<<"Added spacecraft "<<name<<"."<<endl;

}
void SpaceMissionManagementSystem::removeSpacecraft( const string name ) {
    int index = findSpacecraftIndex(name);
    if(index == -1) {
        cout<<"Cannot remove spacecraft. Spacecraft "<<name<<" does not exist."<<endl;
        return;
    }
    if(!spacecraftList[index].isAvailable()) {
        cout<<"Cannot remove spacecraft. Spacecraft "<<name<<" is assigned to a mission."<<endl;
        return;
    }

    numberOfSpacecrafts--;
    if(numberOfSpacecrafts == 0) {
        spacecraftList = nullptr;
    }
    else {
        auto* temp = new Spacecraft[numberOfSpacecrafts];
        for(int i = 0; i < index; i++) {
            temp[i] = spacecraftList[i];

        }
        for(int i = index; i < numberOfSpacecrafts; i++) {
            temp[i] = spacecraftList[i+1];
        }
        delete[] spacecraftList;
        spacecraftList = temp;
    }

    cout<<"Removed spacecraft "<<name<<"."<<endl;
}

void SpaceMissionManagementSystem::assignSpacecraftToMission(const string spacecraftName, const string missionName) {
    Mission* mission = findMission(missionName);
    Spacecraft* spacecraft = findSpacecraft(spacecraftName);

    if(spacecraft == nullptr) {
        cout<<"Cannot assign spacecraft. Spacecraft " << spacecraftName << " does not exist."<<endl;
        return;
    }
    if(mission == nullptr) {
        cout<<"Cannot assign spacecraft. Mission "<< missionName<<" does not exist."<<endl;
        return;
    }
    if(!spacecraft->isAvailable()) {
        cout<<"Cannot assign spacecraft. Spacecraft "<<spacecraftName<<" is already assigned to mission "<<spacecraft->assignedMission->getMissionName()<<"."<<endl;
        return;
    }

    mission->addSpacecraftToMission(*spacecraft);
    spacecraft->setAvaliablity(false);
    spacecraft->setMission(*mission);
    cout<<"Assigned spacecraft "<<spacecraftName<<" to mission "<< missionName<<"."<<endl;

}
void SpaceMissionManagementSystem::dropSpacecraftFromMission(const string spacecraftName) {
    Spacecraft * spacecraft = findSpacecraft(spacecraftName);
    if(spacecraft == nullptr) {
        cout<<"Cannot drop spacecraft. Spacecraft "<<spacecraftName<<" does not exist."<<endl;
        return;
    }
    if(spacecraft->isAvailable()) {
        cout<<"Cannot drop spacecraft. Spacecraft "<<spacecraftName<< " is not assigned to any mission."<<endl;
        return;

    }
    cout<<"Dropped spacecraft "<<spacecraftName<<" from mission "<<spacecraft->getMission().getMissionName()<<"."<<endl;


    spacecraft->assignedMission->removeSpacecraftFromMission(*spacecraft);
    spacecraft->setAvaliablity(true);
    spacecraft->assignedMission = nullptr;



}
void SpaceMissionManagementSystem::showMission(const string missionName) const{
    int index = findMissionIndex(missionName);
    if(index == -1) {
        cout<<"Cannot show mission. Mission "<<missionName<<" does not exist."<<endl;
        return;
    }

    cout<<"Mission:"<<endl;
    cout<<"  Name: "<<missionName<<endl;
    cout<<"  Launch Date: "<<missionList[index].getDate()<<endl;
    cout<<"  Destination: "<<missionList[index].getMissionDestination()<<endl;
    cout<<"  Assigned Spacecrafts:"<<endl;
    if(missionList[index].getSpacecraftCount()==0) {
        cout<<"    None"<<endl;
    }
    for(int i = 0; i < missionList[index].getSpacecraftCount(); i++) {
        cout<<"  - "<<missionList[index].spacecraftsOnMission[i].getName()<<endl;
    }

}
void SpaceMissionManagementSystem::showSpacecraft( const string name ) const {
    int index = findSpacecraftIndex(name);
    string avb;
    if(index == -1) {
        cout<<"Cannot show spacecraft. Spacecraft "<<name<<" does not exist."<<endl;
        return;
    }
    if(spacecraftList[index].isAvailable()) {
        avb = "Available";
    }
    else {
        avb = "Assigned";
    }
    if(index != -1) {
        cout<<"Spacecraft: "<<name<<", Type: "<<spacecraftList[index].getType()<<", Status: "<< avb <<endl;
    }
}
void SpaceMissionManagementSystem::showAllSpacecrafts() const {
    cout<<"Spacecrafts in the space mission management system:"<<endl;
    if(numberOfSpacecrafts == 0) {
        cout<<"None"<<endl;
    }
    string avb;
    for(int i = 0; i < numberOfSpacecrafts; i++) {
        if(spacecraftList[i].isAvailable()) {
            avb = "Available";
        }
        else {
            avb = "Assigned";
        }
        cout<<"Spacecraft: "<<spacecraftList[i].getName()<<", Type: "<<spacecraftList[i].getType()<<", Status: "<<avb <<endl;
    }
}
void SpaceMissionManagementSystem::showAllMissions() const {
    cout<<"Missions in the space mission management system:"<<endl;
    if(numberOfMissions == 0) {
        cout<<"None"<<endl;
    }
    for(int i = 0; i < numberOfMissions; i++) {
        cout<<"Mission: "<<missionList[i].getMissionName()<<", Launch Date: "<<missionList[i].getDate()<<", Destination: "<<missionList[i].getMissionDestination()<<", Assigned Spacecraft ";
        cout<<"Count: " << missionList[i].getSpacecraftCount()<< endl;
    }

}







Mission* SpaceMissionManagementSystem::findMission( const string& name ) const{

    for(int i = 0; i < numberOfMissions; i++) {
        if(missionList[i].getMissionName() == name) {
            return &missionList[i];
        }
    }
    return nullptr;  //if not found
}
Spacecraft* SpaceMissionManagementSystem::findSpacecraft( const string& name ) const {
    for(int i = 0; i < numberOfSpacecrafts; i++) {
        if(spacecraftList[i].getName() == name) {
            return &spacecraftList[i];
        }
    }
    return nullptr;  //if not found
}
int SpaceMissionManagementSystem::findMissionIndex( const string& name ) const{

    for(int i = 0; i < numberOfMissions; i++) {
        if(missionList[i].getMissionName() == name) {
            return i;
        }
    }
    return -1;  //if not found
}
int SpaceMissionManagementSystem::findSpacecraftIndex( const string& name ) const {
    for(int i = 0; i < numberOfSpacecrafts; i++) {
        if(spacecraftList[i].getName() == name) {
            return i;
        }
    }
    return -1;  //if not found
}
