#20115
#에너지 드링크

#my code
#그리디로 풀이
#절반이 사라지는 drink를 가장 작게 유지하는 것이 중요하므로 정렬 후 0번째 인덱스를 마지막 인덱스에 합치는 것이 가장 유리
num = int(input())
drink = list(map(int, input().split()))

drink.sort()

while len(drink) > 1:
    drink[len(drink) - 1] += drink[0] / 2
    drink.pop(0)

print(drink[0])