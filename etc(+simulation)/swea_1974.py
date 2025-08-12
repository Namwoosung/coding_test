T = int(input())
stand = {1, 2, 3, 4, 5, 6, 7, 8, 9}

for test_case in range(1, T+1):
    board = [list((map(int, input().split()))) for _ in range(9)]
    board_tr = list(map(list, zip(*board)))

    is_right = True

    for i in range(9):
        if set(board[i]) != stand:
            is_right = False
            break
        if set(board_tr[i]) != stand:
            is_right = False
            break

    for i in range(3):
        if not is_right:
            break
        for j in range(3):
            now = set()

            for k in range(3):
                for s in range(3):
                    now.add(board[i*3+k][j*3+s])
            if now != stand:
                is_right = False
                break
    if is_right:
        print(f"#{test_case} 1")
    else:
        print(f"#{test_case} 0")

