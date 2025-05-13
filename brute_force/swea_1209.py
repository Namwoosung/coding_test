import sys
sys.stdin = open("/Users/namws/Desktop/coding_test/all_code/input.txt", "r")

T = 10
for test_case in range(1, T + 1):
    _ = int(input())

    arr = [list(map(int, input().split())) for _ in range(100)]

    max = 0
    diag = 0
    for x in range(100):
        now = 0
        for y in range(100):
            now += arr[x][y]

            if x == y:
                diag += arr[x][y]

        if now > max:
            max = now
    
    for y in range(100):
        now = 0
        for x in range(100):
            now += arr[x][y]
        if now > max:
            max = now
    
    if diag > max:
        max = diag
    
    print(f"#{test_case} {max}")


"""
훨씬 효율적인 풀이
기존 풀이 문제: 왼쪽 대각선 체크 없음 <- 틀려야 되는데, 테스트 케이스 중에 왼쪽 대각선이 답인 테스트가 없는 듯
합을 sum 내장함수를 사용해 훨씬 효과적으로 처리
max 또한 일일이 비교하는 게 아니라 max 내장함수로 효과적으로 처리
T = 10
for test_case in range(1, T + 1):
    _ = int(input())
    arr = [list(map(int, input().split())) for _ in range(100)]

    max_sum = 0

    left_diag = 0
    right_diag = 0

    for i in range(100):
        row_sum = sum(arr[i])
        col_sum = sum(arr[j][i] for j in range(100))

        left_diag += arr[i][i]
        right_diag += arr[i][99 - i]

        max_sum = max(max_sum, row_sum, col_sum)

    max_sum = max(max_sum, left_diag, right_diag)

    print(f"#{test_case} {max_sum}")
"""