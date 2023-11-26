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

#python에서 ASCII로 접근해 값을 증가시키는 방법
test = chr(ord(test) + 1)




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
course.reverse() #이건 배열을 거꾸로 뒤집는 것
course.sort(reverse = True) # 이게 내림차순 정렬
print(course.count("C++"))


#list로 만드는 map 함수(아래는 i의 각 자리수의 합을 구하는 예제)
N = input()
for i in range(1, N + 1):
  tmp = sum(map(int,str(i))) #map으로 i의 각 자리수를 element로 하는 int형 list를 생성 -> sum함수로 합을 구함



#여러 개 input을 한 번에 받아 바로 int로 변환
n, m = map(int, input().split())
#여러 개 input을 int형 list로 입력
price = list(map(int, input().split()))



"""
random module 활용
"""
import random
num = random.randrange(1, 11, 2) #1부터 10까지 홀수 난수 생성
num = random.random() #0과 1사이의 실수 난수
myList = [1, 2, 3, 4, 5]
random.shuffle(myList) # 섞기
num = random.choice(myList) #임의의 element 선택


"""
file 기본 활용법
"""
file = open('path')
contents = file.raad() #file의 모든 내용을 한 번에 read

#한 줄씩 읽어 \n을 없애고 ','로 구분된 것을 나눠서 list로 만들어 이중배열로 저장
fileMatrix = []
lineContent = file.readline()
while lineContent != '':
   fileMatrix.append(lineContent.strip('\n').split(','))
   lineContent = file.readline()


file.close()

file = open('path', 'r') #read용으로 열기
file = open('path', 'a') #append용으로 열기 -> wrtie() 사용가능


"""
data collection type들
"""
#set
exSet1 = {1, 2, 3, 4, 4, 5}
exSe2 = set(range(5))
#set은 집합과 동일한 개념, 순서도 없고, 중복도 안됨
exSet1.add(6)
exSet1.remove(6)
# 2집합의 연산으로 | & - >= <= 등 가능

#tuple
tempTuple = (1,2,3,4,5)
#tuple의 경우 기존 list와 비슷하지만, 내부 element 변경 불가

#dictionary
langAuthor = {"python":"Guide van Rossum", "C++":"Bjarne Stroustrup"}
#key와 value의 쌍으로 존재


"""
class 기본 예제
"""
class Student:
  #class variable
  __countStudent = 0

  #attribute혹은 class variable에 __를 같이 선언하지 않으면 class 외부에서도 접근가능, __를 적으면 외부에서 접근 시 error 발생
  #즉 python에서는 private와 public을 __의 유무로 판단

  def __init__(self, givenID, givenName):
      #id와 name은 인스턴스에 종속되기에 self.이라는 namespace
      self.__id = givenID
      self.__name = givenName
      #countStudent는 class전체에 대해 존재하기에 Student.
      Student.countStudent += 1
  def setId(self, givenID):
     self.__id = givenID
  def getID(self):
     return self.__id
  def setName(self, ginveName):
     self.__name = ginveName
  def getName(self):
     return self.__name
  #__str__를 class내부에 정의하면 print(인스턴스)로 출력가능
  def __str__(self):
     msg = "id: {}, name = {}".format(self.__id, self.__name)
     return msg
  #self파라미터는 인스턴스를 파라미터로 넘겨줘야할 떄 사용
  #but countStudent는 인스턴스 단위가 아니므로 파라미터에 self가 없음
  def getNumOfStudent():
     return Student.__countStudent
  

#상속
#python에서 상속은 괄호에 base class를 명시하면 됨
class GraduatedStudent(Student):
  def __init__(self, givenID, givenName, givenYear):
      self.__graduatedYear = givenYear
      #자식이 부모에게 접근할때는 super()를 사용
      super().__init__(givenID, givenName)
  #부모의 함수를 오버라이딩해서 자식에서 재선언
  def __str__(self):
   msg = super().__set__() + ", graduation:{}".format(self.__graduatedYear)
   return msg
  
#포함관계
#학과는 학생 객체를 변수로 가지는 상위 class로 표현될 수 있음, but 상속관계는 아님
class Department:
  def __init__(self):
     memberStudent = Student()

