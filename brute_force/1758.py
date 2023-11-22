#1758
#알바생 강호

#my code
# tip을 많이 주는 사람먼저 커피를 주면 됨
# 순서에 따라 -되는 값은 동일 -> 양수의 손실을 최소화, 음수의 손실을 최대화
# ex) 1원 줄 사람을 마지막에 10번째에 주면 1 - 9 = -8이지만 0이므로 가장 이득
num = int(input())
money = list()
for i in range(num):
    money.append(int(input()))
money.sort(reverse=True)

result = 0
for i in range(len(money)):
    if((money[i] - i) > 0):
        result += money[i] - i    

print(result)