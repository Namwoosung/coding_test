#11399
#ATM

#greedy알고리즘
#1번부터 N번까지 시간은 누각 번호마다 누적되어 계산됨
#-> 1번이 가장 길면 해당 시간 만큼 1~N명이 기다리니 총 N번 누적
#=> 가장 짧게 걸리는 순으로 사용해야 함

num = int(input())
time = list(map(int, input().split()))
time.sort()
result = 0
for i in range(num):
    result += time[i] * (num - i)

print(result)