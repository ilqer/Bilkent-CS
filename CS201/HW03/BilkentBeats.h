//
// Created by ilker yiğitel on 10.12.2024.
//

#pragma once

#include <iostream>
#include "User.h"
#include "Song.h"
using namespace std;

class BilkentBeats {
     public:
     BilkentBeats();
     ~BilkentBeats();

     void addUser( const int userId, const string userName );
     void removeUser( const int userId );
     void printUsers() const;

     void addSong( const int songID, const string songName, const string artists );
     void removeSong( const int songID );
     void printSongs() const;

     void addPlaylist( const int userId, const int playlistId );
     void removePlaylist( const int userId, const int playlistId );
     void addSongToPlaylist( const int playlistId, const int songId );
     void removeSongFromPlaylist( const int playlistId, const int songId );
     void printSongsInPlaylist( const int playlistId ) const;


private:
     User* users = nullptr;
     Song* MusicLibrary = nullptr;

     };
