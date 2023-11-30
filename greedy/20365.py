#20365
#블로그2

#my code
#처음 색으로 가능한 많이 칠하고, 칠한 부분 중 빨간 부분을 가능한 많이 칠하고, 다시 칠한 부분 중 파란 부분을 가능한 많이 칠하는 과정을 반복
num = int(input())
color = input()

stand = color[0]

start = color.find(stand)
end = color.rfind(stand)
result = 0
if end != len(color) - 1:
    result += 1 

while start != -1:
    result += 1 
    color = color[start:end + 1]
    if stand == "B":
        stand = "R"
    else:
        stand = "B"
    
    start = color.find(stand)
    end = color.rfind(stand)

print(result)

"""
훨씬 효율적인 코드
1. 동작방식 -> 전체 string을 돌며 색이 바뀌는 경우를 count하고, 이후 더 작은 색의 횟수 + 1
(만약 빨간색이 더 적다면, 전체를 파란색으로 칠하고 필요한 부분만 빨간색으로 덮는 방법)
2. dictionary를 사용, 또한 string반복 시 for color in colors와 같은 방식을 사용하면 훨씬 가독성이 좋음


N = int(input())
colors = input()

cdict = {'B':0, 'R':0}
precolor = ''
for color in colors:
    if color != precolor:
        cdict[color]+=1
    precolor=color

count = cdict['R']+1 if cdict['B']>cdict['R'] else cdict['B']+1
print(count)
"""