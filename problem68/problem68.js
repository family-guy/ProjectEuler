/**
 * Problem 68
 */

var max = '0';
var n = 5; // n-gon ring
// Get couples
couples = [];
for (var i = 1; i < 2 * n + 1; i++) {
	for (var j = i + 1; j < 2 * n + 1; j++) {
		var coupleOne = [];
		var coupleTwo = [];
		coupleOne.push(i); coupleOne.push(j);
		coupleTwo.push(j); coupleTwo.push(i);
		couples.push(coupleOne); couples.push(coupleTwo);
	}
}
legal = [];
for (var i = 0; i < couples.length; i++) {
	legal.push(true);
}
var solution = [];
solve(solution, couples, legal, n);
console.log('The solution is ' + max);

/**
* Returns a solution set given a solution expressed in terms of indices of elements in an array of couples
* @param array of integers solution, two dimensional array of integers couples
* @return two dimensional array of integers
*/

function solutionSet(solution, couples) {
	var result = []; 
	for (var i = 0; i < solution.length; i++) {
		var row = [];
		for (var j = 0; j < 3; j++) {
			row.push(0);
		}
		result.push(row);
	}
	for (var i = 0; i < solution.length; i++) {
		for (var j = 0; j < couples[solution[i]].length; j++) {
			result[i][j] = couples[solution[i]][j];
		}
		result[i][couples[i].length] = couples[solution[(i + 1) % solution.length]][1];
	}
	return result;
}

/**
* Returns if a > b where a and b are integers represented as strings
* @param string a, string b
* @return boolean
*/
function greaterThan(a, b) {
	if (a.length != b.length) return a.length > b.length;
	for (var i = 0; i < a.length; i++) {
		var digitA = parseInt(a.substring(i, i + 1));
		var digitB = parseInt(b.substring(i, i + 1));
		if (digitA != digitB) return digitA > digitB;
	}
	return false;
}

/**
* Converts a solution set into a string
* @param two dimensional array of integers solutionSet
* @return string
*/
function solutionSetAsStr(solutionSet) {
	var result = '';
	for (var i = 0; i < solutionSet.length; i++) {
		for (var j = 0; j < solutionSet[i].length; j++) {
			result += solutionSet[i][j].toString();
		}
	}
	return result;
}

/**
* Recursive backtracking solver
* @param array of integers solution, two dimensional array of integers couples, array of booleans legal, integer n
* @return void
*/
function solve(solution, couples, legal, n) {
	if (solution.length == n) {
		var total = couples[solution[0]][0] + couples[solution[0]][1] + couples[solution[1]][1];
		if (couples[solution[solution.length - 1]][0] + couples[solution[solution.length - 1]][1] + couples[solution[0]][1] == total) {
			var solSet = solutionSet(solution, couples);
			var solSetAsStr = solutionSetAsStr(solSet);
			if (greaterThan(solSetAsStr, max) && solSetAsStr.length == 16) max = solSetAsStr;
			return;
		}
	}
	else {
		if (solution.length == 0) {
			for (var i = 0; i < couples.length; i++) {
				solution.push(i);
				var sievedLegal = sieveCouples2(couples[i][0], couples, sieveCouples1(couples[i][0], couples[i][1], couples, legal));
				solve(copy(solution), couples, sievedLegal, n);
				solution.splice(solution.length - 1, 1);
			}
		}
		if (solution.length == 1) {
			for (var i = 0; i < couples.length; i++) {
				if (legal[i]) {
					if (couples[i][0] < couples[solution[0]][0] + couples[solution[0]][1]) {
						solution.push(i);
						var sievedLegal = sieveCouples1(couples[i][0], couples[i][1], couples, legal);
						solve(copy(solution), couples, sievedLegal, n);
						solution.splice(solution.length - 1, 1);
					}
				}
			}
		}
		if (solution.length > 1) {
			for (var i = 0; i < couples.length; i++) {
				if (legal[i]) {
					var total = couples[solution[0]][0] + couples[solution[0]][1] + couples[solution[1]][1];
					if (couples[i][1] + couples[solution[solution.length - 1]][0] + couples[solution[solution.length - 1]][1] == total) {
						solution.push(i);
						var sievedLegal = sieveCouples1(couples[i][0], couples[i][1], couples, legal);
						solve(copy(solution), couples, sievedLegal, n);
						solution.splice(solution.length - 1, 1);
					}
				}
			}
		}
	}
}

/**
* Returns an updated array of booleans
* @param integer a, integer b, two dimensional array of integers couples, array of booleans T
* @return array of booleans
*/
function sieveCouples1(a, b, couples, T) {
	var result = [];
	for (var i = 0; i < T.length; i++) {
		if (couples[i].indexOf(a) > -1 || couples[i].indexOf(b) > -1) result.push(false);
		else result.push(T[i]);
	}
	return result;
}

/**
* Returns an updated array of booleans
* @param integer a, two dimensional array of integers couples, array of booleans T
* @return array of booleans
*/
function sieveCouples2(a, couples, T) {
	var result = [];
	for (var i = 0; i < couples.length; i++) {
		if (couples[i][0] <= a) result.push(false);
		else result.push(T[i]);
	}
	return result;
}

/**
* Deep copies of a dynamic array
* @param array of integers T
* @return array of integers 
*/
function copy(T) {
	var result = [];
	for (var i = 0; i < T.length; i++) {
		result.push(T[i]);
	}
	return result;
}