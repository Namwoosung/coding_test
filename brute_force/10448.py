#10448
#유레카이론

num = int(input())
n = 1
T = set()

while (n * (n + 1) / 2) <= 1000:
    T.append((n * (n + 1) / 2))
    n += 1

for i in range(num):
    test = int(input())
