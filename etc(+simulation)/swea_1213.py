T = 10
for test_case in range(1, T+1):
    _ = int(input())

    search = input()
    line = input()

    start = 0
    result = 0
    while True:
        index = line.find(search, start)
        if index != -1:
            result += 1
            start = index + 1
        else:
            break

    print(f"#{test_case} {result}")