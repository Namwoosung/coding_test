from collections import deque


T = 10
for test_case in range(1, T+1):
    N, start = map(int, input().split())
    arr = list(map(int, input().split()))

    graph = [list() for _ in range(max(arr) + 1)]
    visited = [False for _ in range(max(arr) + 1)]

    for i in range(0, N, 2):
        graph[arr[i]].append(arr[i+1])

    queue = deque()
    queue.append(start)
    visited[start] = True

    result = 0
    while queue:
        result = 0
        for i in range(len(queue)):
            now = queue.popleft()
            result = max(result, now)

            for item in graph[now]:
                if not visited[item]:
                    visited[item] = True
                    queue.append(item)

    print(f"#{test_case} {result}")