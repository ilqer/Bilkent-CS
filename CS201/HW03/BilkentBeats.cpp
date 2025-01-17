//
// Created by ilker yiğitel on 10.12.2024.
//

#include "BilkentBeats.h"

using namespace std;

BilkentBeats::BilkentBeats() {}

BilkentBeats::~BilkentBeats() {
    while (users != nullptr) {
        User* toDelete = users;
        users = users->next;
        delete toDelete;
    }
    while (MusicLibrary != nullptr) {
        Song* toDelete = MusicLibrary;
        MusicLibrary = MusicLibrary->next;
        delete toDelete;
    }
}

void BilkentBeats::addUser(const int userId, const string userName) {
    if(users == nullptr) {
        users = new User(userId, userName);
        cout<<"Added user "<<userId<<"."<<endl;
    }
    else {
        User* temp = users;
        if(temp->id == userId) {
            cout << "Cannot add user. There is already a user with ID "<<userId<<"." << endl;
            return;
        }
        if(temp->id > userId) {
            User* newUser = new User(userId, userName);
            newUser->next = temp;
            users = newUser;
            cout<<"Added user "<<userId<<"."<<endl;
            return;
        }
        while(temp->next != nullptr && temp->next->id != userId && temp->next->id < userId) {
            temp = temp->next;
        }
        if(temp->next == nullptr) {
            temp->next = new User(userId, userName);
            cout<<"Added user "<<userId<<"."<<endl;
            return;
        }
        if(temp->next->id == userId) {
            cout << "Cannot add user. There is already a user with ID "<<userId<<"." << endl;
            return;
        }
        if(temp->next->id > userId) {
            User* newUser = new User(userId, userName);
            newUser->next = temp->next;
            temp->next = newUser;
            cout<<"Added user "<<userId<<"."<<endl;
            return;
        }
        cout << "error!!!!!"<<endl;
    }
}
void BilkentBeats::removeUser(const int userId) {
    if(users == nullptr) {
        cout<<"users is nullptr" <<endl;
        return;
    }
    User* temp = users;

    if(temp->id == userId) {
        users = temp->next;
        delete temp;
        cout<<"Removed user "<<userId<<"."<<endl;
        return;
    }
    while(temp->next != nullptr && temp->next->id != userId && temp->next->id < userId) {
        temp = temp->next;
    }
    if(temp->next == nullptr || temp->next->id != userId) {
        cout<<"Cannot remove user. There is no user with ID "<<userId<<"."<<endl;
        return;
    }
    if(temp->next->id == userId) {
        User* toDelete = temp->next;
        temp->next = temp->next->next;
        delete toDelete;
        cout<<"Removed user "<<userId<<"."<<endl;
        return;
    }

    cout<<"error!!!!!!!!!!"<<endl;

}

void BilkentBeats::addSong(const int songID, const string songName, const string artists) {
    if (MusicLibrary == nullptr) {
        MusicLibrary = new Song(songID, songName, artists);
        cout<<"Added song "<<songID<<"."<<endl;
    } else {
        Song* temp = MusicLibrary;
        if(temp->id == songID) {
            cout<<"Cannot add song. BilkentBeats already contains song "<<songID<<"."<<endl;
            return;
        }
        if (temp->id > songID) {
            Song* newSong = new Song(songID, songName, artists);
            newSong->next = temp;
            MusicLibrary = newSong;
            cout<<"Added song "<<songID<<"."<<endl;
            return;
        }
        while (temp->next != nullptr && temp->next->id != songID && temp->next->id < songID) {
            temp = temp->next;
        }
        if (temp->next == nullptr) {
            temp->next = new Song(songID, songName, artists);
            cout<<"Added song "<<songID<<"."<<endl;
            return;
        }
        if (temp->next->id == songID) {
            cout<<"Cannot add song. BilkentBeats already contains song "<<songID<<"."<<endl;
            return;
        }
        if (temp->next->id > songID) {
            Song* newSong = new Song(songID, songName, artists);
            newSong->next = temp->next;
            temp->next = newSong;
            cout<<"Added song "<<songID<<"."<<endl;
            return;
        }
        cout << "error!!!!!" << endl;
    }
}

