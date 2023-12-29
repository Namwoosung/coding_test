#1080
#py

#그리디로 풀이, A를 0,0 지점부터 검사해 다르면 3 x 3 크기의 행렬을 토클, 못하면 중지하고 -1출력
n, m = map(int, input().split())

A = []
B = []
result = 0
isPos = True

A = []
for _ in range(n):
    A.append(list(map(int, list(input()))))
B = []
for _ in range(n):
    B.append(list(map(int, list(input()))))

for i in range(n):
    for j in range(m):
        if A[i][j] != B[i][j]:
            if (j > m - 3) or (i > n - 3):
                isPos = False
                break
            
            result += 1
            for a in range(i, i + 3):
                for b in range(j, j + 3):
                    if A[a][b] == 1:
                        A[a][b] = 0
                    else:
                        A[a][b] = 1
    
    if not isPos:
        break


if isPos:
    print(result)
else:
    print(-1)