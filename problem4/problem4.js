/* Problem 4
 * reverseString: reverses a string
 * isPalindrome: returns whether an integer is a palindrome
*/

function reverseString (str) {
	var result = "";
	for (var i = 0; i < str.length; i++) {
		result += str.charAt(str.length - 1 - i);
	}
	return result;
}

function isPalindrome(n) {
	var numAsStr = n.toString();
	if (numAsStr == reverseString(numAsStr)) return true;
	return false;
}

var maxValue, maxIndex1, maxIndex2; maxValue = 0; maxIndex1 = 0; maxIndex2 = 0;
for (var i = 100; i < 1000; i++) {
	for (var j = 100; j < 1000; j++) {
		if (isPalindrome(i * j) && i * j > maxValue) {
			maxValue = i * j; maxIndex1 = i; maxIndex2 = j;
		}
	}
}
console.log("The solution is " + maxValue + " = " + maxIndex1 + " x " + maxIndex2);
