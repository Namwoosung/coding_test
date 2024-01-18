#1931
#회의실 배정

#끝나는 시간으로 회의를 정렬
#count 배열은 각 시간마다 최대 가능한 회의의 수, count[5]라면 5시까지 최대로 이용가능한 회의의 수
#count[num + 1]이 전체 시간에서 최대로 가능한 회의의 수
num = int(input())
meets = list()
for i in range(num):
    meet = list(map(int, input().split()))
    meets.append(meet)
#종료시간 기준 정렬, 같은 종료시간 내에서는 빠른 시작시간 순으로 정렬 <- 시작시간과 종료시간이 동일한 회의 처리를 위해 필요
meets.sort(key=lambda x:x[0])
meets.sort(key=lambda x:x[1])

#set count
count = [0] * (meets[num-1][1] + 1)

index = 0
#각 회의마다 해당 회의를 포함하는 것이 좋은 지 아닌 지 check
for item in meets:
    #이전 시간들을 update(일단 현재 회의를 포함하지 않는다고 가정하고 값 대입)
    while index < item[1]:
        count[index + 1] = count[index]
        index += 1
    #현재 회의를 포함했을 때 더 많은 회의를 가질 수 있으면 해당 값으로 설정
    if count[index] < (count[item[0]] + 1):
        count[index] = count[item[0]] + 1

print(count[-1])



"""
더 깔끔한 코드
내 풀이는 DP느낌으로 접근 아래 풀이는 greedy로 접근
그냥 시작시간 및 종료시간으로 정렬하고
현재 검사하는 회의의 시작시간이 이전 종료시간 이상이면 회의를 하는 것으로 진행
import sys

N = int(sys.stdin.readline())

time = [[0]*2 for _ in range(N)]
for i in range(N):
    s, e = map(int, sys.stdin.readline().split())
    time[i][0] = s
    time[i][1] = e

time.sort(key = lambda x: (x[1], x[0]))

cnt = 1
end_time = time[0][1]
for i in range(1, N):
    if time[i][0] >= end_time:
        cnt += 1
        end_time = time[i][1]

print(cnt)

"""