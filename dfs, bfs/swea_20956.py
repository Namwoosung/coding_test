def dfs(now):
    global is_possible
    if is_possible:
        return

    if len(now) == len(S):
        if now == S:
            is_possible = True
        return

    if now[-1] == "X":
        dfs(now[:-1])
    if now[0] == "Y":
        dfs(now[::-1][:-1])


T = int(input())
for test_case in range(1, T+1):
    S = input()
    E = input()

    is_possible = False
    dfs(E)

    if is_possible:
        print(f"#{test_case} Yes")
    else:
        print(f"#{test_case} No")