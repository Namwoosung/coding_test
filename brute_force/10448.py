#10448
#유레카이론

#mycode
#T배열을 만들고 모든 경우의 수를 탐색
T = list()
num = 0
num, plus = 0, 1
while num <= 1000:
    num += plus
    T.append(num)
    plus += 1

line = int(input())

for m in range(line):
    K = int(input())
    flag = False
    for i in T:
        for j in T:
            for k in T:
                if (i + j + k) == K:
                    flag = True
                if flag: break
            if flag: break
        if flag: break
    
    if flag: print(1)
    else: print(0)

"""
더 적합한 코드
내 코드는 입력에 따라 검사했음 -> 이런 경우 입력 케이스가 너무 많아지면 시간초과가 날 수도 있음
미리 삼각수를 모두 구하고 입력에 따라 해당 배열이 1인지 0인지 출력
-> 1초 내에 위의 코드는 무조건 동작함(45^3의 시간)
tri = [i * (i + 1) // 2 for i in range(1, 46)]  # 45번째 삼각수 == 1035
eur = [0] * 1001

for i in tri:
    for j in tri:
        for k in tri:
            num = i + j + k
            if num <= 1000:
                eur[num] = 1 # 세 숫자의 조합으로 만들어지는 수

t = int(input())
for _ in range(t):
    print(eur[int(input())])
    """