void BilkentBeats::removeSong(const int songID) {
    if (MusicLibrary == nullptr) {
        cout << "MusicLibrary is empty." << endl;
        return;
    }
    User* userTemp = users;
    Playlist* playlistTemp;
    Song* songTemp;
    while(userTemp!= nullptr) {
        while(userTemp->playlists == nullptr) {
            userTemp = userTemp->next;
        }
        playlistTemp = userTemp->playlists;
        while(playlistTemp != nullptr) {
            songTemp = playlistTemp->songs;
            if(songTemp == nullptr) {
                playlistTemp = playlistTemp->next;
                continue;
            }
            if(songTemp->id == songID) {
                playlistTemp->songs = songTemp->next;
                delete songTemp;
                playlistTemp = playlistTemp->next;
                continue;
            }
            while (songTemp->next != nullptr) {
                if(songTemp->id == songID) {
                    Song* toDelete = songTemp;
                    playlistTemp->songs = songTemp->next;
                    delete toDelete;
                }
                songTemp = songTemp->next;
            }
            playlistTemp = playlistTemp->next;
        }
        userTemp = userTemp->next;
    }
    Song* temp = MusicLibrary;

    if (temp->id == songID) {
        MusicLibrary = temp->next;
        delete temp;
        cout<<"Removed song "<<songID<<"."<<endl;
        return;
    }
    while (temp->next != nullptr && temp->next->id != songID && temp->next->id < songID) {
        temp = temp->next;
    }
    if(temp->next == nullptr || temp->next->id != songID){
        cout<<"Cannot remove song. There is no song with ID "<<songID<<"."<<endl;
        return;
    }
    if (temp->next->id == songID) {
        Song* toDelete = temp->next;
        temp->next = temp->next->next;
        delete toDelete;
        cout<<"Removed song "<<songID<<"."<<endl;
        return;
    }

    cout<<"error!!!!!!!!!!"<<endl;
}

void BilkentBeats::addPlaylist(const int userId, const int playlistId) {
    if(users == nullptr) {
        cout<<"users is nullptr" <<endl;
        return;
    }
    User* temp = users;

    while(temp != nullptr && temp->id != userId && temp->id < userId) {
        temp = temp->next;
    }
    if(temp == nullptr || temp->id != userId ) {
        cout<<"Cannot add playlist. There is no user with ID "<<userId<<"."<<endl;
        return;
    }
    User* check = users;
    Playlist* playlistTemp;
    while(check != nullptr) {
        while (check!=nullptr && check->playlists == nullptr) {
            check = check->next;
        }
        if(check == nullptr) {
            break;
        }
        playlistTemp = check->playlists;

        while (playlistTemp != nullptr && playlistTemp->id <= playlistId ) {
            if (playlistTemp->id == playlistId) {
                cout<<"Cannot add playlist. There is a user having a playlist with ID "<<playlistId<<"."<<endl;
                return;
            }
            playlistTemp = playlistTemp->next;
        }
        check = check->next;
    }
    playlistTemp = temp->playlists;
    if(playlistTemp == nullptr) {
        temp->playlists = new Playlist(playlistId);
        cout<<"Added playlist "<<playlistId<<" to user "<<userId<<"."<<endl;
        return;
    }
    if(playlistTemp->id > playlistId) {
        auto* newPlaylist = new Playlist(playlistId);
        newPlaylist->next = playlistTemp;
        temp->playlists = newPlaylist;
        cout<<"Added playlist "<<playlistId<<" to user "<<userId<<"."<<endl;
        return;
    }
    while(playlistTemp->next!= nullptr && playlistTemp->next->id < playlistId) {
        playlistTemp = playlistTemp->next;
    }
    auto* newPlaylist = new Playlist(playlistId);
    if(playlistTemp->next == nullptr) {
        playlistTemp->next = newPlaylist;
        cout<<"Added playlist "<<playlistId<<" to user "<<userId<<"."<<endl;
        return;
    }
    if(playlistTemp->next->id > playlistId) {
        newPlaylist->next = playlistTemp->next;
        playlistTemp->next = newPlaylist;
        cout<<"Added playlist "<<playlistId<<" to user "<<userId<<"."<<endl;
        return;
    }
    cout<<"error!!!!!!!!!!"<<endl;

    }

