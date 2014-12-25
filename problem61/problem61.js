/**
 * Problem 60
 */

var figurates = [];
var triangle = [];
var square = [];
var pentagonal = [];
var hexagonal = [];
var heptagonal = [];
var octagonal = [];
var i = 1;
while (true) {
	var ithTermTri = i * (i + 1) / 2;
	var ithTermSq = i * i;
	var ithTermPent = i * (3 * i - 1) / 2;
	var ithTermHex = i * (2 * i - 1);
	var ithTermHept = i * (5 * i - 3) / 2;
	var ithTermOct = i * (3 * i - 2);
	if (ithTermTri >= 10000) break;
	if (ithTermTri >= 1000 && ithTermTri < 10000) triangle.push(ithTermTri);
	if (ithTermSq >= 1000 && ithTermSq < 10000) square.push(ithTermSq);
	if (ithTermPent >= 1000 && ithTermPent < 10000) pentagonal.push(ithTermPent);
	if (ithTermHex >= 1000 && ithTermHex < 10000) hexagonal.push(ithTermHex);
	if (ithTermHept >= 1000 && ithTermHept < 10000) heptagonal.push(ithTermHept);
	if (ithTermOct >= 1000 && ithTermOct < 10000) octagonal.push(ithTermOct);
	i++;
}
figurates.push(triangle); figurates.push(square); figurates.push(pentagonal); figurates.push(hexagonal); figurates.push(heptagonal); figurates.push(octagonal);
var unorderedSet = [];
var nbFigurates = 6;
var states = [];
for (var i = 0; i < nbFigurates; i++) {
	states.push(false);
}
solve(unorderedSet, states, figurates, nbFigurates);

/**
* Standard recursive backtracking function
* @param array of integers unorderedSet
* @param array of booleans states
* @param two dimensional array of integers T
* @param integer nbFigurates
* @return boolean
*/
function solve(unorderedSet, states, T, nbFigurates) {
	if (unorderedSet.length == nbFigurates) {
		var lastTwoDigitsLastElt = unorderedSet[nbFigurates - 1].toString().substring(2);
		var firstTwoDigitsFirstElt = unorderedSet[0].toString().substring(0, 2);
		if (lastTwoDigitsLastElt == firstTwoDigitsFirstElt) {
			console.log('The ordered set of six cyclic 4-digit numbers is ' + unorderedSet);
			var sum = 0;
			for (var i = 0; i < unorderedSet.length; i++) {
				sum += unorderedSet[i];
			}
			console.log('The solution is ' + sum);
			return true;
		}
	}
	for (var i = 0; i < states.length; i++) {
		if (!states[i]) {
			for (var j = 0; j < T[i].length; j++) {
				if (unorderedSet.length == 0) {
					unorderedSet.push(T[i][j]);
					states[i] = true;
					if (solve(unorderedSet, states, T, nbFigurates)) return true;
					unorderedSet.splice(unorderedSet.length - 1, 1);
					states[i] = false;
				}
				else {
					var a = unorderedSet[unorderedSet.length - 1];
					var lastTwoDigitsA = a.toString().substring(2);
					var b = T[i][j];
					var firstTwoDigitsB = b.toString().substring(0, 2);
					if (lastTwoDigitsA == firstTwoDigitsB) {
						unorderedSet.push(b);
						states[i] = true;
						if (solve(unorderedSet, states, T, nbFigurates)) return true;
						unorderedSet.splice(unorderedSet.length - 1, 1);
						states[i] = false;
					}
				}
			}
		}
	}
	return false;
}