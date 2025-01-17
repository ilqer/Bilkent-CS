//
// Created by ilker yiğitel on 10.12.2024.
//
#pragma once
#include <iostream>
#include "Song.h"
using namespace std;
class Playlist {
public:
    Playlist(int id) : id(id), songs(nullptr), next(nullptr) {}

    ~Playlist() {
        Song* temp = songs;
        while(temp != nullptr) {
            Song* toDelete = temp;
            temp = temp->next;
            delete toDelete;
        }
    }

    int id;
    Song* songs; //destructor for this?
    //int userID; // I don't think this is necessary
    Playlist* next;
};