void BilkentBeats::removePlaylist(const int userId, const int playlistId) {
    if(users == nullptr) {
        cout<<"users is nullptr" <<endl;
        return;
    }
    User* temp = users;
    while(temp->next != nullptr && temp->next->id != userId && temp->next->id < userId) {
        temp = temp->next;
    }
    if(temp->next == nullptr || temp->next->id != userId) {
        cout<<"Cannot remove playlist. There is no user with ID "<<userId<<"."<<endl;
        return;
    }
    if(temp->next->id == userId) {
        temp = temp->next;
    }
    Playlist* playlistTemp = temp->playlists;
    if(playlistTemp == nullptr) {
        cout<<"Cannot remove playlist. User "<<userId<<" does not have a playlist with ID "<<playlistId<<"."<<endl;
        return;
    }
    if(playlistTemp->id == playlistId) {
        temp->playlists = playlistTemp->next;
        delete playlistTemp;
        cout<<"Removed playlist "<<playlistId<<" from user "<<userId<<"."<<endl;
        return;
    }
    while(playlistTemp->next != nullptr && playlistTemp->next->id != playlistId && playlistTemp->next->id < playlistId) {
        playlistTemp = playlistTemp->next;
    }
    if(playlistTemp->next == nullptr || playlistTemp->next->id != playlistId) {
        cout<<"Cannot remove playlist. User "<<userId<<" does not have a playlist with ID "<<playlistId<<"."<<endl;
        return;
    }
    if(playlistTemp->next->id == playlistId) {
        Playlist* toDelete = playlistTemp->next;
        playlistTemp->next = playlistTemp->next->next;
        delete toDelete;
        cout<<"Removed playlist "<<playlistId<<" from user "<<userId<<"."<<endl;
        return;
    }
    cout<<"error!!!!!!!!!!"<<endl;
}
void BilkentBeats::addSongToPlaylist(const int playlistId, const int songId) {
    if(users == nullptr) {
        cout<<"users is nullptr" <<endl;
        return;
    }
    User* temp = users;
    Playlist* playlistTemp = nullptr;
    do{
        while (temp!=nullptr && temp->playlists == nullptr) {
            temp = temp->next;
        }
        if(temp == nullptr) {
            break;
        }
        playlistTemp = temp->playlists;

        if(playlistTemp!=nullptr && playlistTemp->id == playlistId) {
            break;
        }

        while (playlistTemp != nullptr && playlistTemp->id <= playlistId ) {
            if (playlistTemp->id == playlistId) {
                break;
            }
            playlistTemp = playlistTemp->next;
        }
        if(playlistTemp!=nullptr && playlistTemp->id == playlistId) {
            break;
        }
        temp = temp->next;
    }while(temp != nullptr);

    if(playlistTemp == nullptr || playlistTemp->id != playlistId ) {
        cout<<"Cannot add song. There is no playlist with ID "<<playlistId<<"."<<endl;
        return;
    }

    Song* songTemp = MusicLibrary;
    while(songTemp != nullptr && songTemp->id != songId && songTemp->id < songId) {
        songTemp = songTemp->next;
    }
    if(songTemp == nullptr || songTemp->id != songId) {
        cout<<"Cannot add song. There is no song with ID "<<songId<<"."<<endl;
        return;
    }
    if(songTemp->id == songId) {
        Song* songToAdd = new Song(songTemp->id, songTemp->name, songTemp->artist);
        if(playlistTemp->songs == nullptr) {
            playlistTemp->songs = songToAdd;
            cout<<"Added song "<<songId<<" to playlist "<<playlistId<<"."<<endl;
            return;
        }
        Song* songListTemp = playlistTemp->songs;
        if(songListTemp!=nullptr && songListTemp->id == songId) {
            cout<<"Cannot add song. The playlist already contains song "<<songId<<"."<<endl;
            delete songToAdd;
            return;
        }
        if(songListTemp == nullptr) {
            playlistTemp->songs = songToAdd;
            cout<<"Added song "<<songId<<" to playlist "<<playlistId<<"."<<endl;
            return;
        }
        while(songListTemp->next!=nullptr && songListTemp->next->id !=songId) {
            songListTemp = songListTemp->next;
        }
        if(songListTemp->next == nullptr) {
            songListTemp->next = songToAdd;
            cout<<"Added song "<<songId<<" to playlist "<<playlistId<<"."<<endl;
            return;
        }
        if(songListTemp->next->id == songId) {
            cout<<"Cannot add song. The playlist already contains song "<<songId<<"."<<endl;
            delete songToAdd;
            return;
        }
        cout<<"error!!!!!!!!!!"<<endl;
        }
    }

