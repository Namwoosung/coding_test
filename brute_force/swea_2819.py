def dfs(x, y, index, code):
    code += board[x][y]
    index += 1
    if index == 7:
        results.add(code)
        return
    for dx, dy in directions:
        nx, ny = x + dx, y + dy
        if nx >= 0 and nx < 4 and ny >= 0 and ny <4:
            dfs(nx, ny, index, code)


T = int(input())
for test_case in range(1 ,T+1):
    N = 4
    board = [list(input().split()) for _ in range(N)]

    directions = [(1,0), (-1, 0), (0, 1), (0, -1)]

    results = set()

    for i in range(4):
        for j in range(4):
            dfs(i, j, 0, '')

    print(f"#{test_case} {len(results)}")
    