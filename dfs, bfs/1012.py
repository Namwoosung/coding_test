import sys
from collections import deque

input = sys.stdin.readline

t = int(input())

for i in range(t):
    col, row, k = map(int, input().split())
    table = [[0] * col for _ in range(row)]
    count = 0

    for i in range(k):
        x, y = map(int, input().split())
        table[y][x] = 1

    queue = deque()

    for i in range(row):
        for j in range(col):
            if(table[i][j] == 1):
                count += 1
                table[i][j] = 0
                queue.append((i,j))
            
            while queue:
                ni, nj = queue.popleft()

                for dx, dy in [(-1, 0), (0, 1), (1, 0), (0, -1)]:
                    ai, aj = ni + dx, nj + dy
                    if 0 <= ai < row and 0 <= aj < col and table[ai][aj] == 1:
                        table[ai][aj] = 0
                        queue.append((ai,aj))

    print(count)