# Problem 85

def nb_rtgles(width, height):
    result = 0
    for i in range(1, height + 1):
        for j in range(1, width + 1):
            result += (width - j + 1) * (height - i + 1)
    return result

n = 2000000
best_diff = n - 1
best_h = 1
best_w = 1

for i in range(1, 2000):
    for j in range(1, i + 1):
        x = nb_rtgles(i, j)
        if x > n: 
            break
        current_diff = abs(x - n)
        if current_diff < best_diff:
            best_h = i
            best_w = j
            best_diff = current_diff
            
print('The solution is %d' % (best_h * best_w))


