import sys
input = sys.stdin.readline

board = list(list(map(int, input().split())) for _ in range(19))

def check_right(i, j):
    if i > 14:
        return False
    
    global board
    count = 0
    color = board[i][j]
    while(i <= 18 and board[i][j] == color):
        count += 1
        i += 1
    
    if(count == 5):
        return True
    else:
        return False
    

def check_down(i, j):
    if j> 14:
        return False
    
    global board
    count = 0
    color = board[i][j]
    while(j <= 18 and board[i][j] == color):
        count += 1
        j += 1
    
    if(count == 5):
        return True
    else:
        return False
    

def check_rightdown(i, j):
    if j > 14 or i > 14:
        return False
    
    global board
    count = 0
    color = board[i][j]
    while(i <= 18 and board[i][j] == color):
        count += 1
        i += 1
        j += 1
    
    if(count == 5):
        return True
    else:
        return False
    

def check_rightup(i, j):
    if j > 14 or i < 4:
        return False
    
    global board
    count = 0
    color = board[i][j]
    while(i >= 0 and j <= 18 and board[i][j] == color):
        count += 1
        i -= 1
        j += 1
    
    if(count == 5):
        return True
    else:
        return False


is_end = False
for i in range(19):
    for j in range(19):
        if board[i][j] == 0:
            continue
        if not (i > 0 and board[i][j] == board[i-1][j]):
            if check_right(i, j):
                is_end = True
                print(board[i][j])
                print(i + 1, j + 1)
                break

        if not (j > 0 and board[i][j] == board[i][j-1]):
            if check_down(i, j):
                is_end = True
                print(board[i][j])
                print(i + 1, j + 1)
                break

        if not (i > 0 and j > 0 and board[i][j] == board[i-1][j-1]):
            if check_rightdown(i, j):
                is_end = True
                print(board[i][j])
                print(i + 1, j + 1)
                break

        if not(i < 18 and j > 0 and board[i][j] == board[i+1][j-1]):
            if check_rightup(i, j):
                is_end = True
                print(board[i][j])
                print(i + 1, j + 1)
                break

        if is_end: break
    if is_end: break

if not is_end:
    print(0)




"""
좀 더 효율적인 코드
동작방식은 동일하지만, 함수 4개가 거의 유사하므로 direction으로 동일하게 처리
다음과 같이 board에서 좌표 기반으로 풀이할 때는 direction을 만드는 것이 더 좋아보임
import sys
input = sys.stdin.readline

# 보드 입력
board = [list(map(int, input().split())) for _ in range(19)]

# 방향 이동: 오른쪽, 아래, 오른쪽 아래 대각선, 오른쪽 위 대각선
directions = [(1, 0), (0, 1), (1, 1), (-1, 1)]

def check_five_in_a_row(i, j, di, dj):
    color = board[i][j]
    count = 1

    # 현재 방향으로 5칸 연속 체크
    for _ in range(4):
        i += di
        j += dj
        if not (0 <= i < 19 and 0 <= j < 19) or board[i][j] != color:
            return False

        count += 1

    # 여섯 번째 칸이 같은 색이면 불필요한 5개 연속이므로 체크하지 않음
    ni, nj = i + di, j + dj
    if 0 <= ni < 19 and 0 <= nj < 19 and board[ni][nj] == color:
        return False

    # 반대 방향도 체크해 여섯 개 이상이 아닌지 확인
    ni, nj = i - 5 * di, j - 5 * dj
    if 0 <= ni < 19 and 0 <= nj < 19 and board[ni][nj] == color:
        return False

    return count == 5

is_end = False
for i in range(19):
    for j in range(19):
        if board[i][j] == 0:
            continue

        for di, dj in directions:
            if check_five_in_a_row(i, j, di, dj):
                print(board[i][j])
                print(i + 1, j + 1)
                is_end = True
                break

        if is_end:
            break
    if is_end:
        break

if not is_end:
    print(0)

"""