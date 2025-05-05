# 기본 제공코드는 임의 수정해도 관계 없습니다. 단, 입출력 포맷 주의
# 아래 표준 입출력 예제 필요시 참고하세요.

# 표준 입력 예제
'''
a = int(input())                        정수형 변수 1개 입력 받는 예제
b, c = map(int, input().split())        정수형 변수 2개 입력 받는 예제 
d = float(input())                      실수형 변수 1개 입력 받는 예제
e, f, g = map(float, input().split())   실수형 변수 3개 입력 받는 예제
h = input()                             문자열 변수 1개 입력 받는 예제
'''

# 표준 출력 예제
'''
a, b = 6, 3
c, d, e = 1.0, 2.5, 3.4
f = "ABC"
print(a)                                정수형 변수 1개 출력하는 예제
print(b, end = " ")                     줄바꿈 하지 않고 정수형 변수와 공백을 출력하는 예제
print(c, d, e)                          실수형 변수 3개 출력하는 예제
print(f)                                문자열 1개 출력하는 예제
'''




'''
아래의 구문은 input.txt 를 read only 형식으로 연 후,
앞으로 표준 입력(키보드) 대신 input.txt 파일로부터 읽어오겠다는 의미의 코드입니다.
여러분이 작성한 코드를 테스트 할 때, 편의를 위해서 input.txt에 입력을 저장한 후,
아래 구문을 이용하면 이후 입력을 수행할 때 표준 입력 대신 파일로부터 입력을 받아올 수 있습니다.
따라서 테스트를 수행할 때에는 아래 주석을 지우고 이 구문을 사용하셔도 좋습니다.
아래 구문을 사용하기 위해서는 import sys가 필요합니다.
단, 채점을 위해 코드를 제출하실 때에는 반드시 아래 구문을 지우거나 주석 처리 하셔야 합니다.
'''
#import sys
#sys.stdin = open("input.txt", "r")

def changeDir(dx, dy):
    if dx == 0 and dy == 1:
        return 1, 0
    if dx==1 and dy ==0:
        return 0, -1
    if dx ==0 and dy == -1:
        return -1, 0
    if dx == -1 and dy == 0:
        return 0, 1

T = int(input())
# 여러개의 테스트 케이스가 주어지므로, 각각을 처리합니다.
for test_case in range(1, T + 1):
    num = int(input())
    
    arr = [[0] * num for _ in range(num)]
    
    count = 1
    x, y = 0, 0
    dx, dy = 0, 1
    while count <= num**2:
        arr[x][y] = count
        count += 1
        
        if(0 > x + dx or x + dx >= num or 0 > y + dy or y + dy >= num or arr[x+dx][y+dy] != 0):
            dx,dy = changeDir(dx,dy)
        
        x, y = x + dx, y + dy
    
    print(f"#{test_case}")
    for i in range(num):
        for j in range(num):
            print(arr[i][j], end=" ")
        print()
        
"""
더 깔끔한 버전, 방향을 바꿀 때, 함수로 바꾸지 말고, 미리 배열로 정의해놓고 index를 순회하면서 수정
T = int(input())

for test_case in range(1, T + 1):
    n = int(input())
    arr = [[0] * n for _ in range(n)]
    
    # 방향: 오른쪽, 아래, 왼쪽, 위
    dirs = [(0,1), (1,0), (0,-1), (-1,0)]
    dir_idx = 0
    x, y = 0, 0
    for val in range(1, n * n + 1):
        arr[x][y] = val
        dx, dy = dirs[dir_idx]
        nx, ny = x + dx, y + dy

        if nx < 0 or nx >= n or ny < 0 or ny >= n or arr[nx][ny] != 0:
            dir_idx = (dir_idx + 1) % 4
            dx, dy = dirs[dir_idx]
            nx, ny = x + dx, y + dy
        
        x, y = nx, ny

    print(f"#{test_case}")
    for row in arr:
        print(*row)

"""