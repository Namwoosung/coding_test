#16953
#A -> B

#my code
#b를 줄여가며 check
#마지막이 1이면 빼주고 혹은 짝수면 절반으로 나눠줌
#아무 동작도 시행할 수 없거나 b가 0으로 수렴해버리면 종료
a, b = map(int, input().split())

result = 1
while b != a:
    if b <= 0:
        result = -1
        break
    else:
        if (b % 10) == 1:
            b = b // 10
            result += 1
        elif (b % 2) == 0:
            b = b // 2
            result += 1
        else:
            result = -1
            break

print(result)


"""
bfs를 이용해 bottom-up방식으로도 풀이 가능
from collections import deque
a,b = map(int,input().split())
q = deque()
q.append((a,1))
r = 0

while(q):
    n,t = q.popleft()
    if n > b:
        continue
    if n == b:
        print(t)
        break
    q.append((int(str(n)+"1"),t+1))
    q.append((n*2,t+1))
else:
    print(-1)

a부터 시작, queue에 +1한 것과 *2 한 것을 각각 대입해 나감
-> 이후 queue를 전부 돌았는데 답이 없으면 -1 출력
"""
