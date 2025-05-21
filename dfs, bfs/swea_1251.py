import heapq

T = int(input())
for test_case in range(1,T+1):
    N = int(input())
    X = list(map(int, input().split()))
    Y = list(map(int, input().split()))
    E = float(input())

    node = list(map(list, zip(X, Y)))
    graph = list()

    for i in range(N):
        cost = []
        for j in range(N):
            cost.append(E * (pow(node[i][0] - node[j][0], 2) + pow(node[i][1] - node[j][1], 2)))
        graph.append(cost)

    visited = [False] * N
    visited[0] = True
    total_cost = 0

    heap = []
    for j in range(N):
        if not visited[j]:
            heapq.heappush(heap, (graph[0][j], j))  # (비용, 인덱스)

    while heap:
        next_cost, next_node = heapq.heappop(heap)
        if visited[next_node]:
            continue
        visited[next_node] = True
        total_cost += next_cost

        for j in range(N):
            if not visited[j]:
                heapq.heappush(heap, (graph[next_node][j], j))


    print(f"#{test_case} {round(total_cost)}")