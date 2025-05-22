def dfs(chance):
    global max_num

    if chance == 0:
        now = int("".join(num))
        max_num = max(now, max_num)
        return

    now = int("".join(num))
    if (now, chance) in visited:
        return
    visited.add((now, chance))

    for i in range(len(num)):
        for j in range(len(num)):
            if i == j:
                continue
            num[i], num[j] = num[j], num[i]
            visited.add((now, chance))
            dfs(chance-1)
            num[i], num[j] = num[j], num[i]



T = int(input())
for test_case in range(1, T+1):
    num, change = input().split()
    change = int(change)
    num = list(num.strip())

    max_num = 0
    visited = set()
    dfs(change)

    print(f"#{test_case} {max_num}")
