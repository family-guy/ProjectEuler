/* Problem 2
 * fib: returns the n-th term of Fibonacci's sequence
*/

var sum = 0;
for (var i = 1; fib(i) < 4000001; i++) {
	if (fib(i) % 2 == 0)
	sum += fib(i);
}
console.log("The solution is ", sum);

function fib(n) {
	if (n == 1) return 1;
	if (n == 2) return 2;
	else return fib(n - 1) + fib(n - 2);
}

