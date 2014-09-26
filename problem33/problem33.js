/* Problem 33
 * isDigitCancellingFrac: takes a fraction entered as an array (first element the numerator, second the denominator) and returns whether is is digit cancelling
 * gcd: returns the greatest common divisor of a and b, a <= b
*/

var nbSolutions = 0; var numProduct = 1; var denProduct = 1;
for (var p = 10; p < 100; p++) {
	if (nbSolutions == 4) break;
	for (var q = p + 1; q < 100; q++) {
		if (nbSolutions == 4) break;
		var fraction = [p, q];
		if (isDigitCancellingFrac(fraction) && !(p % 10 == 0 && q % 10 == 0)) {
			nbSolutions++;
			numProduct *= p; denProduct *= q;
		}
	}
}
console.log("The solution is " + (denProduct / gcd(numProduct, denProduct)));

function isDigitCancellingFrac(F) {
	var firstDigitNum = Math.floor(F[0] / 10); var secondDigitNum = F[0] % 10;
	var firstDigitDen = Math.floor(F[1] / 10); var secondDigitDen = F[1] % 10;
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

function gcd(a, b) {
	if (a % b == 0) return a;
	return gcd(a % b, a);
}