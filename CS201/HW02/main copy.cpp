#include <iostream>
#include "searchAlgoritms.h"
using namespace std;

int main() {

    int size = 10000;
    double first;
    double mid;
    double last;
    double non;

    int* array = searchAlgoritms::randArray(size);
    for(int i = 0; i < size; i++) {
        cout << array[i] << " ";
    }
    cout << endl;
    searchAlgoritms::quickSort(array, 0, size-1);


    /*searchAlgoritms::linearSearch(array, size,key);
    searchAlgoritms::linearRecursive(array,  size,key);
    searchAlgoritms::binarySearch(array,0,size-1,key);
    searchAlgoritms::jumpSearch(array, size, key);
    searchAlgoritms::randomSearch(array, size, key);*/


    searchAlgoritms::testLinearSearchAlgoritms(array,size,first,mid,last,non);
    searchAlgoritms::testRecLinearSearchAlgoritms(array,size,first,mid,last,non);
    searchAlgoritms::testBinarySearchAlgoritms(array,size,first,mid,last,non);
    searchAlgoritms::testJumpSearchAlgoritms(array,size,first,mid,last,non);
    searchAlgoritms::testRandomSearchAlgoritms(array,size,first,mid,last,non);
    delete[] array;





    return 0;
}
