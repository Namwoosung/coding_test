T = 10
for test_case in range(1, T+1):
    _ = int(input())
    codes = list(input().split())
    num = int(input())
    commands = list(input(). split())

    index = 1
    for i in range(num):
        pos = int(commands[index])
        count = int(commands[index + 1])

        codes = codes[:pos] + commands[index + 2: index + 2 + count] + codes[pos:]
        index += count + 3

    print(f"#{test_case} ", end="")
    for i in range(10):
        print(codes[i], end=" ")
    print()


"""
개선버전, 동일한데, 각 item을 insert 내장함수로 집어 넣어 더 효율
T = 10
for test_case in range(1, T + 1):
    _ = int(input())
    codes = list(input().split())
    _ = int(input())
    commands = list(input().split())

    index = 0
    while index < len(commands):
        if commands[index] == 'I':
            pos = int(commands[index + 1])
            count = int(commands[index + 2])
            insert_data = commands[index + 3:index + 3 + count]

            for offset, item in enumerate(insert_data):
                codes.insert(pos + offset, item)

            index += 3 + count

    print(f"#{test_case} {' '.join(codes[:10])}")

"""


