#2217
#로프

##greedy algorithm으로 풀이
#무거운 순으로 정렬하고 각 개수별로 로프를 활용했을 때, 최대로 들 수 있는 무게중 max값을 출력
num = int(input())
arr = list()
for i in range(num):
    arr.append(int(input()))
arr.sort(reverse = True)

result = list()

for i in range(len(arr)):
    result.append(arr[i] * (i + 1))
print(max(result))