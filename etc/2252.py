#fail

import sys
input = sys.stdin.readline

n, m = map(int, input().split())
list = [i for i in range(1, n+1)]

for i in range(m):
    a, b = map(int, input().split())

    indexA, indexB = -1, -1

    for index in range(n):
        if a == list[index]:
            if indexB == -1:
                break
            indexA = index
            break
        if b == list[index]:
            indexB = index
        
    if indexB == -1:
        continue

    list[indexA], list[indexB] = list[indexB], list[indexA]


print(*list)
