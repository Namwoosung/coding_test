#input data는 항상 string이다 -> 형변환 잊지 말것



"""알아두면 좋은 함수 및 코드"""
"XXXX.XX".replace('XXXX', 'AAAA').replace('XX', 'AA') #replace함수

board[idx:idx+4:2] #인덱싱 접근방식 -> start부터 end-1까지 step만큼 띄어서 접근
board[::-1] #이렇게하면 -1이 step이고 전체에 대해서이므로 전체 list를 뒤집게 됨

abs(x) 
pow(x,y)
round(x)
help(abs)
min(2, 3, max(5, 7), -1, 2)



#사용자 정의함수에서 help를 사용할 수 있도록 하려면 FDR로 문장을 추가하면 됨
def convert_to_celsius(fahrenheit):
    """(int) -> int
    
    Return the celcius number from the given fahrenheit number.
    
    >>> convert_to_celsius(212)
    100
    """
    return (fahrenheit - 32) * 5/9




"""
python에서의 string 활용
python은 str type이 내장되어 있음
"""
name = 'Issac'
len(name)
name[3:] #ac
name[2:-1] #sa
name.replace('ss', 'xx')
name.count('xx')
name.find('x')
name.find('x', 2) #2번째 x의 index
# 숫자일 경우 int(name), float(name)으로 형변환 가능




#print함수 활용
print(1, '2', "3", sep = " : ", end = " {endl}  \"")
#sep는 value사이에 출력할 값으로 default는 공백 한 칸
#end는 value를 모두 출력하고 출력할 값으로 default는 \n




"""
boolean
"""
a =True
b= False
#int 혹은 float가 0이면 false, 나머지는 true





"""
쓸만한 모듈
math
random
turtle <- 그림그리기

사용자 정의 모듈은 그냥 .py로 모듈 만들고 import하면 됨
"""





"""
class와 method
string도 일종의 class, int, float도 class
name = "Abcd"로 선언하면
name이라는 string instance를 만들고 member data로 ABcd를 입력한 것
.relace()
.upper()
.count()
등이 string class안에 method
"""




#string format
printf('{1} ate {0} apples {2}.'.format('5', 'you', 'at 2pm'))





"""
list와 활용
python에서는 서로 다른 type의 data를 하나의 list에 저장가능
"""
temp = [ "Python", "Javascript", "C++", "__reserved__", 1, 2 ]
#list에서 사용가능한 함수 예시
print(len(course))
print(max(course))
print(min(course))
print(sorted(course))
print(sorted(course, reverse=True))
new_list = tmep + course #list끼리 더하기도 가능
printf('c' in course) #in ketword로 true, return 반환

#별칭과 복사
test = course #별칭임, 즉 하나의 list memory를 2개의 pointer가 가르키고 있는 것
test2 = course[:] #복사, 새로운 lsit를 생성하고 메모리를 할당해 test2가 저장

#list의 method
course = [ "Python", "Javascript", "C++", "__reserved__" ]
print(course.pop())
course.sort() #정렬
course.append("Go") #마지막에 추가
course.append(["HTML5", "CSS3"])
course.extend(["HTML", "Dart"]) #확장
course.insert("Java", 2) #2번째 index에 삽입
course.remove("Go")
course.reverse()
print(course.count("C++"))




"""
random module 활용
"""
import random
num = random.randrange(1, 11, 2) #1부터 10까지 홀수 난수 생성
num = random.random() #0과 1사이의 실수 난수
myList = [1, 2, 3, 4, 5]
random.shuffle(myList) # 섞기
num = random.choice(myList) #임의의 element 선택


"""웹-파이썬 실습자료 step12까지 했음"""