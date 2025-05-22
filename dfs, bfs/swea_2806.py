def dfs(row):
    global result
    for i in range(N):
        now_queen = (row, i)
        is_possible = True

        for item in queens:
            if now_queen[1] == item[1] or abs(now_queen[0] - item[0]) == abs(now_queen[1] - item[1]):
                is_possible = False
                break

        if not is_possible:
            continue

        if row == N-1:
            result += 1
        else:
            queens.append(now_queen)
            dfs(row+1)
            queens.pop()



T = int(input())
for test_case in range(1, T+1):
    N = int(input())

    queens = list()
    result = 0
    dfs(0)

    print(f"#{test_case} {result}")


"""
더 효율적인 풀이
기존 풀이는 현재 queens들을 모두 저장해 가능 여부를 각각의 queens과 대조했다면, 아래 코드는 열, 대각선마다 true, false를 저장해 훨씬 효율적
그러나 대각선 관련 메커니즘을 떠올리는 게 어려울 수 있음
ex) row + col이 동일한 것들은 왼쪽 아래 방향 대각선이 같은 것들을 의미
ex) row - col + N - 1는 오른쪽 아래 방향 대각선이 같은 것들을 의미

def dfs(row):
    global result

    for col in range(N):
        if col_used[col] or diag1_used[row + col] or diag2_used[row - col + N - 1]:
            continue

        if row == N - 1:
            result += 1
        else:
            col_used[col] = diag1_used[row + col] = diag2_used[row - col + N - 1] = True
            dfs(row + 1)
            col_used[col] = diag1_used[row + col] = diag2_used[row - col + N - 1] = False


T = int(input())
for test_case in range(1, T + 1):
    N = int(input())
    result = 0

    col_used = [False] * N
    diag1_used = [False] * (2 * N - 1)  # ↙ 대각선 (row + col)
    diag2_used = [False] * (2 * N - 1)  # ↘ 대각선 (row - col + N - 1)

    dfs(0)
    print(f"#{test_case} {result}")

"""