import sys, itertools
input = sys.stdin.readline

n, k = map(int, input().split())
words = list()

to_replcae = "antci\n"
translate_table = str.maketrans('', '', to_replcae)
for i in range(n):
    words.append(set(input().translate(translate_table)))

if k < 5:
    print(0)
    exit
    
canditaes = list("bdefghjklmopqrsuvwxyz")
k -= 5



candiates  = [set(i) for i in itertools.combinations(list("bdefghjklmopqrsuvwxyz"), k)]

max_result = 0
for candiate in candiates:
    result = 0
    for word in words:
        if candiate >= word:
            result += 1
    
    max_result = max(max_result, result)

print(max_result)
