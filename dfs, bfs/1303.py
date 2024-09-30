import sys

input = sys.stdin.readline

n, m = map(int, input().split())

soldier = list(list(input().rstrip()) for _ in range(m))
check = list([0] * n for _ in range(m))

def check_same(i, j, num, stand):
    check[i][j] = 1

    if i + 1 < m and soldier[i + 1][j] == stand and check[i+1][j] != 1:
        num += 1
        num = check_same(i+1, j, num, stand)
    
    if j + 1 < n and soldier[i][j + 1] == stand and check[i][j+1] != 1:
        num += 1
        num = check_same(i, j+1, num, stand)

    if i - 1 >=0 and soldier[i-1][j] == stand and check[i-1][j] != 1:
        num += 1
        num = check_same(i-1, j, num, stand)

    if j - 1 >= 0 and soldier[i][j-1] == stand and check[i][j-1] != 1:
        num += 1
        num = check_same(i, j-1, num, stand)


    return num


W, B = 0, 0
for i in range(m):
    for j in range(n):
        if check[i][j] == 1:
            continue

        stand = soldier[i][j]
        num = 1

        num = check_same(i, j, num, stand)
        if stand == 'W':
            W += num**2
        else:
            B += num**2

print(W, B)





