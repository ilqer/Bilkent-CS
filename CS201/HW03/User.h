//
// Created by ilker yiğitel on 10.12.2024.
//
#pragma once

#include <iostream>
#include "Playlist.h"
using namespace std;
class User {
public:
    User(const int id, const string name) : id(id), name(name), playlists(nullptr), next(nullptr) {}
    ~User() {
        Playlist* temp = playlists;
        while(temp != nullptr) {
            Playlist* toDelete = temp;
            temp = temp->next;
            delete toDelete;
        }
    }
    int id;
    string name;
    Playlist* playlists;
    User* next;
};
