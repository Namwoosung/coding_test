from itertools import permutations

T = int(input())

for test_case in range(1, T+1):
    N = int(input())

    arr = list(map(int, input().split()))
    company = (arr[0], arr[1])
    home = (arr[2], arr[3])
    client = []

    for i in range(N):
        client.append((arr[4 + i*2], arr[5 + i*2]))
    
    all_path = permutations(client)

    dist = float('inf')

    for item in all_path:
        now = 0
        now += abs(company[0] - item[0][0]) + abs(company[1] - item[0][1])
        if now > dist:
            continue
        for i in range(N-1):
            now += abs(item[i][0] - item[i+1][0]) + abs(item[i][1] - item[i+1][1])
            if now > dist:
                continue
        now += abs(item[N-1][0] - home[0]) + abs(item[N-1][1] - home[1])
        if now < dist:
            dist = now

    print(f"#{test_case} {dist}")


"""
위와 전체적인 풀이방식은 동일
그러나 위는 순열을 사용, 아래는 dfs를 사용
=> 메모리 측면이나 백트래킹 측면에서 아래 코드가 더 효율적

def dfs(x, y, visited, total):
    global min_dist

    if total > min_dist:
        return
    
    if all(visited):
        total += abs(x - home[0]) + abs(y - home[1])
        min_dist = min(min_dist, total)
        return
    
    for i in range(N):
        if not visited[i]:
            visited[i] = True
            dfs(clients[i][0], clients[i][1], visited, total + abs(x - clients[i][0]) + abs(y - clients[i][1]))
            visited[i] = False



T = int(input())
for test_case in range(1, T+1):
    N = int(input())
    arr = list(map(int, input().split()))

    company = (arr[0], arr[1])
    home = (arr[2], arr[3])
    clients = [tuple(arr[i:i+2]) for i in range(4, len(arr), 2)]

    min_dist = float('inf')
    visited = [False] * N
    
    dfs(company[0], company[1], visited, 0)

    print(f"#{test_case} {min_dist}")
"""