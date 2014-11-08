#ifndef COMBINATORICS_H
#define COMBINATORICS_H
#include <string>
/*
 * This header file contains combinatorics functions for strings and integers
 * fact: returns the factorial of a non-negative integer
 * nChooseK: returns the value of C^k_n
 * isPerm: takes two positive integers and returns whether they are permutations of each other 
 * permutationsOfAString: returns all permutations of a string
 * showStrings: prints an array of strings
*/

// auxiliary functions
std::string insertCharAt(std::string str, std::string ch, int i);

std::string removeCharAt(std::string str, int i);

std::string * allNewStrings(std::string str, std::string ch);

std::string * allNewStrings(std::string T[], std::string ch, int l);

//main functions
int fact(int n);

int nChooseK(int k, int n);

bool isPerm(int a, int b);

std::string * permutationsOfAString(std::string str); 

void showStrings(std::string T[], int l);

#endif