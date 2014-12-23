#include <iostream>
#include <string>
/* 
 * Problem 62
 */

int main() {
	int n = 2000000;
	long *cubes = new long[n];
	for (int i = 0; i < n; i++) {
		long iAsLong = (long)i;
		cubes[i] = iAsLong * iAsLong * iAsLong;
	}
	for (int i = 0; i < n; i++) {
		std::string cubeIAsStr = std::to_string(cubes[i]);
		int nbDigitsCubeI = cubeIAsStr.length(); 
		int nbPermsCubeI = 0;
		for (int j = 0; j < n; j++) {
			std::string cubeJAsStr = std::to_string(cubes[j]);
			int nbDigitsCubeJ = cubeJAsStr.length();
			if (nbDigitsCubeJ > nbDigitsCubeI) break;
			if (nbDigitsCubeJ == nbDigitsCubeI) {
				int *freqCubeI = new int[10];
				int *freqCubeJ = new int[10];
				for (int k = 0; k < nbDigitsCubeI; k++) {
					freqCubeI[atoi(cubeIAsStr.substr(k, 1).c_str())]++;
					freqCubeJ[atoi(cubeJAsStr.substr(k, 1).c_str())]++;
				}
				for (int l = 0; l < 10; l++) {
					if (freqCubeI[l] != freqCubeJ[l]) break;
					if (l == 9) nbPermsCubeI++;
				}
			}
		}
		if (nbPermsCubeI == 5) {
			std::cout << "The solution is " << cubes[i] << std::endl;
			break;
		}		
	}
	return 0;
}