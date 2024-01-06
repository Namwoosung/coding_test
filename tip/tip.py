"""
python input에서 sys module활용
"""
#코테에서 sys를 사용해 input을 받는 이유와 사용법 찾아서 notion에 정리할 것



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

