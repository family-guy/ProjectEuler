# Problem 4

largest_pal = 10201 # 101 * 101
for i in range(100, 1000):
    for j in range(100, 1000):
        prod = i * j
        prod_as_str = str(prod)
        if int(prod_as_str[::-1]) == prod:
            if prod > largest_pal:
                largest_pal = prod
                
print('The solution is %d' % largest_pal)
            
        
    

            
        
    
