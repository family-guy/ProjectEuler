from pyprimes import next_prime
# Problem 10

current_p = 2
sum = 2
while (True):
    next_p = next_prime(current_p)
    if next_p >= 2000000:
        print('The solution is %d' % sum)
        break
    sum += next_p
    current_p = next_p