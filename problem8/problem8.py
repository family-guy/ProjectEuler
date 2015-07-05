# Problem 8

def product(L):
    result = 1
    for x in L:
        result *= x
        
    return result

nb_adj_digits = 13
file_path = 'Problem8.txt'
file_contents = open(file_path).read()
file_contents_clean = file_contents.replace('\n', '')
greatest = 0

for i in range(0, len(file_contents_clean) - nb_adj_digits + 1):
    current = product(list(int(x) for x in file_contents_clean[i:i + nb_adj_digits]))
    if current > greatest:
        greatest = current
        
print('The solution is %d' % greatest)
    

