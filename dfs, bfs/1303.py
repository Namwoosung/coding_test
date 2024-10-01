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




## 더 효율적인 코드 => BFS, DFS 문제 풀이 시 앞으로 queue나 stack과 같은 자료구조를 활용하기
"""
import sys
from collections import deque

input = sys.stdin.read

def bfs(x, y, mark, team):
    # BFS로 같은 팀의 병사를 모두 탐색
    queue = deque([(x, y)])
    count = 1
    visited[x][y] = True
    
    while queue:
        i, j = queue.popleft()
        
        for dx, dy in [(-1, 0), (1, 0), (0, -1), (0, 1)]:
            ni, nj = i + dx, j + dy
            if 0 <= ni < m and 0 <= nj < n and not visited[ni][nj] and soldier[ni][nj] == team:
                visited[ni][nj] = True
                queue.append((ni, nj))
                count += 1
    
    return count

# 입력 받기
data = input().splitlines()
n, m = map(int, data[0].split())
soldier = [list(row) for row in data[1:]]
visited = [[False] * n for _ in range(m)]

W, B = 0, 0

# 각 칸을 순회하며 BFS 탐색
for i in range(m):
    for j in range(n):
        if not visited[i][j]:
            team = soldier[i][j]
            num = bfs(i, j, visited, team)
            if team == 'W':
                W += num ** 2
            else:
                B += num ** 2

print(W, B)
"""

