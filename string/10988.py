#10988
#팰린드롬인지 확인하기

#mycode
#직관적으로 문자열을 하나씩 비교
text = input()
flag = True
for i in range(len(text)):
    if text[i] != text[len(text) -1 - i]:
        print(0)
        flag = False
        break

if flag:
   print(1)

"""
더 좋은 코드
생각해보니 string도 list이고, [::]로 접근하면 순서를 바꿀 수 있기에 간단히 비교 가능
palindrome = input()

if palindrome[::1] == palindrome[::-1]:
    print(1)
else:
    print(0)
"""