T = int(input())

for test_case in range(1, T+1):
    n = int(input())
    board = [list(map(int, input().strip())) for _ in range(n)]

    result = 0
    mid = n // 2
    for i in range(n):
        row = board[i]
        valid = row[abs(i - mid): n - abs(i - mid)]
        result += sum(valid)
    
    print(f"#{test_case} {result}")