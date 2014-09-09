// Problem 28

var lengthOfSpiral = 1001; var sum = 1; 
var currentTerm = 1; // current term of the sequence 1, 3, 5, 7, 9, 13, 17, 21, 25, ...
for (var currentLength = 3; currentLength <= lengthOfSpiral; currentLength += 2) {
	for (var i = 0; i < 4; i++) {
		currentTerm += currentLength - 1;
		sum += currentTerm;
	}
}
console.log("The solution is ", sum);