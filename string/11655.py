#11655
#ROT13

line = input()

for i in range(len(line)):
    stand = ord(line[i])
    if 65 <= stand and stand <= 77:
        print(chr(stand + 13), end = "")
    elif 78 <= stand and stand <= 90:
        print(chr(stand - 13), end = "")
    elif 97 <= stand and stand <= 109:
        print(chr(stand + 13), end = "")
    elif 110 <= stand and stand <= 122:
        print(chr(stand - 13), end = "")
    else:
        print(chr(stand), end = "")     

"""
더 깔끔한 코드 동작은 동일
arr=input()
ans=''
for i in arr:
    if i.islower():
        print(chr(97+(ord(i)+13-97)%26), end='')

    elif i.isupper():
        print(chr(65+(ord(i)+13-65)%26), end='')

    else:
        print(i,end='')
출처: https://youjin86.tistory.com/45 [_:티스토리]
"""