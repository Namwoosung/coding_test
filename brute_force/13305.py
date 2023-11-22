#13305
#주유소

#my code
#현재 위치에서 더 싼 곳을 만날 때 까지 주유
#더 싼 곳이 있으면 해당 위치부터 다음 싼 곳 까지 주유
num = int(input())
road = list(map(int, input().split()))
price = list(map(int, input().split()))

stand = price[0]
result = 0

for i in range(0, num - 1):
    result += stand * road[i]
    if stand > price[i + 1]:
        stand = price[i + 1]

print(result)