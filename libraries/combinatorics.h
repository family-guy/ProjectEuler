#ifndef COMBINATORICS_H
#define COMBINATORICS_H
#include <string>
/*
 * This header file contains combinatorics functions for strings and integers
 */

// Auxiliary functions

/**
* Inserts a character into a string
*/
std::string insertCharAt(std::string str, std::string ch, int i);

/**
* Removes a character from a string
*/
std::string removeCharAt(std::string str, int i);

/**
* Gets new strings formed by adding one character to an existing string
*/
std::string * allNewStrings(std::string str, std::string ch);

/**
* Gets new strings formed by adding one character to an existing array of strings
*/
std::string * allNewStrings(std::string T[], std::string ch, int l);

// Functions

/**
* Suppose that we enumerate all k-combinations of a set S of n elements in lexicographic order e.g. if S = {1, 2, 3, 4, 5}, k = 3, then we have {1, 2, 3}, {1, 	2, 4}, {1, 2, 5}, {1, 3, 4}, {1, 3, 5}, {1, 4, 5}, {2, 3, 4}, {2, 3, 5}, {2, 4, 5}, {3, 4, 5}. Given one of these k-combinations, returns the next k-combination e.g. next({1, 4, 5}, 5) returns {2, 3, 4}
*/
int * next(int T[], int t, int n);

/**
* Enumerates all k-combinations of a set containing n elements in lexicographic order e.g. enum_n_choose_k(3, 5) returns {1, 2, 3}, {1, 	2, 4}, {1, 2, 5}, {1, 3, 4}, {1, 3, 5}, {1, 4, 5}, {2, 3, 4}, {2, 3, 5}, {2, 4, 5}, {3, 4, 5}
*/
int ** enumNchooseK(int k, int n);

/**
* Calculates the factorial of a number
*/
int fact(int n);

/**
* Calculates n choose k
*/
int nChooseK(int k, int n);


/**
* Checks if two numbers are permutations of one another
*/
bool isPerm(int a, int b);

/**
* Gets all permutations of a string
*/
std::string * permutationsOfAString(std::string str); 

/**
* Prints an array of strings
*/
void showStrings(std::string T[], int l);

#endif