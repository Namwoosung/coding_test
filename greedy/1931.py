#1931
#회의실 배정

#끝나는 시간으로 회의를 정렬
#count 배열은 각 시간마다 최대 가능한 회의의 수, count[5]라면 5시까지 최대로 이용가능한 회의의 수
#count[num + 1]이 전체 시간에서 최대로 가능한 회의의 수
num = int(input())
meets = list()
for i in range(num):
    meet = list(map(int, input().split()))
    meets.append(meet)
meets.sort(key=lambda x:x[1])

#set count
count = [0] * (meets[num-1][1] + 1)


index, i = 0, 0
while i < len(count):
    #현재 check할 회의가 없으면 이전 시간과 동일
    if i != meets[index][1]:
        count[i] = count[i - 1]
        i += 1
    #check할 회의가 존재하면, 현재 회의를 넣는 경우와 아닌 경우를 고려해 더 큰 값을 count에 저장
    else:
        if count[i - 1] > (count[meets[index][0]] + 1):
            count[i] = count[i - 1]
        else:
            count[i] = count[meets[index][0]] + 1
        
        index += 1


print(count[len(count) - 1])
