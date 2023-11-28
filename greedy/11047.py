#11047
#동전 0

#mycode
#greedy로 풀이, 그리디로 풀 수 있는 이유는 문제 조건에 처음 동전은 항상 1이고 이후 동전들은 2번째 동전의 배수 값이기에 가능
N, K = map(int, input().split())
money = list()

for i in range(N):
    money.append(int(input()))
money.sort(reverse=True)

result = 0
for i in range(N):
    result += K // money[i]
    K = K % money[i]
    
print(result)