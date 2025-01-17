//
// Created by ilker yiğitel on 14.11.2024.
//

#include "searchAlgoritms.h"
#include <iostream>
using namespace std;
int searchAlgoritms::linearSearch(const int array[], int size, int key) {
    for (int i = 0; i < size; i++) {
        if (array[i] == key) {
            return i;//fixtime-----
        }
    }
    return -1;
}
int searchAlgoritms::linearRecursive(const int array[], int size, int key) {
    if (size == 0) {
        return -1;
    }
    if (array[size-1]== key) {
        return size-1;
    }
    return linearRecursive(array, size-1, key);
}
int searchAlgoritms::binarySearch(const int array[], int low,int high, int key) {
    int mid ;
    while (low <= high) {
        mid = low+(high-low)/2;
        if(array[mid] == key) {
            return mid;
        }
        if(array[mid] < key) {
            low = mid+1;
        }
        else  { //if(array[mid] > key)
            high = mid-1;
        }
    }
    return -1;

}

int searchAlgoritms::jumpSearch(const int array[], int size, int key) { ////????????????
    int a = 0;
    int b = sqrt(size);
    while(array[min(b, size-1)] <= key) {  // <=
        a = b;
        b = b+ sqrt(size);
        if(a>size) {
            return -1;
        }
    }
    for(int i = a; i <= b; i++) {
        if(i==size) {
            return -1;
        }
        if(array[i] == key) {
            return i;
        }
    }
    return -1;
}

int searchAlgoritms::randomSearch(const int array[], int size, int key) {
    srand(time(0));
    int indArray[size];
    int index;
    int temp;
    for(int i = 0; i < size; i++) {
        indArray[i] = i;
    }
    for(int i = 0; i < size; i++) {
        index = rand() % (size);
        swap(indArray[i], indArray[index]);
        /*temp = indArray[i];
        indArray[i]= indArray[index];
        indArray[index] = temp;*/
    }
    for (int i = 0; i < size; i++) {
        if (array[indArray[i]] == key) {
            return i;//fixtime-----
        }
    }

    return -1;
}
int searchAlgoritms::min(int a, int b) {
    return a < b ? a : b;
}
int* searchAlgoritms::randArray(int size) {
    srand(time(0));
    int *array = new int[size];
    for (int i = 0; i < size; i++) {
        array[i] = rand()%(size*10);
    }
    return array;
}
void searchAlgoritms::quickSort(int* array, int first, int last) {
    int pivotIndex;
    if (first < last) {
        partition(array, first, last, pivotIndex);
        quickSort(array, first, pivotIndex-1);
        quickSort(array, pivotIndex+1, last);
    }
}
void searchAlgoritms::partition(int array[], int first, int last, int &pivotIndex) {
    // Precondition: array[first..last] is an array; first <= last.
    // Postcondition: Partitions array[first..last] such that:
     //   S1 = array[first..pivotIndex-1] < pivot
     //   array[pivotIndex] == pivot
     //   S2 = array[pivotIndex+1..last] >= pivot
     // place pivot in array[first]
    choosePivot(array, first, last);
    int pivot = array[first];
    // // copy pivot
    // initially, everything but pivot is in unknown
    int lastS1 = first;
    // index of last item in S1 int
    int firstUnknown = first+1;
    // index of first item in unknown
    // move one item at a time until unknown region is empty
    for (  ; firstUnknown <= last; ++firstUnknown) {
        // Invariant:
        //array[first+1..lastS1] < pivot
        //            array[lastS1+1..firstUnknown-1] >= pivot
        // move item from unknown to proper region
        if (array[firstUnknown] < pivot) {
        // belongs to S1
            ++lastS1;
            swap(array[firstUnknown], array[lastS1]);

        }
        //else belongs to S2
        }   // place pivot in proper position and mark its location
        swap(array[first], array[lastS1]);
        pivotIndex = lastS1;
}
void searchAlgoritms::choosePivot(int array[], int first, int last) {
    int mid = first + (last - first) / 2;
    swap(array[first], array[mid]);
}
void searchAlgoritms::swap(int &a, int &b) noexcept {
    int temp = a;
    a = b;
    b = temp;
}

