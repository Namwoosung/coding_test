#1343
#폴리오미노

#my code
#앞에서 부터 .이 나올 때까지 X의 개수를 세어서 치환해 줌
#홀수일 경우 -1을 return하도록 함
string = input()
num = 0
result = ""

for i in range(len(string)):
    if string[i] == 'X':
        num += 1
    else:
        if num % 2 == 1:
            result = -1
            break
        else:
            while num >= 4:
                result += "AAAA"
                num -= 4
            if num == 2:
                result += "BB"
                num = 0
            result += "."

if num % 2 == 1:
    result = -1
else:
    while num >= 4:
        result += "AAAA"
        num -= 4
    if num == 2:
        result += "BB"
        num = 0

print(result)


"""
비슷하게 greedy algorith으로 풀었지만 더 깔끔한 코드
board = input()

idx = 0
newboard = ''

while idx<len(board):
    if board[idx:idx+4]=='XXXX':
        newboard += 'AAAA'
        idx += 4
    elif board[idx:idx+2]=='XX':
        newboard +='BB'
        idx += 2
    elif board[idx]=='X':
        newboard = -1
        break
    else :
        newboard += board[idx]
        idx += 1

print(newboard)
"""



"""replace 함수 활용 방법
board = input()

board = board.replace('XXXX', 'AAAA')
board = board.replace('XX', 'BB')

if 'X' in board: print(-1)
else: print(board)
"""
