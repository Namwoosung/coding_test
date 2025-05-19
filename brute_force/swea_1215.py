T = 10
for test_case in range(1, T+1):
    stand = int(input())
    board = [input() for _ in range(8)]

    result = 0

    for x in range(8):
        for y in range(8):
            if x + stand <= 8:

                code = [board[i][y] for i in range(x , x+stand)]

                if code == code[::-1]:
                    result += 1


            if y + stand <= 8:

                code = board[x][y:y+stand]

                if code == code[::-1]:
                    result += 1

    
    print(f"#{test_case} {result}")