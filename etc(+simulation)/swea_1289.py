T = int(input())
for test_case in range(1, T+1):
    line = input()
    
    result = 0
    stand = '0'
    for char in line:
        if char != stand:
            result += 1
            stand = char

    print(f"#{test_case} {result}")