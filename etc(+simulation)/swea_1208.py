T = 10
for test_case in range(1, T+1):
    chance = int(input())
    arr = list(map(int, input().split()))

    while chance > 0:
        if max(arr) - min(arr) == 1 or max(arr) - min(arr) == 0:
            break

        arr[arr.index(max(arr))] -= 1
        arr[arr.index(min(arr))] += 1

        chance -= 1

    print(f"#{test_case} {max(arr) - min(arr)}")
    
    
"""
더 효과적인 버전, 방식은 동일한데 heapq를 이용해서 풀이
(python의 heapq는 최소 힙만 지원하므로 최대힙의 경우 각 값의 음수 값을 넣어서 구현)

import heapq

T = 10
for test_case in range(1, T + 1):
    chance = int(input())
    heights = list(map(int, input().split()))

    # 최소 힙, 최대 힙 구성
    min_heap = heights[:]
    max_heap = [-h for h in heights]
    heapq.heapify(min_heap)
    heapq.heapify(max_heap)

    for _ in range(chance):
        min_val = heapq.heappop(min_heap)
        max_val = -heapq.heappop(max_heap)

        # 중단 조건
        if max_val - min_val <= 1:
            heapq.heappush(min_heap, min_val)
            heapq.heappush(max_heap, -max_val)
            break

        # 평탄화 작업
        min_val += 1
        max_val -= 1

        heapq.heappush(min_heap, min_val)
        heapq.heappush(max_heap, -max_val)

    print(f"#{test_case} {-max_heap[0] - min_heap[0]}")

"""