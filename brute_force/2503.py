#2503
#숫자 야구

#111~999까지 순열을 생성하고 각 숫자들을 대답과 비교해 모든 대답에 부합하는 숫자만 count하도록 구현

#set answer
n = int(input())
answers = list()
for i in range(n):
    answers.append(list(input().split()))

result = list()

#check posible all number
for i in range(1, 10):
    for j in range(1, 10):
        if i == j:
            continue
        for k in range(1, 10):
            if i == k or j == k:
                continue
            
            test = str(i) + str(j) + str(k)
            flag = True
            #check matching
            for answer in answers:
                strike, ball = 0, 0
                #check strike and ball
                for index in range(3):
                    if test[index] == answer[0][index]:
                        strike += 1
                    elif test[index] in answer[0]:
                        ball += 1
                if strike != int(answer[1]) or ball != int(answer[2]):
                    flag = False
                    break
            #add result
            if flag:
                result.append(test)

print(len(result))


"""
좀 더 간결한 코드
1. 여기서는 111~999의 순열을 생성할 때 permutation이라는 method를 활용
2. 순열을 만들어 놓고, 각 case를 검사하며 부합하지 않으면 제거하는 방식으로 진행
-> 마지막에 lsit에 남아있는 숫자의 개수가 가능한 경우의 수
from itertools import permutations
N = int(input())

data = ['1', '2', '3', '4', '5', '6', '7', '8', '9']
num = list(permutations(data, 3))

for _ in range(N):
    n, s, b = map(int, input().split())
    n = list(str(n))
    rmcnt = 0
    for i in range(len(num)):
        strike = ball = 0
        i -= rmcnt # num[0] 부터 시작
        for j in range(3):
            if num[i][j] == n[j]:
                strike += 1
            elif n[j] in num[i]:
                ball += 1
            
        if (strike != s) or (ball != b):
            num.remove(num[i])
            rmcnt += 1

print(len(num))

"""