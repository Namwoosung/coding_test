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

int maid()
{
	float z = Plus(13.2, 12.5);
	cout << z;
}

//call by value, reference, pointer 확실히 정리할 것!!!!!!


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