int searchAlgoritms::firstFifthKey(const int* array, int first, int last) {
    srand(time(0));
    last = last/5;
    int key = array[rand()%last];
    return key;
}
int searchAlgoritms::middleFifthKey(const int* array, int first, int last) {
    srand(time(0));
    last = last/5;
    int key = array[rand()%(last)+2*last];
    return key;
}
int searchAlgoritms::lastFifthKey(const int* array, int first, int last) {
    srand(time(0));
    int key = array[rand()%(last/5)+4*last/5];
    return key;
}


void searchAlgoritms::testLinearSearchAlgoritms(const int* array,int size,double&first,double&mid,double&last,double&non) {


    cout <<"Execution for LinearSearch "<<endl;

    //first----------------------------------------------------------
    int key = searchAlgoritms::firstFifthKey(array, 0, size);
    chrono::time_point< std::chrono::system_clock > startTime;
    chrono::duration< double, milli > elapsedTime{};
    //Store the starting time
    startTime = std::chrono::system_clock::now();
    //Code block
    for(int i = 0; i < 10000; i++) {
        searchAlgoritms::linearSearch(array, size, key);
    }
    //Compute the number of seconds that passed since the starting time
    elapsedTime = std::chrono::system_clock::now() - startTime;
    first = elapsedTime.count()/10000;
    cout << "Execution took " << first << " milliseconds for first key." << endl;

    //mid--------------------------------------------------------
    key = searchAlgoritms::middleFifthKey(array, 0, size);
    startTime = std::chrono::system_clock::now();
    for(int i = 0; i < 10000; i++) {
        searchAlgoritms::linearSearch(array, size, key);
    }
    elapsedTime = std::chrono::system_clock::now() - startTime;
    mid = elapsedTime.count()/10000;
    cout << "Execution took " << mid << " milliseconds for mid key." << endl;


    //last--------------------------------------------------------
    key = searchAlgoritms::lastFifthKey(array, 0, size);
    startTime = std::chrono::system_clock::now();
    for(int i = 0; i < 10000; i++) {
        searchAlgoritms::linearSearch(array, size, key);
    }
    elapsedTime = std::chrono::system_clock::now() - startTime;
    last = elapsedTime.count()/10000;
    cout << "Execution took " << last << " milliseconds for last key." << endl;

    //non---------------------------------------------------------
    key = size*10+1;
    startTime = std::chrono::system_clock::now();
    for(int i = 0; i < 10000; i++) {
        searchAlgoritms::linearSearch(array, size, key);
    }
    elapsedTime = std::chrono::system_clock::now() - startTime;
    non = elapsedTime.count()/10000;
    cout << "Execution took " << non << " milliseconds for non existing key." << endl;


}
void searchAlgoritms::testRecLinearSearchAlgoritms(const int* array,int size, double &first, double &mid, double &last, double &non) {

    cout <<"Execution for RecLinearSearch "<<endl;

    //first----------------------------------------------------------
    int key = searchAlgoritms::firstFifthKey(array, 0, size);
    chrono::time_point< std::chrono::system_clock > startTime;
    chrono::duration< double, milli > elapsedTime{};
    //Store the starting time
    startTime = std::chrono::system_clock::now();
    //Code block
    for(int i = 0; i < 10000; i++) {
        searchAlgoritms::linearRecursive(array, size, key);
    }
    //Compute the number of seconds that passed since the starting time
    elapsedTime = std::chrono::system_clock::now() - startTime;
    first = elapsedTime.count()/10000;
    cout << "Execution took " << first << " milliseconds for first key." << endl;

    //mid--------------------------------------------------------
    key = searchAlgoritms::middleFifthKey(array, 0, size);
    startTime = std::chrono::system_clock::now();
    for(int i = 0; i < 10000; i++) {
        searchAlgoritms::linearRecursive(array, size, key);
    }
    elapsedTime = std::chrono::system_clock::now() - startTime;
    mid = elapsedTime.count()/10000;
    cout << "Execution took " << mid << " milliseconds for mid key." << endl;


    //last--------------------------------------------------------
    key = searchAlgoritms::lastFifthKey(array, 0, size);
    startTime = std::chrono::system_clock::now();
    for(int i = 0; i < 10000; i++) {
        searchAlgoritms::linearRecursive(array, size, key);
    }
    elapsedTime = std::chrono::system_clock::now() - startTime;
    last = elapsedTime.count()/10000;
    cout << "Execution took " << last << " milliseconds for last key." << endl;

    //non---------------------------------------------------------
    key = size*10+1;
    startTime = std::chrono::system_clock::now();
    for(int i = 0; i < 10000; i++) {
        searchAlgoritms::linearRecursive(array, size, key);
    }
    elapsedTime = std::chrono::system_clock::now() - startTime;
    non = elapsedTime.count()/10000;
    cout << "Execution took " << non << " milliseconds for non existing key." << endl;

}
void searchAlgoritms::testBinarySearchAlgoritms(const int* array,int size, double &first, double &mid, double &last, double &non) {


    cout <<"Execution for BinarySearch "<<endl;

    //first----------------------------------------------------------
    int key = searchAlgoritms::firstFifthKey(array, 0, size);
    chrono::time_point< std::chrono::system_clock > startTime;
    chrono::duration< double, milli > elapsedTime{};
    //Store the starting time
    startTime = std::chrono::system_clock::now();
    //Code block
    for(int i = 0; i < 10000; i++) {
        searchAlgoritms::binarySearch(array,0, size-1, key);
    }
    //Compute the number of seconds that passed since the starting time
    elapsedTime = std::chrono::system_clock::now() - startTime;
    first = elapsedTime.count()/10000;
    cout << "Execution took " << first << " milliseconds for first key." << endl;

    //mid--------------------------------------------------------
    key = searchAlgoritms::middleFifthKey(array, 0, size);
    startTime = std::chrono::system_clock::now();
    for(int i = 0; i < 10000; i++) {
        searchAlgoritms::binarySearch(array,0, size-1, key);
    }
    elapsedTime = std::chrono::system_clock::now() - startTime;
    mid = elapsedTime.count()/10000;
    cout << "Execution took " << mid << " milliseconds for mid key." << endl;


    //last--------------------------------------------------------
    key = searchAlgoritms::lastFifthKey(array, 0, size);
    startTime = std::chrono::system_clock::now();
    for(int i = 0; i < 10000; i++) {
        searchAlgoritms::binarySearch(array,0, size-1, key);
    }
    elapsedTime = std::chrono::system_clock::now() - startTime;
    last = elapsedTime.count()/10000;
    cout << "Execution took " << last << " milliseconds for last key." << endl;

    //non---------------------------------------------------------
    key = size*10+1;
    startTime = std::chrono::system_clock::now();
    for(int i = 0; i < 10000; i++) {
        searchAlgoritms::binarySearch(array,0, size-1, key);
    }
    elapsedTime = std::chrono::system_clock::now() - startTime;
    non = elapsedTime.count()/10000;
    cout << "Execution took " << non << " milliseconds for non existing key." << endl;

}
void searchAlgoritms::testJumpSearchAlgoritms(const int* array,int size, double &first, double &mid, double &last, double &non) {


    cout <<"Execution for JumpSearch "<<endl;


    //first----------------------------------------------------------
    int key = searchAlgoritms::firstFifthKey(array, 0, size);
    chrono::time_point< std::chrono::system_clock > startTime;
    chrono::duration< double, milli > elapsedTime{};
    //Store the starting time
    startTime = std::chrono::system_clock::now();
    //Code block
    for(int i = 0; i < 10000; i++) {
        searchAlgoritms::jumpSearch(array,size,key);
    }
    //Compute the number of seconds that passed since the starting time
    elapsedTime = std::chrono::system_clock::now() - startTime;
    first = elapsedTime.count()/10000;
    cout << "Execution took " << first << " milliseconds for first key." << endl;

    //mid--------------------------------------------------------
    key = searchAlgoritms::middleFifthKey(array, 0, size);
    startTime = std::chrono::system_clock::now();
    for(int i = 0; i < 10000; i++) {
        searchAlgoritms::jumpSearch(array,size,key);
    }
    elapsedTime = std::chrono::system_clock::now() - startTime;
    mid = elapsedTime.count()/10000;
    cout << "Execution took " << mid << " milliseconds for mid key." << endl;


    //last--------------------------------------------------------
    key = searchAlgoritms::lastFifthKey(array, 0, size);
    startTime = std::chrono::system_clock::now();
    for(int i = 0; i < 10000; i++) {
        searchAlgoritms::jumpSearch(array,size,key);
    }
    elapsedTime = std::chrono::system_clock::now() - startTime;
    last = elapsedTime.count()/10000;
    cout << "Execution took " << last << " milliseconds for last key." << endl;

    //non---------------------------------------------------------
    key = size*10+1;
    startTime = std::chrono::system_clock::now();
    for(int i = 0; i < 10000; i++) {
        searchAlgoritms::jumpSearch(array,size,key);
    }
    elapsedTime = std::chrono::system_clock::now() - startTime;
    non = elapsedTime.count()/10000;
    cout << "Execution took " << non << " milliseconds for non existing key." << endl;

}
void searchAlgoritms::testRandomSearchAlgoritms(const int* array,int size, double &first, double &mid, double &last, double &non) {


    cout <<"Execution for RandomSearch "<<endl;

    //first----------------------------------------------------------
    int key = searchAlgoritms::firstFifthKey(array, 0, size);
    chrono::time_point< std::chrono::system_clock > startTime;
    chrono::duration< double, milli > elapsedTime{};
    //Store the starting time
    startTime = std::chrono::system_clock::now();
    //Code block
    for(int i = 0; i < 10000; i++) {
        searchAlgoritms::randomSearch(array,size,key);
    }
    //Compute the number of seconds that passed since the starting time
    elapsedTime = std::chrono::system_clock::now() - startTime;
    first = elapsedTime.count()/10000;
    cout << "Execution took " << first << " milliseconds for first key." << endl;

    //mid--------------------------------------------------------
    key = searchAlgoritms::middleFifthKey(array, 0, size);
    startTime = std::chrono::system_clock::now();
    for(int i = 0; i < 10000; i++) {
        searchAlgoritms::randomSearch(array,size,key);
    }
    elapsedTime = std::chrono::system_clock::now() - startTime;
    mid = elapsedTime.count()/10000;
    cout << "Execution took " << mid << " milliseconds for mid key." << endl;


    //last--------------------------------------------------------
    key = searchAlgoritms::lastFifthKey(array, 0, size);
    startTime = std::chrono::system_clock::now();
    for(int i = 0; i < 10000; i++) {
        searchAlgoritms::randomSearch(array,size,key);
    }
    elapsedTime = std::chrono::system_clock::now() - startTime;
    last = elapsedTime.count()/10000;
    cout << "Execution took " << last << " milliseconds for last key." << endl;

    //non---------------------------------------------------------
    key = size*10+1;
    startTime = std::chrono::system_clock::now();
    for(int i = 0; i < 10000; i++) {
        searchAlgoritms::randomSearch(array,size,key);
    }
    elapsedTime = std::chrono::system_clock::now() - startTime;
    non = elapsedTime.count()/10000;
    cout << "Execution took " << non << " milliseconds for non existing key." << endl;

}












