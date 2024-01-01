#2870
#수학숙제

#숫자마다 리스트에 담고 정렬 후 출력
n = int(input())

number= list()
for i in range(n):
    line = input()

    flag = False
    start = 0
    for j in range(len(line)):
        if 'a' <= line[j] and line[j] <= 'z':
            if flag:
                number.append(int(line[start:j]))
                flag = False
        else:
            if not flag:
                flag = True
                start = j
    
    if flag:
        number.append(int(line[start:j+1]))
        flag = False

number.sort()
for i in number:
    print(i)


"""
동작은 동일한데 조금 더 깔끔한 코드
isdigit() 메소드 활용 및 숫자를 string으로 받아놓으면서 처리
n = int(input())
answer = []

for _ in range(n):
    result = ''
    num = input()
    for i in num:
        if i.isdigit():
            result += i
        else:
            if result != '':
                answer.append(int(result))
                result = ''
    if result != '':
        answer.append(int(result))

answer.sort()
for i in answer:
    print(i)
"""