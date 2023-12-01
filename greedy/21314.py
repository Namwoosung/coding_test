#21314
#민겸 수

#최대값은 K앞에 최대한 많은 M을 연결
#최소값은 K는 따로 구분, M은 최대한 길게 연결

line = input()

max, min = "", ""
count = 0
for i in line:
    if i == "M":
        count += 1
    else:
        if count == 0:
            max += "5"
            min += "5"
        else:
            max += "5" + "0" * count
            min += "1" + "0" * (count - 1) + "5"
            count = 0

#문자열이 M으로 끝나 M이 남아있는 경우 후 처리
if count != 0:
    max += "1" * count
    min += "1" + "0" * (count - 1)

print(max)
print(min)