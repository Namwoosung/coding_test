T = int(input())

val0 = [0,0,0,1,1,0,1]
val1 = [0,0,1,1,0,0,1]
val2=[0,0,1,0,0,1,1]
val3=[0,1,1,1,1,0,1]
val4=[0,1,0,0,0,1,1]
val5=[0,1,1,0,0,0,1]
val6=[0,1,0,1,1,1,1]
val7=[0,1,1,1,0,1,1]
val8=[0,1,1,0,1,1,1]
val9=[0,0,0,1,0,1,1]

for test_case in range(1, T+1):
    N, M = map(int, input().split())

    arr = [list(map(int, input().strip())) for _ in range(N)]

    right_top = (0, 0)
    for i in range(N):
        for j in range(M-1, -1, -1):
            if arr[i][j] == 1:
                right_top = (i, j)
                break
    
    code = arr[right_top[0]][right_top[1] - 55: right_top[1] + 1]
    result = list()
    for i in range(8):
        now = code[7*i : 7*i+7]

        if now == val0:
            result.append(0)
        elif now == val1:
            result.append(1)
        elif now == val2:
            result.append(2)
        elif now == val3:
            result.append(3)
        elif now == val4:
            result.append(4)
        elif now == val5:
            result.append(5)
        elif now == val6:
            result.append(6)
        elif now == val7:
            result.append(7)
        elif now == val8:
            result.append(8)
        elif now == val9:
            result.append(9)
    
    decode = 0
    for i in range(8):
        if i % 2 == 1:
            decode += result[i]
        else:
            decode += result[i] * 3
    if decode % 10 == 0:
        print(f"#{test_case} {sum(result)}")
    else:
        print(f"#{test_case} 0")




"""
더 효과적인 풀이, 풀이법은 동일한데 dictionary 도입 및 일부 효율성 개선
T = int(input())

# 패턴을 미리 dict로 정의
patterns = {
    (0, 0, 0, 1, 1, 0, 1): 0,
    (0, 0, 1, 1, 0, 0, 1): 1,
    (0, 0, 1, 0, 0, 1, 1): 2,
    (0, 1, 1, 1, 1, 0, 1): 3,
    (0, 1, 0, 0, 0, 1, 1): 4,
    (0, 1, 1, 0, 0, 0, 1): 5,
    (0, 1, 0, 1, 1, 1, 1): 6,
    (0, 1, 1, 1, 0, 1, 1): 7,
    (0, 1, 1, 0, 1, 1, 1): 8,
    (0, 0, 0, 1, 0, 1, 1): 9,
}

for test_case in range(1, T+1):
    N, M = map(int, input().split())
    arr = [list(map(int, input().strip())) for _ in range(N)]

    # 암호 끝(1이 마지막으로 등장하는 위치) 찾기
    for i in range(N):
        if 1 in arr[i]:
            for j in range(M-1, -1, -1):
                if arr[i][j] == 1:
                    row = arr[i]
                    code = row[j-55:j+1]  # 56개의 암호 코드 추출
                    break
            break  # 첫 row에서 찾으면 종료

    # 암호 해독
    result = []
    for i in range(0, 56, 7):
        part = tuple(code[i:i+7])
        result.append(patterns.get(part, -1))  # 잘못된 패턴이면 -1

    if -1 in result:
        print(f"#{test_case} 0")  # 패턴 매칭 실패 시 0 처리
        continue

    # 검증: 홀수 자리 * 3 + 짝수 자리
    checksum = sum(result[i] * 3 if i % 2 == 0 else result[i] for i in range(8))

    if checksum % 10 == 0:
        print(f"#{test_case} {sum(result)}")
    else:
        print(f"#{test_case} 0")
"""