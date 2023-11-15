#2231
#분해합

n = int(input())

num = 1
stand = 1

while True:
    stand = num

    for i in range(len(str(num))):
        stand += int(str(num)[i])

    if stand == n:
        break

    num += 1
    if num > n:
        num = 0
        break

print(num)

    