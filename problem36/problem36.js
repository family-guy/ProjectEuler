/* Problem 36
 * isPalindrome: takes an integer and returns whether it is a palindrome
 * convertIntToArray: takes a positive integer and returns its value in the base given e.g. convertIntToArray(123, 10) = [1, 2, 3]
*/

var N = 1000000; var sum = 0;
for (var i = 1; i < N; i++) {
	if (isPalindrome(i) && convertIntToArray(i, 2).toString() === convertIntToArray(i, 2).reverse().toString()) sum += i;
}
console.log("The solution is " + sum);

function isPalindrome(n) {
	var nAsStr = n.toString();
	return (nAsStr === nAsStr.split("").reverse().join(""));
}

function convertIntToArray(n, b) {
	var result = []; var divisor = 1; var dividend = n;
	while (true) {
		if (divisor * b > n) break;
		divisor *= b; 
	}
	while (divisor > 0) {
		result.push(Math.floor(dividend / divisor));
		dividend %= divisor;
		divisor = Math.floor(divisor / b);
	}
	return result;
}