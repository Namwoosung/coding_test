#17615
#볼 모으기

#공을 옮기는 경우는 red를 왼쪽, 오른쪽, blue를 왼쪽, 오른쪽 총 4가지 경우 -> 4가지 경우 중 최소값을 탐색

n = int(input())
balls = input()

#set case
result = [0 for i in range(4)]

#check left
l_start = 0
while l_start < n and balls[l_start] == balls[0]:
    l_start += 1

for i in range(l_start, n):
    if balls[i] == 'R':
        result[0] += 1
    else:
        result[1] += 1

#check right
r_start = n-1
while r_start > -1 and balls[r_start] == balls[n-1]:
    r_start -= 1

for i in range(0, r_start + 1):
    if balls[i] == 'R':
        result[2] += 1
    else:
        result[3] += 1

print(min(result))



"""
더 간결한 코드
개념은 동일, 4가지 case를 상정하고 최소값을 탐색
1. 나는 index를 이동해가며 초기에 동일한 색 공을 pass 했다면, 여기서는 stript method를 이용해 공을 제거하고 시작
2. string의 count method를 이용해 반복문을 사용하지 않고 간단하게 개수 확인

import sys

N = int(sys.stdin.readline().strip())
balls = str(sys.stdin.readline().strip())
cnt = []

# 우측으로 레드 모으기
rexplore = balls.rstrip('R')
cnt.append(rexplore.count('R'))

# 우측으로 블루 모으기
rexplore = balls.rstrip('B')
cnt.append(rexplore.count('B'))

# 좌측으로 레드 모으기
lexplore = balls.lstrip('R')
cnt.append(lexplore.count('R'))

# 좌측으로 블루 모으기
lexplore = balls.lstrip('B')
cnt.append(lexplore.count('B'))

print(min(cnt))

"""