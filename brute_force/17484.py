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