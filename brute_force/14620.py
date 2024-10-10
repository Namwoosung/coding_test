import sys
input = sys.stdin.readline

size = int(input())
table = list()
for i in range(size):
    table.append(list(map(int, input().split())))

def calCost(row, col):
    return table[row][col] + table[row - 1][col] + table[row + 1][col] + table[row][col-1] + table[row][col+1]

minCost = 600
for aRow in range(1, size - 1):
    for aCol in range(1, size - 1):
        nowCost = 0
        nowCost += calCost(aRow, aCol)
        if(nowCost > minCost):
            continue


        for bRow in range(1, size - 1):
            for bCol in range(1, size - 1):
                if(abs(aRow - bRow) + abs(aCol - bCol) < 3):
                    continue
                nowCost += calCost(bRow, bCol)
                if(nowCost > minCost):
                    nowCost -= calCost(bRow, bCol)
                    continue


                for cRow in range(1, size - 1):
                    for cCol in range(1, size - 1):
                        if(abs(aRow - cRow) + abs(aCol - cCol) < 3):
                            continue
                        if(abs(bRow - cRow) + abs(bCol - cCol) < 3):
                            continue
                        
                        nowCost += calCost(cRow, cCol)
                        if(nowCost > minCost):
                            nowCost -= calCost(cRow, cCol)
                            continue
                        else:
                            minCost = nowCost


print(minCost)
                        




        
