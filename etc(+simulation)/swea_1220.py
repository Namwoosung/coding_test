
T = 10
for test_case in range(1, 1 + T):
    N = int(input())
    board = [list(map(int, input().split())) for _ in range(N)]

    result = 0
    for i in range(N):
        trig = False
        for j in range(N):

            if board[j][i] == 1:
                trig = True
            
            if board[j][i] == 2 and trig:
                result += 1
                trig = False
    print(f"#{test_case} {result}")