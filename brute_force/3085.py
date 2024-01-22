#3085
#사탕 게임
#brute-force
#-> 완전탐색으로 가능한 모든 사탕을 바꾸고, 바꾼 다음 전체 board를 다시 검사해 가장 긴 열이나 행을 탐색

N = int(input())
result = 0

def CheckBoard(board):
    global result, N

    for i in range(N):
        for j in range(N):
            #check low
            cnt = 1
            while (i+cnt) < N:
                if board[i][j] == board[i+cnt][j]:
                    cnt += 1
                else:
                    break
            if cnt > result:
                result = cnt

            #check col
            cnt = 1                
            while(j+cnt) < N:
                if board[i][j] == board[i][j+cnt]:
                    cnt += 1
                else:
                    break
            if cnt > result:
                result = cnt            

#set board
board = list()
for i in range(N):
    board.append(list(input()))

#check
for i in range(N):
    for j in range(N):
        if i != (N-1):
            if board[i][j] != board[i+1][j]:
                board[i][j], board[i+1][j] = board[i+1][j], board[i][j]
                CheckBoard(board)
                board[i][j], board[i+1][j] = board[i+1][j], board[i][j]

        if j != (N-1):
            if board[i][j] != board[i][j+1]:
                board[i][j], board[i][j+1] = board[i][j+1], board[i][j]
                CheckBoard(board)
                board[i][j], board[i][j+1] = board[i][j+1], board[i][j]     
                  
print(result)