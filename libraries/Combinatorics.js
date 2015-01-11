/**
 * This module contains combinatorics methods for strings and integers
 */

// Auxiliary functions

/**
* Inserts a character into a string
* @param string str, string ch (of length 1), integer i
* @return string
*/
function insertCharAt(str, ch, i) {
	if (i === 0) return ch + str;
	if (i === str.length) return str + ch;
	else return str.substring(0, i) + ch + str.substring(i, str.length);
}

/**
* Removes a character from a string
* @param string str, integer i
* @return string
*/
function removeCharAt(str, i) {
	      return str.substring(0, i) + str.substring(i + 1);
	  }

/**
* Gets new strings formed by adding one character to an existing string
* @param string str, string ch (of length 1)
* @return array of strings
*/	
function allNewStrings1(str, ch) {
		var result = Array(str.length + 1);
		for (var i = 0; i < str.length + 1; i++) {
			result[i] = insertCharAt(str, ch, i);
		}
		return result;
	}

/**
* Gets new strings formed by adding one character to an existing array of strings
* @param array of strings strings, string ch (of length 1)
* @return array of strings
*/	
function allNewStrings2(strings, ch) {
		var p = strings.length; var q = strings[0].length + 1;
		var result = Array(p * q); var j = -1;
		for (var i = 0; i < p * q; i++) {
			if (i % q == 0) j++;
			result[i] = allNewStrings1(strings[j], ch)[i % q];			
		}
		return result;
	}
	
// Functions

/**
* Suppose that we enumerate all k-combinations of a set S of n elements in lexicographic order e.g. if S = {1, 2, 3, 4, 5}, k = 3, then we have {1, 2, 3}, {1, 	2, 4}, {1, 2, 5}, {1, 3, 4}, {1, 3, 5}, {1, 4, 5}, {2, 3, 4}, {2, 3, 5}, {2, 4, 5}, {3, 4, 5}. Given one of these k-combinations, returns the next k-combination e.g. next({1, 4, 5}, 5) returns {2, 3, 4}
* @param array of integers T, integer n
* @return array of integers
*/
function next(T, n) { 
	var result = [];
	for (var i = 0; i < T.length; i++) {
		result.push(T[i]);
	}
	var i = 0;
	while (T.length - 1 - i > -1) {
		if (T[T.length - 1 - i] < n - i) {
			result[result.length - 1 - i]++;
			for (var j = result.length - i; j < T.length; j++) {
				result[j] = result[result.length - 1 - i] + 1 + (j - (result.length - i));
			}
			break;
		}
		i++;
	}
	return result;
}

/**
* Enumerates all k-combinations of a set containing n elements in lexicographic order e.g. enum_n_choose_k(3, 5) returns {1, 2, 3}, {1, 	2, 4}, {1, 2, 5}, {1, 3, 4}, {1, 3, 5}, {1, 4, 5}, {2, 3, 4}, {2, 3, 5}, {2, 4, 5}, {3, 4, 5}
* @param integer k, integer n
* @return two dimensional array of integers
*/
function enumNchooseK(k, n) {
	var result = [];
	for (var i = 0; i < nChooseK(k, n); i++) {
		var row = [];
		for (var j = 0; j < k; j++) {
			row.push(0);
		}
		result.push(row);
	}
	for (var j = 0; j < result[0].length; j++) {
		result[0][j] = j + 1;
	}
	for (var i = 1; i < result.length; i++) {
		var nextEnum = next(result[i - 1], n);
		for (var j = 0; j < result[i].length; j++) {
			result[i][j] = nextEnum[j];
		}
	}
	return result;
}

/**
* Calculates the factorial of a number
* @param integer n
* @return integer
*/
function fact(n) {
	if (n === 0) return 1;
	var result = 1;
	for (var i = 1; i <= n; i++) {
		result *= i;
	}
	return result;
}

/**
* Calculates n choose k
* @param integer k, integer n
* @return integer
*/
function nChooseK(k, n) {
	if (k === 0) return 1;
	var result = 1;
	for (var i = 1; i < k + 1; i++) {
		result *= n - (k - i);
		result = Math.floor(result / i);
	}
	return result;
}

