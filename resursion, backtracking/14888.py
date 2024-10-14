#순열을 이용한 경우, 시간복잡도가 n!개로 비효율적인 코드
import sys, itertools
input = sys.stdin.readline

n = int(input())
numbers = list(map(int, input().split()))
operate = list(map(int, input().split()))

operations = list()
for i in range(4):
    operations.extend([i] * operate[i])
all_operations = list(set(list(itertools.permutations(operations))))

def cal(a, b, ope):
    match ope:
        case 0:
            return a + b
        case 1:
            return a - b
        case 2:
            return a * b
        case 3:
            if a < 0 and b > 0:
                a *= -1
                return a//b * -1
            return a//b

results = list()
for ope in all_operations:
    result = numbers[0]
    for i in range(n-1):
        result = cal(result, numbers[i+1], ope[i])
    results.append(result)

print(max(results))
print(min(results))



#백트래킹을 이용한 코드, 시간 복잡도가 4^n으로 더 효율적
import sys
input = sys.stdin.readline

# 입력 처리
n = int(input())
numbers = list(map(int, input().split()))
operate = list(map(int, input().split()))  # 덧셈, 뺄셈, 곱셈, 나눗셈 순서

# 최대, 최소값 초기화
max_result = -float('inf')
min_result = float('inf')

# 백트래킹 함수 정의
def backtrack(idx, current_result, add, sub, mul, div):
    global max_result, min_result
    # 모든 숫자를 다 사용한 경우
    if idx == n:
        max_result = max(max_result, current_result)
        min_result = min(min_result, current_result)
        return
    
    # 각 연산자에 대해 백트래킹 진행
    if add > 0:
        backtrack(idx + 1, current_result + numbers[idx], add - 1, sub, mul, div)
    if sub > 0:
        backtrack(idx + 1, current_result - numbers[idx], add, sub - 1, mul, div)
    if mul > 0:
        backtrack(idx + 1, current_result * numbers[idx], add, sub, mul - 1, div)
    if div > 0:
        # 음수 나눗셈 처리
        if current_result < 0:
            backtrack(idx + 1, -(-current_result // numbers[idx]), add, sub, mul, div - 1)
        else:
            backtrack(idx + 1, current_result // numbers[idx], add, sub, mul, div - 1)

# 백트래킹 시작
backtrack(1, numbers[0], operate[0], operate[1], operate[2], operate[3])

# 결과 출력
print(max_result)
print(min_result)