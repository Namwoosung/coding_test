#10808
#알파벳 개수

#mycode
#a부터 z까지 반복해 각각의 개수를 탐색
#이건 시간상 비효율적일 것 -> 동일한 문자열을 a부터 z까지 계속 반복
text = input()
test = 'a'
while test <= 'z':
    print(text.count(test), end=" ")
    test = chr(ord(test) + 1)

"""
문자열을 받아 문자열만큼 for문을 돌아 count
이게 더 효율적 주어진 문자열을 한번만 탐색하며 값을 count
arr=input()
cnt=[0]*26

for i in arr:
    cnt[ord(i)-97]+=1

print(*cnt)
"""