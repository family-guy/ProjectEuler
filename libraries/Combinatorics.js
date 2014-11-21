/*
 * This module contains combinatorics methods for strings and integers
 * insertCharAt: takes a string and inserts a character (string of length 1) at the given index
 * removeCharAt: takes a string and removes the character at the given index
 * allNewStrings1: takes a string (or an array of strings) and a character and returns all new strings that can be made by adding that character
 * allNewStrings2: takes an array of strings and a character and returns all new strings that can be made by adding that character
 * fact: returns the factorial of a non-negative integer
 * nChooseK: returns the value of C^k_n
 * isPerm: takes two positive integers and returns whether they are permutations of each other
 * permutationsOfAString: returns all permutations of a string
 * showStrings: prints an array of strings
*/

function insertCharAt(str, ch, i) {
	if (i === 0) return ch + str;
	if (i === str.length) return str + ch;
	else return str.substring(0, i) + ch + str.substring(i, str.length);
}

function removeCharAt(str, i) {
	      return str.substring(0, i) + str.substring(i + 1);
	  }
	
function allNewStrings1(str, ch) {
		var result = Array(str.length + 1);
		for (var i = 0; i < str.length + 1; i++) {
			result[i] = insertCharAt(str, ch, i);
		}
		return result;
	}
	
function allNewStrings2(T, ch) {
		var p = T.length; var q = T[0].length + 1;
		var result = Array(p * q); var j = -1;
		for (var i = 0; i < p * q; i++) {
			if (i % q == 0) j++;
			result[i] = allNewStrings1(T[j], ch)[i % q];			
		}
		return result;
	}

function fact(n) {
	if (n === 0) return 1;
	var result = 1;
	for (var i = 1; i <= n; i++) {
		result *= i;
	}
	return result;
}

function nChooseK(k, n) {
	if (k === 0) return 1;
	var result = 1;
	for (var i = 1; i < k + 1; i++) {
		result *= n - (k - i);
		result = Math.floor(result / i);
	}
	return result;
}

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

function permutationsOfAString(str) {
		if (str.length === 2) {
			var result = Array(2);
			result[0] = str;
			result[1] = str.substring(1) + str.substring(0, 1);
			return result;
		}
		return allNewStrings2(permutationsOfAString(removeCharAt(str, str.length - 1)), str.substring(str.length - 1));
	}
	
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
	
exports.allNewStrings2 = function(T, ch) {
		var p = T.length; var q = T[0].length + 1;
		var result = Array(p * q); var j = -1;
		for (var i = 0; i < p * q; i++) {
			if (i % q === 0) j++;
			result[i] = allNewStrings1(T[j], ch)[i % q];			
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

	

	