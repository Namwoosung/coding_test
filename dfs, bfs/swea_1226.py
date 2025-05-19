T = 10
for test_case in range(1, T+1):
    _ = int(input())

    board = [list(map(int, input().strip())) for _ in range(16)]
    visited = [[False] * 16 for _ in range(16)]

    stack = list()
    stack.append((1,1))
    visited[1][1] = True

    directions = [(1,0), (-1,0), (0,1), (0,-1)]

    is_goal = False
    while stack:
        now = stack.pop()

        if board[now[0]][now[1]] == 3:
            is_goal = True
            break

        for dx, dy in directions:
            nx = now[0] + dx
            ny = now[1] + dy

            if nx >=0 and nx < 16 and ny >= 0 and ny <16:
                if board[nx][ny] in (0,3) and not visited[nx][ny]:
                    stack.append((nx, ny))
                    visited[nx][ny] = True

    if is_goal:
        print(f"#{test_case} 1")
    else:
        print(f"#{test_case} 0")


