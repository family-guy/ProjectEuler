# Problem 9

def couples(n, c):
    return list((a, b) for a in range(1, (n // 2) + 1) for b in range(1, n) if a < b and a + b == n and b < c)

for c in range(1000 // 3, 1000):
    solution_found = False
    for (a, b) in couples(1000 - c, c):
        if a ** 2 + b ** 2 == c ** 2:
            print('The solution is %d' % (a * b * c))
            solution_found = True
            break
            
    if solution_found:
        break
            
    
    
            
    
    