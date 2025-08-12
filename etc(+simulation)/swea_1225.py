from collections import deque

T = 10
for test_case in range(1, T + 1):
    _ = int(input())
    heap = deque()


    heap.extend(list(map(int, input().split())))
    
    iteration = 1
    while True:
        item = heap.popleft()
        item -= iteration

        if item <= 0:
            heap.append(0)
            break
        heap.append(item)

        iteration += 1
        if iteration > 5:
            iteration=1

    print(f"#{test_case} ", end="")
    while heap:
        item = heap.popleft()    
        print(item, end=" ")
    print()
    

"""
풀이는 동일하지만 더 효과적인 코드
from collections import deque
from itertools import cycle

T = 10
for test_case in range(1, T + 1):
    _ = int(input())
    q = deque(map(int, input().split()))

    for dec in cycle(range(1, 6)):  # 1~5 반복
        val = q.popleft() - dec
        q.append(0 if val <= 0 else val)
        if val <= 0:
            break

    print(f"#{test_case}", *q)
"""