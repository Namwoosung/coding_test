#17484
#진우의 달 여행(small)

#my code
#완전탐색, 각 위치에서 왼쪽으로 가는 함수, 아래로 가는 함수, 오른쪽으로 가는 함수를 각각 호출해 모든 경우를 list에 넣고 최소값을 출력
n, m = map(int, input().split())

space = list()
for i in range(n):
    line = list(map(int, input().split()))
    space.append(line)

def MoveLeft(i, j, now):
    if i == (n - 1):
        return now
    cost = now + space[i + 1][j - 1]
    result = MoveDown(i + 1, j - 1, cost)

    if (j-1) < (m-1):
        temp = MoveRight(i + 1, j - 1, cost)
        if result > temp:
            result = temp

    return result

def MoveDown(i, j, now):
    if i == (n - 1):
        return now
    cost = now + space[i + 1][j]

    if j > 0 and j < (m-1):
        a = MoveLeft(i + 1, j, cost)
        b = MoveRight(i + 1, j, cost)
        if a > b:
            result = b
        else:
            result = a
    elif j == 0:
        result = MoveRight(i + 1, j, cost)
    elif j == (m-1):
        result = MoveLeft(i + 1, j, cost)

    return result 

def MoveRight(i, j, now):
    if i == (n - 1):
        return now
    cost = now + space[i + 1][j + 1]
    result = MoveDown(i + 1, j + 1, cost)

    if (j+1) > 0:
        temp = MoveLeft(i + 1, j + 1, cost)
        if result > temp:
            result = temp

    return result
    
results = list()
for i in range(m):
    if i != 0:
        results.append(MoveLeft(0, i, space[0][i]))
    results.append(MoveDown(0, i, space[0][i]))
    if i != (m-1):
        results.append(MoveRight(0, i, space[0][i]))

print(min(results))



"""
더 깔끔한 코드 dfs로 풀이
컨셉은 동일한데 더 간결한 코드
import sys

n, m = map(int, sys.stdin.readline().split(' '))
square = []
for i in range(n):
    square.append(list(map(int, sys.stdin.readline().split(' '))))

direction = [-1, 0, 1]

def dfs(col, row, d, low, answer):
    if col == n-1:
        return min(low, answer)
    for i in direction:
        if i != d:
            if 0 <= col < n and 0 <= row + i < m:
                low = dfs(col+1, row+i, i, low, answer + square[col+1][row+i])
    return low

low = int(sys.maxsize)
for i in range(m):
    low = min(dfs(0, i, 2, low, square[0][i]), low)

print(low)
"""



"""
여기는 dp를 활용해서 풀이한 경우
import sys
input = sys.stdin.readline

N, M = map(int,input().split())
fuel_list = [list(map(int,input().split())) for _ in range(N)]
min_fuel_list = [[[float('inf')] * 3 for _ in range(M)] for _ in range(N)]

for i in range(M):
    min_fuel_list[0][i] = [fuel_list[0][i],fuel_list[0][i],fuel_list[0][i]]

for i in range(1,N):
    for j in range(M):
        for k in range(3):
            if (j == 0 and k == 2) or (j==M-1 and k == 0):
                continue
            for l in range(3):
                if k == l :
                    continue
                min_fuel_list[i][j][k] = min(min_fuel_list[i][j][k], min_fuel_list[i-1][j-k+1][l] + fuel_list[i][j])

song = 1e9
for i in range(M):
    song = min(min(min_fuel_list[N-1][i]), song)
print(song)
"""