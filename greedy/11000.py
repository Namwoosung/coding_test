#11000
#강의실 배정
import sys

input = sys.stdin.readline

num = int(input())
time = [tuple(map(int, input().split())) for _ in range(num)]

start = sorted(t[0] for t in time)
end = sorted(t[1] for t in time)

result, room = 0, 0
i, j = 0, 0

while i < num:
    if start[i] < end[j]:
        room += 1
        i += 1
    else:
        room -= 1
        j += 1
    result = max(result, room)

print(result)

    
