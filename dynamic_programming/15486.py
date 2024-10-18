import sys
input = sys.stdin.readline

state = input().rstrip(";\n")
index = state.find(' ')

type = state[0:index]
vars = state[index + 1:].split(', ')

for i in vars:
    var_index = 1000
    if i.find('*'):
        var_index = min(var_index, i.find('*'))
    if i.find('['):
        var_index = min(var_index, i.find('['))
    if i.find('&'):
        var_index = min(var_index, i.find('&'))
    
    var_name = i[:var_index]
    if(var_index == 1000):
        var_name = i
    
    var_type = ''
    for char in i[::-1]:
        if(char == '*' or char =='&'):
            var_type += char
        elif char == ']':
            var_type += "[]"
        elif char == '[':
            continue
        else:
            break

    print(type + var_type + ' ' + var_name+ ";")


print(type)
print(vars)