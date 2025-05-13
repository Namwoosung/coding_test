import heapq

T = int(input())

for test_case in range(1, T + 1):
    N = int(input())
    arr = [list(map(int, input().strip())) for _ in range(N)]
    dist = [[float('inf')] * N for _ in range(N)]

    dx = [-1, 1, 0, 0]  # 상하좌우
    dy = [0, 0, -1, 1]

    heap = []
    heapq.heappush(heap, (0, 0, 0))  # (비용, x, y)
    dist[0][0] = 0

    while heap:
        cost, x, y = heapq.heappop(heap)

        if (x, y) == (N - 1, N - 1):
            break  # 최종 도착점 도달 시 조기 종료 가능

        for dir in range(4):
            nx = x + dx[dir]
            ny = y + dy[dir]

            if 0 <= nx < N and 0 <= ny < N:
                ncost = cost + arr[nx][ny]
                if dist[nx][ny] > ncost:
                    dist[nx][ny] = ncost
                    heapq.heappush(heap, (ncost, nx, ny))

    print(f"#{test_case} {dist[N - 1][N - 1]}")




""" bfs버전, 이 문제는 dp로 풀면 안 되고 상하좌우 모든 경우를 고려해야 함 => (0,0) 부터 가능한 모든 곳을 조사하고, 만약 거리가 갱신되면 queue에 추가하는 방식으로 구현, 그러나 priority queue를 사용한 Dijkstra보다 성능 안좋음
from collections import deque

T = int(input())

for test_case in range(1, T + 1):
    size = int(input())
    arr = [list(map(int, input().strip())) for _ in range(size)]
    visited = [[float('inf')] * size for _ in range(size)]

    dx = [1, -1, 0, 0]
    dy = [0, 0, 1, -1]


    queue = deque()
    queue.append((0, 0))
    visited[0][0] = 0

    while queue:
        x, y = queue.popleft()

        for i in range(0, 4):
            nx, ny = x + dx[i], y + dy[i]

            if nx >= 0 and nx < size and ny >= 0 and ny < size:
                cost = arr[nx][ny] + visited[x][y]
                if visited[nx][ny] > cost:
                    visited[nx][ny] = cost
                    queue.append((nx, ny))


    print(f"#{test_case} {visited[size-1][size-1]}")
"""