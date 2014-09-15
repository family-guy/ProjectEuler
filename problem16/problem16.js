/* Problem 16
 * multiplication: takes an array of numbers, multiplies by an integer and returns the result in a new array e.g. multiplication([1, 2, 3], 10) returns [1, 2, 3, 0]
 * sumOfArray: returns the sum of the numbers in an array
*/

var array = [1];
for (var i = 0; i < 1000; i++) {
	var array = multiplication(array, 2);
}
console.log("The solution is ", sumOfArray(array));

function multiplication(array, n) {
	var result = []; var carryOver = 0; var p = 0;
	for (var i = 0; i < array.length - 1; i++) {
		p = array[array.length - 1 - i] * n + carryOver; 
		result.push(p % 10); carryOver = Math.floor(p / 10); 
	}
	p = array[0] * n + carryOver;
	while (true) {
		result.push(p % 10);
		if (Math.floor(p / 10) == 0) break;
		else p = Math.floor(p / 10);
	}
	return result.reverse();
}

function sumOfArray(array) {
	var sum = 0;
	for (var i = 0; i < array.length; i++) {
		sum += array[i];
	}
	return sum;
}

