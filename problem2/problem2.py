# Problem 2

F = [1, 2]
result = 2
while F[-1] < 4000001:
    F.append(sum(F[-2:]))
    if F[-1] % 2 == 0:
        result += F[-1]

print('The solution is %d' % result)
    

    
