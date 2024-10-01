import sys
from collections import deque

input = sys.stdin.readline

size = int(input())
graph = list(list() for _ in range(size))
infect = [False for _ in range(size)]
queue = deque()

n = int(input())
for i in range(n):
    a, b = map(int, input().split())
    graph[a - 1].append(b - 1)
    graph[b - 1].append(a - 1)


queue.append(0)
infect[0] = True
count = 0

while(queue):
    now = queue.popleft()

    for i in graph[now]:
        if infect[i] == False:
            queue.append(i)
            infect[i] = True
            count += 1

print(count)


""" 변수 표현이나 선언 등을 더 깔끔하게 한 코드
import sys
from collections import deque

input = sys.stdin.readline

size = int(input())
graph = [[] for _ in range(size)]  # 리스트 초기화 간소화
infected = [False] * size  # 리스트 초기화 간소화
queue = deque()

n = int(input())
for _ in range(n):
    a, b = map(int, input().split())
    graph[a - 1].append(b - 1)
    graph[b - 1].append(a - 1)

# 0번 노드를 큐에 추가하고 감염 처리
queue.append(0)
infected[0] = True
count = 0

while queue:
    current_node = queue.popleft()

    for neighbor in graph[current_node]:
        if not infected[neighbor]:  # 감염 여부 체크
            queue.append(neighbor)
            infected[neighbor] = True
            count += 1

print(count)
"""