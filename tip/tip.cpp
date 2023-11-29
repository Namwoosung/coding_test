//기본 입출력
#include <iostream>

using namespace std;

int main() {
	int num1, num2;
	char sign;

	cin >> num1 >> num2;
	cin >> sign;

	switch (sign)
	{
	case '+':
		cout << num1 + num2;
		break;
	case '-':
		cout << num1 - num2;
		break;
	default:
		cout << num1 << num2;
		break;
	}

}

//형 변환을 이용한 실수 반올림
#include <iostream>

using namespace std;

int main()
{
	float value;
	cout << "반올림하고싶은 실수를 입력해주십시오.";
	cin >> value;

	//소수 첫 째 자리만 중요하므로 10을 곱하고 int 형변환을 하고 10을 나누면 소수 첫재짜리 판별 가능
	float value_test = value * 10;
	int value_one = (int)value_test % 10;

	//첫재짜리가 5보다 작으면 그냥 형변환, 5보다 크면 형변환 하고 +1
	if (value_one < 5)
	{
		cout << int(value);
	}
	else
	{
		cout << int(value) + 1;
	}

}

//난수 생성
#include <iostream>
#include <ctime>

int main() {
	srand(time(NULL));
	int ran;
	ran = rand() % 1000 + 100;
	return 0;
}

//함수 오버로드
//type이 다른 경우 오버로드 가능
#include <iostream>

using namespace std;

int Plus(int x, int y)
{
	return x + y;
}

float Plus(float x, float y)
{
	return x + y;
}

double Plus(double x, double y)
{
	return x + y;
}

int main()
{
	float z = Plus(13.2, 12.5);
	cout << z;
}


//pointer 개념
/*
pointer는 주소 값을 가르키는 변수
int *p = NULL;
int num = 15;
p = &num;
과 같이 선언하면 p는 num이라는 변수의 주소를 저장하는 변수인 것, *p와 같이 접근하면 p라는 포인터변수가 저장한 메모리에 저장된 값에 접근

int형 변수는 4byte 크기, double형 변수는 8byte 크기를 할당받듯이 pointer 변수도 할당받는 크기가 정해져있음 -> 32bit 시스템이면 4byte, 64bit 시스템이면 8byte

pointer변수는 메모리 주소를 저장하는 변수인데, 그럼 왜 pointer 변수는 int *, double *와 같이 자료형에 따라 달라지는가?
int는 해당 메모리부터 4byte, double은 해당 메모리부터 8byte를 읽어야되는 것과 같이 자료형 마다 점유하는 메모리크기가 다르기에 pointer변수도 자료형에 따라 읽어야되는 범위가 달라지기 떄문

(*p)++ //이렇게 하면 p가 참조하는 값이 증가하는 것이 맞음
*p++ //이렇게 하면 안됨, 참조 연산자는 후위증가 연산자보다 우선순위가 늦기에, p의 메모리값이 증가되고 이후에 참조됨 -> 그러면 쓰레기값이 출력될 것


추가로 배열도 pointer변수다
int arr[5] = {1, 2, 3, 4, 5};
이렇게 선언하면 arr이라는 변수는 arr[0]이 저장된 메모리 주소를 가르킨다.
arr++로 접근하면 arr은 int형 pointer변수이기에 arr[0]의 다음 4byte주소를 가르켜 arr[1]에 접근하는 것

추가로 객체에 접근하는 방식은 해당 calss type변수에 .으로 접근하는 방식과 class type의 pointer 변수로 ->로 접근한느 방식이 있다.
class Test{
public:
	int a;
	int b;
};

int main(){
	Test T;
	Test* pT = &T;
}
위와 같이 선언되어 있으면 T.a, (&T)->a, pT->a, (*pT).a 모두 사용 가능한 접근 방식이다.

*/




//call by value, reference, pointer 개념
//call by value 예제
void Swap(int x, int y) {
	int tmp = x;
	x = y;
	y = tmp;
}

int main() {
	int a = 10;
	int b = 10;

	Swap(a, b);

	cout << "a: " << a << endl;
	cout << "b: " << b << endl;
}
/*
Swpa을 해도 a와 b가 바뀌지 않는 이유
먼저 a와 b가 선언되면 해당 값은 Stack에 쌓임, 이후 Swap이 호출 -> 그럼 Swap내 지역변수인 x와 y, tmp가 선언되고 해당 값들은 b아래에 쌓이게 됨
-> 여기서 x와 y는 a와 b의 값을 복사해서 저장할 뿐, 서로 다른 메모리 영역에 할당되어 있음
=> 즉 x와 y의 값을 변경하더라도 해당 메모리 영역에서의 변화가 존재할 뿐 a와 b의 값은 그대로
*/

