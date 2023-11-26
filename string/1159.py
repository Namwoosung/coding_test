#1159
#농구 경기

#mycode
num = int(input())
name = list()
for i in range(num):
    name.append(input())

stand = [0] * 26
for i in name:
    stand[ord(i[0]) - 97] += 1

flag = True
for i in range(len(stand)):
    if stand[i] >= 5:
        print(chr(i + 97), end = "")
        flag = False

if flag:
    print("PREDAJA")


"""
전체적으로 비슷한데 여기는 set을 활용해 중복을 제거하고, 존재하는 앞자리의 개수를 count
n = int(input())
player_list = []
result = []

for _ in range(n):
    a = input()
    player_list.append(a[0])

first_names = set(player_list)

for i in first_names:
    if player_list.count(i) >= 5:
        result.append(i)

if len(result) > 0:
    print(''.join(sorted(result)))
else:
    print("PREDAJA")
"""