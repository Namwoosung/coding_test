T = 10
for test_case in range(1, T+1):
    N = int(input())
    line = input()

    count1 = 0
    count2 = 0
    count3 = 0
    count4 = 0

    stand = False
    for item in line:
        if item == '(':
            count1 += 1
        elif item == '[':
            count2 += 1
        elif item == '{':
            count3 += 1
        elif item == '<':
            count4 += 1
        elif item == ')':
            count1 -= 1
        elif item == ']':
            count2 -= 1
        elif item == '}':
            count3 -= 1
        elif item == '>':
            count4 -= 1
        
        if count1 < 0 or count2 < 0 or count3 < 0 or count4 < 0:
            break
    
    if count1 == 0 and count2 == 0 and count3 == 0 and count4 == 0:
        stand = True

    if stand:
        print(f"#{test_case} 1")
    else:
        print(f"#{test_case} 0")



"""
좀 더 효과적인 코드
++ 처음 풀 때 문제 이해를 잘 못해서 위의 풀이는 잘 못된 풀이인데, 위의 풀이에 대한 반례가 없어서 통관한듯
나는 괄호 순서가 반드시 일치할 필요 없이 여는 거랑 닫는 거랑 다 일치만 되면 되는 줄 알았는데, 여닫는 순서또한 올바른 괄호끼리 이루어져야 해서 아래와 같이 stack 기반으로 푸는 것이 맞음


T = 10
pairs = {')': '(', ']': '[', '}': '{', '>': '<'}

for test_case in range(1, T + 1):
    N = int(input())
    line = input()
    stack = []
    valid = True

    for char in line:
        if char in '([{<':
            stack.append(char)
        elif char in ')]}>':
            if not stack or stack[-1] != pairs[char]:
                valid = False
                break
            stack.pop()

    if stack:
        valid = False

    print(f"#{test_case} {1 if valid else 0}")

"""