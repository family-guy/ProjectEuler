/*
 * Problem 66
 * See http://en.wikipedia.org/wiki/Pell%27s_equation#Concise_representation_and_faster_algorithms, section 3 'Example'
 */

var currentMax = '0';
var currentMaxIndex = 0;
for (var i = 0; i <= 1000; i++) {
	if (!isSquare(i)) {
		var str = minSolInX(i);
		if (greaterThan(str, currentMax)) {
			currentMax = str;
			currentMaxIndex = i;
		}
	}
}
console.log('The solution is: D equals ' + currentMaxIndex + ' for which the value of x is ' + currentMax);

/*
* Returns whether a positive integer is a square
* @param integer n
* @return boolean
*/
function isSquare(n) {
	var a = Math.floor(Math.sqrt(n));
	return a * a == n;
}

/*
* Takes two non-negative integers a and b represented as strings and returns whether a is greater than b
* @param string a
* @param string b
* @return boolean
*/
function greaterThan(a, b) {
	if (a.length < b.length) return false;
	if (a.length == b.length) {
		for (var i = 0; i < a.length; i++) {
			var aDigit = a.substring(i, i + 1);
			var bDigit = b.substring(i, i + 1);
			if (aDigit != bDigit) return parseInt(aDigit) > parseInt(bDigit); 
		}
		return false;
	}
	return true;
}

/*
* Returns the minimal solution in x for a given D
* @param integer D
* @return string
*/
function minSolInX (D) {
	var i = 0;
	while (true) {
		var x = kthTermInfContFrac(i, D)[0];
		var y = kthTermInfContFrac(i, D)[1];
		if (mult(x, x) == add(mult(D.toString(), mult(y, y)), '1')) return x;
		i++;
	}
}

/*
* Takes an integer and returns the k-th term in the sequence of partial values of its infinite continued fraction
* @param integer k
* @param integer n
* @return array of strings
*/
function kthTermInfContFrac(k, n) {
	var result = [];
	var infContFracNotation = infContFrac(n);
	if (k == 0) {
		result.push(infContFracNotation[0][0].toString());
		result.push('1');
		return result;
	}
	var values = repeatBlock(infContFracNotation[1], k);
	var currentFrac = [];
	currentFrac.push(values[values.length - 1]);
	currentFrac.push('1');
	for (var i = values.length - 2; i > -1; i--) {
		var currentInt = values[i];
		var next = f(currentInt, currentFrac[0], currentFrac[1]);
		currentFrac[0] = next[0];
		currentFrac[1] = next[1];
	}
	result[0] = add(mult(infContFracNotation[0][0].toString(), currentFrac[0]), currentFrac[1]);
	result[1] = currentFrac[0];
	return result;
}

/*
* Takes an array of integers and returns an array of length k by repeating the values in the array given e.g. repeatBlock([1, 1, 5], 7) returns [1, 1, 5, 1, 1, 5, 1]
* @param array of integers block
* @param integer k
* @return array of strings
*/
function repeatBlock(block, k) {
	var values = [];
	for (var i = 0; i < k; i++) {
		values.push(block[i % block.length].toString());
	}
	return values;
}

/**
* Takes a triple (a, b_1, b_2) and returns a + 1 / b where b = b_1 / b_2
* @param string a
* @param string b_1
* @param string b_2
* @return array of strings
*/
function f(a, b_1, b_2) {
	var result = [];
	result.push(add(mult(a, b_1), b_2)); // numerator
	result.push(b_1); // denominator
	return result;
}

/**
* Takes two integers a and b, where a >= b, represented by strings, and returns a string representing their sum
* @param string a
* @param string b
* @return string
*/
function add(a, b) {
	var result = '';
	var difference = a.length - b.length;
	for (var i = 0; i < difference; i++) {
		b = '0' + b;
	}
	var carryOver = 0;
	for (var i = a.length - 1; i > -1; i--) {
		var x = parseInt(a.substring(i, i + 1));
		var y = parseInt(b.substring(i, i + 1));
		var value = (x + y + carryOver) % 10;
		result = value.toString() + result;
		carryOver = Math.floor((x + y + carryOver) / 10);
	}
	if (carryOver == 0) return result;
	return carryOver.toString() + result;
}

/**
* Returns the product a * b of two non-negative integers where b is a digit and a is represented as a string
* @param string a
* @param integer b
* @return string
*/
function multAux(a, b) {
	if (b == 0) return "0";
	var result = '';
	var carryOver = 0; var p = 0;
	for (var i = 0; i < a.length - 1; i++) {
		p = parseInt(a.substring(a.length - 1 - i, a.length - i)) * b + carryOver;
		result = (p % 10).toString() + result;
		carryOver = Math.floor(p / 10);
	}
	p = parseInt(a.substring(0, 1)) * b + carryOver;
	while (true) {
		result = (p % 10) + result;
		if (Math.floor(p / 10) == 0) break;
		p = Math.floor(p / 10);
	}
	return result;
}

/**
* Returns the product of two non-negative integers represented as strings
* @param string a
* @param string b
* @return string
*/
function mult(a, b) {
	if (a == '0' || b == '0') return '0';
	var sum = '0';
	for (var i = 0; i < a.length; i++) {
		var zeros = '';
		for (var j = 0; j < a.length - 1 - i; j++) {
			zeros += '0';
		}
		// if/else because in sum(a, b), a >= b
		if (i == 0) {
			sum = add(multAux(b, parseInt(a.substring(i, i + 1))) + zeros, sum); 
		}
		else {
			sum = add(sum, multAux(b, parseInt(a.substring(i, i + 1))) + zeros); 
		}
	}
	return sum;
}

/*
* Takes a non-square positive integer and returns its infinite contiuned fraction e.g. infContFrac(23) returns [[4], [1, 3, 1, 8]]
* @param integer n
* @return two dimensional array of integers
*/
function infContFrac(n) {
	var result = [];
	var firstElt = [Math.floor(Math.sqrt(n))];
	result.push(firstElt);
	var secondElt = [];
	var firstTriple = [1, n, (-1) * firstElt[0]];
	var currentTriple = [1, n, (-1) * firstElt[0]];
	while (true) {
		var intermedFracDen = currentTriple[1] - currentTriple[2] * currentTriple[2];
		secondElt.push(Math.floor(currentTriple[0] * (Math.sqrt(currentTriple[1]) - currentTriple[2]) / intermedFracDen));
		var next = nextTerm(currentTriple);
		if (equals(next, firstTriple)) break;
		for (var i = 0; i < currentTriple.length; i++) {
			currentTriple[i] = next[i];
		}
	}
	result.push(secondElt);
	return result;
}

/*
* Takes a triple (a, b, c) representing a fraction of the form a / (sqrt(b) + c) and returns the next fraction (of the same form) in the sequence
* @param array of integers T
* @return array of integers
*/
function nextTerm(T) {
	var result = [];
	var intermedFracDen = T[1] - T[2] * T[2];
	result.push(Math.floor(intermedFracDen / T[0]));
	result.push(T[1]);
	var intermedFracWholePart = Math.floor(T[0] * (Math.sqrt(T[1]) - T[2]) / intermedFracDen);
	result.push((-1) * T[2] - intermedFracWholePart * result[0]);
	return result;
}

/*
* Returns if two arrays of integers are equal
* @param array of integers T
* @param array of integers U
* @return boolean
*/
function equals(T, U) {
	if (T.length != U.length) return false;
	for (var i = 0; i < T.length; i++) {
		if (T[i] != U[i]) return false;
	}
	return true;
}