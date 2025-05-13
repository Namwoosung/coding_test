T = 10
for test_case in range(1, T + 1):
    tset_num = int(input())
    board = [list(map(int, input().split())) for _ in range(100)]

    dest = 0
    for i in range(100):
        if board[99][i] == 2:
            dest = i
    
    dx = [-1, 0, 0]
    dy = [0, -1, 1]
    dir = 0

    x, y  = 99, dest
    while x >= 0:
        if dir == 0:
            left = y - 1
            right = y + 1

            while x >= 0:
                if left >=0 and board[x][left] == 1:
                    dir = 1
                    y = left
                    break
                elif right < 100 and board[x][right] == 1:
                    dir = 2
                    y = right
                    break
                else:
                    x-=1
            
        elif dir == 1:
            up = x - 1

            while y >=0:
                if up >=0 and board[up][y] == 1:
                    dir = 0
                    x = up
                    break
                else:
                    y -= 1

        elif dir == 2:
            up = x - 1

            while y < 100:
                if up >= 0 and board[up][y] == 1:
                    dir = 0
                    x = up
                    break
                else:
                    y += 1

    print(f"#{test_case} {y}")



""" 전체 동작 흐름은 동일한데, 분기 처리를 더 효율적으로 개선 및 가독성 향상
T = 10
for test_case in range(1, T + 1):
    _ = int(input())
    board = [list(map(int, input().split())) for _ in range(100)]

    # 목적지 위치 (2) 찾기
    y = board[99].index(2)
    x = 99

    # 방향: 왼쪽(-1), 오른쪽(1), 위쪽(0)
    direction = 0

    while x > 0:
        # 좌측에 길이 있고, 방금 오른쪽에서 온 게 아니라면
        if y > 0 and board[x][y - 1] == 1 and direction != 1:
            y -= 1
            direction = -1  # 왼쪽

        # 우측에 길이 있고, 방금 왼쪽에서 온 게 아니라면
        elif y < 99 and board[x][y + 1] == 1 and direction != -1:
            y += 1
            direction = 1  # 오른쪽

        else:
            x -= 1
            direction = 0  # 위로 이동

    print(f"#{test_case} {y}")
"""