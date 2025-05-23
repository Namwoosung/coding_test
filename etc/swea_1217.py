import sys
sys.stdin = open("/Users/namws/Desktop/ssafycote/input.txt", "r")

T = 10
for test_case in range(1, T+1):
    _ = int(input())
    board = [list(input()) for _ in range(100)]
    board_tc = list(map(list, zip(*board)))


    result = 0
    is_found = False
    for length in range(100, 0, -1):

        if is_found:
            break

        for i in range(100):
            if is_found:
                break
            for j in range(0, 100 - length + 1):
                code = board[i][j:j + length]
                code_tc = board_tc[i][j:j + length]

                if code == code[::-1] or code_tc == code_tc[::-1]:
                    is_found = True
                    result = length
                    break

    print(f"#{_} {result}")


