#1018
#체스판 다시 칠하기

#brute-force로 완전탐색, 모든 8 x 8크기의 체스판을 고려
n, m = map(int, input().split())

#set board
board = list()
for i in range(n):
    line = input()
    board.append(line)

#check board
result_a, result_b = 0, 0 #a와 b는 각각 색깔이 다른 경우
result = n * m
for i in range(n - 7):
    for j in range(m - 7):
        result_a, result_b = 0, 0

        for a in range(i, i + 8):
            for b in range(j, j + 8):
                if ((a + b) % 2) == 0:
                    if board[a][b] == 'B':
                        result_a += 1
                    else:
                        result_b += 1
                else:
                    if board[a][b] == 'W':
                        result_a += 1
                    else:
                        result_b += 1

        if result_a < result:
            result = result_a
        if result_b < result:
            result = result_b    

print(result)