/**
* Checks if two numbers are permutations of one another
* @param integer a, integer b
* @return boolean
*/
function isPerm(a, b) {
	var aAsStr = a.toString(); var bAsStr = b.toString(); 
	if (aAsStr.length !== bAsStr.length) return false;
	var A = []; var B = [];
	for (var i = 0; i < 10; i++) {
		A.push(0); B.push(0);
	}
	for (var i = 0; i < aAsStr.length; i++) {
		A[parseInt(aAsStr.substring(i, i + 1))]++;
		B[parseInt(bAsStr.substring(i, i + 1))]++;
	}
	for (var i = 0; i < A.length; i++) {
		if (A[i] !== B[i]) return false;
	}
	return true;
}

/**
* Gets all permutations of a string
* @param string str
* @return array of strings
*/
function permutationsOfAString(str) {
		if (str.length === 2) {
			var result = Array(2);
			result[0] = str;
			result[1] = str.substring(1) + str.substring(0, 1);
			return result;
		}
		return allNewStrings2(permutationsOfAString(removeCharAt(str, str.length - 1)), str.substring(str.length - 1));
	}

/**
* Prints an array of strings
* @param array of strings T
* @return void
*/	
function showStrings(T) {
	for (var i = 0; i < T.length; i++) {
		console.log(T[i]);
	}
}	

exports.insertCharAt = function(str, ch, i) {
	if (i === 0) return ch + str;
	if (i === str.length) return str + ch;
	else return str.substring(0, i) + ch + str.substring(i, str.length);
}
	
exports.removeCharAt = function(str, i) {
	return str.substring(0, i) + str.substring(i + 1);
}
	
exports.allNewStrings1 = function(str, ch) {
	var result = Array(str.length + 1);
	for (var i = 0; i < str.length + 1; i++) {
		result[i] = insertCharAt(str, ch, i);
	}
	return result;
}
	
exports.allNewStrings2 = function(strings, ch) {
	var p = strings.length; var q = strings[0].length + 1;
	var result = Array(p * q); var j = -1;
	for (var i = 0; i < p * q; i++) {
		if (i % q === 0) j++;
		result[i] = allNewStrings1(strings[j], ch)[i % q];			
	}
	return result;
}

exports.next = function (T, n) {
	var result = [];
	for (var i = 0; i < T.length; i++) {
		result.push(T[i]);
	}
	var i = 0;
	while (T.length - 1 - i > -1) {
		if (T[T.length - 1 - i] < n - i) {
			result[result.length - 1 - i]++;
			for (var j = result.length - i; j < T.length; j++) {
			result[j] = result[result.length - 1 - i] + 1 + (j - (result.length - i));
			}
			break;
		}
		i++;
	}
	return result;
}

exports.enumNchooseK = function (k, n) {
	var result = [];
	for (var i = 0; i < nChooseK(k, n); i++) {
		var row = [];
		for (var j = 0; j < k; j++) {
			row.push(0);
		}
		result.push(row);
	}
	for (var j = 0; j < result[0].length; j++) {
		result[0][j] = j + 1;
	}
	for (var i = 1; i < result.length; i++) {
		var nextEnum = next(result[i - 1], n);
		for (var j = 0; j < result[i].length; j++) {
			result[i][j] = nextEnum[j];
		}
	}
	return result;
}
	
exports.fact = function(n) {
	if (n === 0) return 1;
	var result = 1;
	for (var i = 1; i <= n; i++) {
		result *= i;
	}
	return result;
}

exports.nChooseK = function(k, n) {
	if (k === 0) return 1;
	var result = 1;
	for (var i = 1; i < k + 1; i++) {
		result *= n - (k - i);
		result = Math.floor(result / i);
	}
	return result;
}

exports.isPerm = function(a, b) {
	var aAsStr = a.toString(); var bAsStr = b.toString(); 
	if (aAsStr.length !== bAsStr.length) return false;
	var A = []; var B = [];
	for (var i = 0; i < 10; i++) {
		A.push(0); B.push(0);
	}
	for (var i = 0; i < aAsStr.length; i++) {
		A[parseInt(aAsStr.substring(i, i + 1))]++;
		B[parseInt(bAsStr.substring(i, i + 1))]++;
	}
	for (var i = 0; i < A.length; i++) {
		if (A[i] !== B[i]) return false;
	}
	return true;
}

exports.permutationsOfAString = function(str) {
	if (str.length === 2) {
		var result = Array(2);
		result[0] = str;
		result[1] = str.substring(1) + str.substring(0, 1);
		return result;
	}
	return allNewStrings2(permutationsOfAString(removeCharAt(str, str.length - 1)), str.substring(str.length - 1)); 
}
	
exports.showStrings = function(T) {
	for (var i = 0; i < T.length; i++) {
		console.log(T[i]);
	}
}

	

	