//call by reference 예제 <- C에는 존재하지 않고, C++에 추가된 개념
void Swap(int &x, int &y) {
	int tmp = x;
	x = y;
	y = tmp;
}

/*
call by reference는 별칭과 동일한 개념이다.
int main(){
	int a = 7;
	int &b = a;
}
위와 같이 선언하면 b는 a와 동일한 메모리를 사용하고, 동일한 값을 저장한 말 그대로 별칭의 역할을 한다
따라서 하나의 값만 바뀌어도 2개는 동일한 메모리를 사용하니 동일하게 변경된다.
즉 call by reference는 파라미터로 받는 값이 새로 stack에 할당되는 것이 아니라 넘어온 값의 별칭으로 완전히 동일한 메모리 영역을 갖는다
따라서 해당 함수 내에서의 변경이 main함수의 a와 b에도 영향을 주는 것
*/

//call by pointer 예제 <- 기존 C에서 call by reference와 동일한 방식
void Swap(int* x, int* y) {
	int tmp = *x;
	*x = *y;
	*y = tmp;
}
/*
엄밀히말하면 call by value와 같이 파라미터로 넘어온 값을 복사해서 새로 stack에 저장하는 것은 동일
그러나 여기서는 메모리 값을 받아서 해당 메모리 값을 갖는 pointer변수를 stack에 저장
즉 main에서 a와 b의 주소를 파라미터로 넘기면 stack에 int *형 변수 x와 y가 아래에 새로 생기고 거기에 a와 b의 주소를 저장
따라서 x와 y로 메모리를 역 참조해 해당 값을 변경하면, 실제 main에 있는 a와 b의 값이 변경되는 것
*/




//최소공배수랑 최대공약수 구하는 알고리즘 정리할 것!!


//배열 초기화
int arr[100] = { 0, }; //모두 0으로 초기화
fill_n(arr2, 100, 1) //모두 1로 초기화


///file IO <- .getline() .get()등 file metho는 나중에 필요할 때 찾아서 정리
#include <iostream>
#include <fstream>
#include <string>

using namespace std;

int main()
{
	//ofstream은 write용
	ofstream fout;
	fout.open("user.txt");

	while (true)
	{
		string user_name;
		cout << "이름을 입력해주십시오.quit를 입력하면 종료됩니다." << endl;
		cin >> user_name;

		if (user_name == "quit")
		{
			break;
		}

		fout << user_name << endl;
	}
	fout.close();

	//ifstream은 read용
	ifstream in("user.txt");

	char user[1000];

	while (in.getline(user, 1000))
	{
		cout << user << endl;
	}
	in.close();
}
*/


/* 자료구조 특징 비교
C++ 자료구조로 list, vector, stack, queue등 존재
list는 double linked list형태, vector는 배열의 형태(동적 할당 가능한), stack과 queue는 사실상 배열일 듯?
list는 중간에 삽입과 삭제가 많은 경우 사용하는 자료구조
vector는 삽입과 삭제가 마지막과 끝에서만 주로 이루어지고, memory access가 많은 경우에 사용하는 자료구조
메모리 관점에서 vector와 list중에 vector가 더 효율적이다
*/



//default parameter
//디폴트 값은 뒤에서부터 지정해줘야 함 -> 앞은 지정하고, 뒤는 지정하지 않는 것은 불가능
int sum(int a, b = 10) {
	return a + b;
}

int main(){
	int a = 5;
	cout << sum(a) << endl; //실제 파라미터를 넘길 때, 하나만 넘기면 b는 default값으로 지정됨

	return 0;
}



//local과 global variable
/*
global과 local에 같은 이름의 변수가 존재하면 local이 먼저 탐색됨
즉 좁은 구역부터 탐색해나가며 해당 변수를 찾고, access됨
*/




//재귀는 메모리관점에서 보통 비효율적, but 프로그래머가 이해하기는 더 용이할 수 있음



//C++에서 sort방법 <- 나중에 vector 정렬과, 3번째 인자로 comapre 함수를 넘기는 방식도 함께 정리
#icnlude <algorithm>

int arr[10] = { 1, 2, 3, };
sort(arr, arr + 10); //오름차순



//date_5까지 정리한 것