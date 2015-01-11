#include <iostream>
#include <string>
#include <vector>
#include <algorithm>
/**
 * Problem 68
 */

/**
* Returns a solution set given a solution expressed in terms of indices of elements in an array of couples
*/
int ** solutionSet(std::vector<int> solution, std::vector<std::vector<int> > couples);

/**
* Returns if a > b where a and b are integers represented as strings
*/
bool greaterThan(std::string a, std::string b);

/**
* Converts a solution set into a string
*/
std::string solutionSetAsStr(int **solutionSet, int n, int m);

/**
* Recursive backtracking solver
*/
void solve(std::vector<int> solution, std::vector<std::vector<int> > couples, bool *legal, int n);

/**
* Returns an updated array of booleans
*/
bool * sieveCouples1(int a, int b, std::vector<std::vector<int> > couples, bool T[], int t);

/**
* Returns an updated array of booleans
*/
bool * sieveCouples2(int a, std::vector<std::vector<int> > couples, bool T[], int t);

std::string max = "0";

int main() {
	int n = 5; // n-gon ring
	// Get couples
	std::vector<std::vector<int> > couples;
	for (int i = 1; i < 2 * n + 1; i++) {
		for (int j = i + 1; j < 2 * n + 1; j++) {
			std::vector<int> coupleOne;
			std::vector<int> coupleTwo;
			coupleOne.push_back(i); coupleOne.push_back(j);
			coupleTwo.push_back(j); coupleTwo.push_back(i);
			couples.push_back(coupleOne); couples.push_back(coupleTwo);
		}
	}
	bool *legal = new bool[couples.size()];
	for (int i = 0; i < couples.size(); i++) {
		legal[i] = true;
	}
	std::vector<int> solution;
	solve(solution, couples, legal, n);
	std::cout << "The solution is " << max << std::endl;
}

int ** solutionSet(std::vector<int> solution, std::vector<std::vector<int> > couples) {
	int **result = new int * [solution.size()];
	for (int i = 0; i < solution.size(); i++) {
		result[i] = new int[3];
	}
	for (int i = 0; i < solution.size(); i++) {
		for (int j = 0; j < couples[solution[i]].size(); j++) {
			result[i][j] = couples[solution[i]][j];
		}
		result[i][couples[i].size()] = couples[solution[(i + 1) % solution.size()]][1];
	}
	return result;
}

bool greaterThan(std::string a, std::string b) {
	if (a.length() != b.length()) return a.length() > b.length();
	for (int i = 0; i < a.length(); i++) {
		int digitA = atoi(a.substr(i, 1).c_str());
		int digitB = atoi(b.substr(i, 1).c_str());
		if (digitA != digitB) return digitA > digitB;
	}
	return false;
}

std::string solutionSetAsStr(int **solutionSet, int n, int m) {
	std::string result = "";
	for (int i = 0; i < n; i++) {
		for (int j = 0; j < m; j++) {
			result += std::to_string(solutionSet[i][j]);
		}
	}
	return result;
}

void solve(std::vector<int> solution, std::vector<std::vector<int> > couples, bool *legal, int n) {
	if (solution.size() == n) {
		int total = couples[solution[0]][0] + couples[solution[0]][1] + couples[solution[1]][1];
		if (couples[solution[solution.size() - 1]][0] + couples[solution[solution.size() - 1]][1] + couples[solution[0]][1] == total) {
			int ** solSet = solutionSet(solution, couples);
			std::string solSetAsStr = solutionSetAsStr(solSet, n, 3);
			if (greaterThan(solSetAsStr, max) && solSetAsStr.length() == 16) max = solSetAsStr;
			return;
		}
	}
	else {
		if (solution.size() == 0) {
			for (int i = 0; i < couples.size(); i++) {
				solution.push_back(i);
				bool *sievedLegal = sieveCouples2(couples[i][0], couples, sieveCouples1(couples[i][0], couples[i][1], couples, legal, couples.size()), couples.size());
				solve(solution, couples, sievedLegal, n);
				solution.erase(solution.begin() + (solution.size() - 1));
			}
		}
		if (solution.size() == 1) {
			for (int i = 0; i < couples.size(); i++) {
				if (legal[i]) {
					if (couples[i][0] < couples[solution[0]][0] + couples[solution[0]][1]) {
						solution.push_back(i);
						bool *sievedLegal = sieveCouples1(couples[i][0], couples[i][1], couples, legal, couples.size());
						solve(solution, couples, sievedLegal, n);
						solution.erase(solution.begin() + (solution.size() - 1));
					}
				}
			}
		}
		if (solution.size() > 1) {
			for (int i = 0; i < couples.size(); i++) {
				if (legal[i]) {
					int total = couples[solution[0]][0] + couples[solution[0]][1] + couples[solution[1]][1];
					if (couples[i][1] + couples[solution[solution.size() - 1]][0] + couples[solution[solution.size() - 1]][1] == total) {
						solution.push_back(i);
						bool *sievedLegal = sieveCouples1(couples[i][0], couples[i][1], couples, legal, couples.size());
						solve(solution, couples, sievedLegal, n);
						solution.erase(solution.begin() + (solution.size() - 1));
					}
				}
			}
		}
	}
}

bool * sieveCouples1(int a, int b, std::vector<std::vector<int> > couples, bool T[], int t) {
	bool * result = new bool[t];
	for (int i = 0; i < t; i++) {
		if (std::find(couples[i].begin(), couples[i].end(), a) - couples[i].begin() < couples[i].size() || std::find(couples[i].begin(), couples[i].end(), b) - couples[i].begin() < couples[i].size()) result[i] = false;
		else result[i] = T[i];
	}
	return result;
}

bool * sieveCouples2(int a, std::vector<std::vector<int> > couples, bool T[], int t) {
	bool * result = new bool[t];
	for (int i = 0; i < couples.size(); i++) {
		if (couples[i][0] <= a) result[i] = false;
		else result[i] = T[i];
	}
	return result;
}