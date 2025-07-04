N = int(input())

for i in range(1, N+1):
    now = str(i)

    count = now.count('3') + now.count('6') + now.count('9')

    if count > 0:
        print("-" * count, end=" ")
    else:
        print(now, end=" ")