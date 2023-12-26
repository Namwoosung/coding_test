#11501
#주식

#mycode
#배열을 뒤로 뒤집고, 순차적으로 검사해 stand는 현재까지 만난 값중 가장 큰 값 -> stand보다 작을 경우 +해주는 방식
#처음에는 배열을 앞에서부터 검사했고, max값을 구하는 과정 때문에 시간 복잡도가 O(n^2)이 나와 시간 초과 -> 뒤에서 부터 검사하면 O(N)으로 수행 가능
num = int(input())

for i in range(num):
    data_num = int(input())
    data = list(map(int, input().split()))
    
    data = data[::-1]
    stand = data[0]
    result = 0

    for i in data:
        if i < stand:
            result += stand - i
        else:
            stand = i
    print(result)


