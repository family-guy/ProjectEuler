#include <iostream>
#include <string>
#include <vector>
/**
 * Problem 61
 */

/*
* Standard recursive backtrackng function
*/
bool solve(std::vector<int> unorderedSet, bool states[], std::vector<std::vector<int> > T, int nbFigurates);

int main() {
	std::vector<std::vector<int> > figurates;
	std::vector<int> triangle; std::vector<int> square; std::vector<int> pentagonal; std::vector<int> hexagonal; std::vector<int> heptagonal; std::vector<int> octagonal;
	int i = 1;
	while (true) {
		int ithTermTri = i * (i + 1) / 2;
		int ithTermSq = i * i;
		int ithTermPent = i * (3 * i - 1) / 2;
		int ithTermHex = i * (2 * i - 1);
		int ithTermHept = i * (5 * i - 3) / 2;
		int ithTermOct = i * (3 * i - 2);
		if (ithTermTri >= 10000) break;
		if (ithTermTri >= 1000 && ithTermTri < 10000) triangle.push_back(ithTermTri);
		if (ithTermSq >= 1000 && ithTermSq < 10000) square.push_back(ithTermSq);
		if (ithTermPent >= 1000 && ithTermPent < 10000) pentagonal.push_back(ithTermPent);
		if (ithTermHex >= 1000 && ithTermHex < 10000) hexagonal.push_back(ithTermHex);
		if (ithTermHept >= 1000 && ithTermHept < 10000) heptagonal.push_back(ithTermHept);
		if (ithTermOct >= 1000 && ithTermOct < 10000) octagonal.push_back(ithTermOct);
		i++;
	}
	figurates.push_back(triangle); figurates.push_back(square); figurates.push_back(pentagonal); figurates.push_back(hexagonal); figurates.push_back(heptagonal); figurates.push_back(octagonal);
	std::vector<int> unorderedSet;
	const int nbFigurates = 6;
	bool states[nbFigurates] = {false};
	solve(unorderedSet, states, figurates, nbFigurates);
	return 0;
}

bool solve(std::vector<int> unorderedSet, bool states[], std::vector<std::vector<int> > T, int nbFigurates) {
	if (unorderedSet.size() == nbFigurates) {
		std::string lastTwoDigitsLastElt = std::to_string(unorderedSet[nbFigurates - 1]).substr(2);
		std::string firstTwoDigitsFirstElt = std::to_string(unorderedSet[0]).substr(0, 2);
		if (lastTwoDigitsLastElt == firstTwoDigitsFirstElt) {
			std::cout << "The ordered set of six cyclic 4-digit numbers is ";
			for (int i = 0; i < unorderedSet.size(); i++) {
				std::cout << unorderedSet[i] << " ";
			}
			std::cout << std::endl;
			int sum = 0;
			for (int i = 0; i < unorderedSet.size(); i++) {
				sum += unorderedSet[i];
			}
			std::cout << "The solution is " << sum << std::endl;
			return true;
		}
	}
	for (int i = 0; i < nbFigurates; i++) {
		if (!states[i]) {
			for (int j = 0; j < T[i].size(); j++) {
				if (unorderedSet.size() == 0) {
					unorderedSet.push_back(T[i][j]);
					states[i] = true;
					if (solve(unorderedSet, states, T, nbFigurates)) return true;
					unorderedSet.erase(unorderedSet.begin() + unorderedSet.size() - 1);
					states[i] = false;
				}
				else {
					int a = unorderedSet[unorderedSet.size() - 1];
					std::string lastTwoDigitsA = std::to_string(a).substr(2);
					int b = T[i][j];
					std::string firstTwoDigitsB = std::to_string(b).substr(0, 2);
					if (lastTwoDigitsA == firstTwoDigitsB) {
						unorderedSet.push_back(b);
						states[i] = true;
						if (solve(unorderedSet, states, T, nbFigurates)) return true;
						unorderedSet.erase(unorderedSet.begin() + unorderedSet.size() - 1);
						states[i] = false;
					}
				}
			}
		}
	}
	return false;
}