void BilkentBeats::removeSongFromPlaylist(const int playlistId, const int songId) {
    if(users == nullptr) {
        cout<<"users is nullptr" <<endl;
        return;
    }
    User* temp = users;
    Playlist* playlistTemp = nullptr;
    do{
        while (temp->playlists == nullptr) {
            temp = temp->next;
        }
        playlistTemp = temp->playlists;
        if(playlistTemp->id == playlistId) {
            break;
        }
        while (playlistTemp->next != nullptr && playlistTemp->next->id <= playlistId ) {
            if (playlistTemp->next->id == playlistId) {
                playlistTemp = playlistTemp->next;
                break;
            }
            playlistTemp = playlistTemp->next;
        }
        if(playlistTemp->id == playlistId) {
            break;
        }
        temp = temp->next;
    }while(temp != nullptr);

    if(playlistTemp == nullptr|| playlistTemp->id != playlistId ) {
        cout<<"Cannot remove song. There is no playlist with ID "<<playlistId<<"."<<endl;
        return;
    }

    if(playlistTemp->id == playlistId) {
        Song* songTemp = playlistTemp->songs;
        if(songTemp == nullptr) {
            cout<<"Cannot remove song. There is no song "<<songId<<" in playlist "<<playlistId<<"."<<endl;
            return;
        }

        if(songTemp->id == songId) {
            if(songTemp->next == nullptr) {
                playlistTemp->songs = nullptr;
                delete songTemp;
                cout<<"Removed song "<<songId<<" from playlist "<<playlistId<<"."<<endl;
                return;
            }
            playlistTemp->songs = songTemp->next;
            delete songTemp;
            cout<<"Removed song "<<songId<<" from playlist "<<playlistId<<"."<<endl;
            return;
        }
        while(songTemp->next != nullptr && songTemp->next->id != songId && songTemp->next->id < songId) {
            songTemp = songTemp->next;
        }
        if(songTemp->next == nullptr || songTemp->next->id != songId) {
            cout<<"Cannot remove song. There is no song "<<songId<<" in playlist "<<playlistId<<"."<<endl;
            return;
        }
        if(songTemp->next->id == songId) {
            Song* toDelete = songTemp->next;
            songTemp->next = songTemp->next->next;
            delete toDelete;
            cout<<"Removed song "<<songId<<" from playlist "<<playlistId<<"."<<endl;
            return;
        }
    }
    cout<<"error!!!!!!!!!!"<<endl;
}


void BilkentBeats::printUsers() const {
    if (users == nullptr) {
        cout << "There are no users to show." << endl;
        return;
    }

    cout << "Users in the system:" << endl;
    User* temp = users;
    while (temp != nullptr) {
        cout << "User ID : " << temp->id << ", Name : " << temp->name << ", Playlist IDs : ";
        if (temp->playlists == nullptr) {
            cout << "None";
        } else {
            cout << "[";
            Playlist* playlistTemp = temp->playlists;
            while (playlistTemp != nullptr) {
                cout << playlistTemp->id;
                if (playlistTemp->next != nullptr) {
                    cout << ",";
                }
                playlistTemp = playlistTemp->next;
            }
            cout << "]";
        }
        cout << endl;
        temp = temp->next;
    }
}

void BilkentBeats::printSongs() const {
    if (MusicLibrary == nullptr) {
        cout << "Cannot print songs. There is no song in the music library." << endl;
        return;
    }

    cout << "Music Library:" << endl;
    Song* temp = MusicLibrary;
    while (temp != nullptr) {
        cout << "Song " << temp->id << " : " << temp->name << " - " << temp->artist << endl;
        temp = temp->next;
    }
}

void BilkentBeats::printSongsInPlaylist(const int playlistId) const {
    User* tempUser = users;
    while (tempUser != nullptr) {
        Playlist* tempPlaylist = tempUser->playlists;
        while (tempPlaylist != nullptr) {
            if (tempPlaylist->id == playlistId) {
                Song* songTemp = tempPlaylist->songs;
                if (songTemp == nullptr) {
                    cout << "There are no songs to show in playlist " << playlistId << "." << endl;
                    return;
                }
                cout << "Songs in playlist " << playlistId << ":" << endl;
                while (songTemp != nullptr) {
                    cout << "Song " << songTemp->id << " : " << songTemp->name << " - " << songTemp->artist << endl;
                    songTemp = songTemp->next;
                }
                return;
            }
            tempPlaylist = tempPlaylist->next;
        }
        tempUser = tempUser->next;
    }

    cout << "Cannot print songs. There is no playlist with ID " << playlistId << "." << endl;
}










