#14916
#거스름돈

#mycode
#5로 거슬러 줄 수 있는 가장 큰 수 부터 5의 몫을 하나씩 줄여가면서 count를 셈
money = int(input())

num5 = money // 5
result = -1

while num5 >= 0:
    remain = money - 5 * num5
    if remain % 2 == 1:
        num5 -= 1
    else:
        result = num5 + remain // 2
        break

print(result)

"""
아래 코드는 5의 배수면 몫을 더하고, 아닐 경우 2씩 빼면서 5의 배수가 될 때까지 count +1을 해줌
아래 코드가 더 효율적일 듯
n = int(input())
count = 0

while n>0:
    if n%5==0:
        count+=n//5
        break
    
    n-=2
    count+=1

if n<0:
    print(-1)
else:
    print(count)
"""