#2828
#사과 담기 게임

#my code, greedy
#바구니 위치를 stand, 사과의 위치를 place로 놓고
#stand와 크기 M을 비교해가며 최소한의 이동거리를 계속 더해감
N, M = input().split()
N = int(N)
M = int(M)
num = int(input())
stand = 1
result = 0
for i in range(num):
    place = int(input())
    if stand <= place and place < stand + M:
        continue
    elif stand > place:
        result += stand - place
        stand = place
    else:
        result += place - stand - M + 1
        stand = place - M + 1
print(result)



"""
좀 더 깔끔한 코드
내 코드에 continue 부분은 사실 검사할 필요 없이 그냥 검사 안해도 됨
또한 start와 end로 범위를 지정하면 좀 더 가독성이 좋아짐
from sys import stdin


n, m = map(int, stdin.readline().split())
j = int(stdin.readline())

start = 1
end = m
distance = 0

for _ in range(j):
    p = int(stdin.readline())

    if p < start:
        distance += (start - p)
        start = p
        end = p + m - 1

    elif p > end:
        distance += (p - end)
        end = p
        start = end - m + 1

print(distance)
"""