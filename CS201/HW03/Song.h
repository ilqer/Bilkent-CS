//
// Created by ilker yiğitel on 10.12.2024.
//
#pragma once

#include <iostream>

using namespace std;


class Song {
public:
    int id;
    string name;
    string artist;
    Song* next;

    Song(const int id, const string name, const string artist) : id(id), name(name), artist(artist), next(nullptr) {}
};



