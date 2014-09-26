/* Problem 32
 * convertIntToArray: returns a given integer as a dynamic array e.g. convertIntToArray(123) = [1, 2, 3]
 * isPandigital: returns whether a dynamic array of integers, where each integer is in {0, 1, ..., 9}, is a permutation of {1, ..., 9}
 * sumSolutions: returns the sum of products made from a n digit multiplier and a m digit multiplicand
*/ 

console.log("The solution is " + (sumSolutions(2, 3) + sumSolutions(1, 4)));

function sumSolutions(n, m) {
	var solutions = []; var sum = 0;
	for (var i = Math.pow(10, n - 1); i < Math.pow(10, n); i++) {
		for (var j = Math.pow(10, m - 1); j < Math.pow(10, m); j++) {
			var multiplier = convertIntToArray(i); var multiplicand = convertIntToArray(j); var product = convertIntToArray(i * j);
			if (isPandigital((product.concat(multiplier)).concat(multiplicand)) && solutions.indexOf(i * j) == -1) {
				solutions.push(i * j); sum += i * j;
			}
		}
	}
	return sum;
}

function isPandigital(T) {
	if (T.length != 9 || T.indexOf(0) > -1) return false;
	for (var i = 0; i < 10; i++) {
		if (T.indexOf(i) != T.lastIndexOf(i)) return false;
	}
	return true;
}

function convertIntToArray(n) {
	var result = []; var divisor = 1; var dividend = n;
	while (true) {
		if (divisor * 10 > n) break;
		divisor *= 10; 
	}
	while (divisor > 0) {
		result.push(Math.floor(dividend / divisor));
		dividend %= divisor;
		divisor = Math.floor(divisor / 10);
	}
	return result;
}