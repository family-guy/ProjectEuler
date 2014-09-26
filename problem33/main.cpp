#include <iostream>
#include <integer_arithmetic.h>
/* Problem 33
 * isDigitCancellingFrac: takes a fraction entered as an array and returns whether is is digit cancelling
*/

bool isDigitCancellingFrac(int F[]);

int main() {
	int nbSolutions = 0; int numProduct = 1; int denProduct = 1;
	for (int p = 10; p < 100; p++) {
		if (nbSolutions == 4) break;
		for (int q = p + 1; q < 100; q++) {
			if (nbSolutions == 4) break;
			int fraction[2] = {p, q};
			if (isDigitCancellingFrac(fraction) && !(p % 10 == 0 && q % 10 == 0)) {
				nbSolutions++;
				numProduct *= p; denProduct *= q; 
			}
		}
	}
	std::cout << "The solution is " << denProduct / gcd(numProduct, denProduct) << std::endl;
	return 0;
}

bool isDigitCancellingFrac(int F[]) {
	int firstDigitNum = F[0] / 10; int secondDigitNum = F[0] % 10;
	int firstDigitDen = F[1] / 10; int secondDigitDen = F[1] % 10;
	if (firstDigitNum == firstDigitDen || firstDigitNum == secondDigitDen) {
		if (firstDigitNum == firstDigitDen) {
			if (secondDigitNum * F[1] == F[0] * secondDigitDen) return true;
			return false;
		}
		else {
			if (secondDigitNum * F[1] == F[0] * firstDigitDen) return true;
			return false;
		}
	}
	if (secondDigitNum == firstDigitDen || secondDigitNum == secondDigitDen) {
		if (secondDigitNum == firstDigitDen) {
			if (firstDigitNum * F[1]  == F[0] * secondDigitDen) return true;
			return false;
		}
		else {
			if (firstDigitNum * F[1]  == F[0] * firstDigitDen) return true;
			return false;
		}
	}
	return false;
}