#1436
#영화감독 숌


#my code
#대부분 동일하게 푼 듯, 브루트포스 방식으로 풀이
n = int(input())

num = 666
while n > 0:
    if str(num).find('666') != -1:
        n -= 1
        result = num
    num += 1

print(result)
