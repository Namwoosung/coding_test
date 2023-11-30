#1541
#잃어버린 괄호

#mycode
# -부호가 처음 나온 시점 이후로는 뒤에 모든 식은 빼기로 계산 가능
# -부호 이전에는 모든 값을 더하고, 이후에는 모든 값을 뺌
line = input()
#반복문에서 + 혹은 -기준으로 숫자를 구분하므로 마지막에 -를 추가해 마지막 숫자에 대해서도 계산 가능하도록 함
line += "-"
stand = line.find("-")

result, start = 0, 0
num = 0

for i in range(len(line)):
    if line[i] == "+" or line[i] == "-":
        num = int(line[start:i])
        start = i + 1

        if i <= stand:
            result += num
        else:
            result -= num


print(result)


"""
좀 더 깔끔한 코드 <- 풀이방식은 동일, 더 깔끔함
-기준으로 나누서 받아 리스트를 구성 -> 각 리스트에서 +기준으로 나눠 int를 받고
-이후의 값들은 모두 빼주는 것으로 계산
arr = input().split('-')
s = 0
for i in arr[0].split('+'):
    s += int(i)
for i in arr[1:]:
    for j in i.split('+'):
        s -= int(j)
print(s)
"""