//�⺻ �����
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

//�� ��ȯ�� �̿��� �Ǽ� �ݿø�
#include <iostream>

using namespace std;

int main()
{
	float value;
	cout << "�ݿø��ϰ���� �Ǽ��� �Է����ֽʽÿ�.";
	cin >> value;

	//�Ҽ� ù ° �ڸ��� �߿��ϹǷ� 10�� ���ϰ� int ����ȯ�� �ϰ� 10�� ������ �Ҽ� ù��¥�� �Ǻ� ����
	float value_test = value * 10;
	int value_one = (int)value_test % 10;

	//ù��¥���� 5���� ������ �׳� ����ȯ, 5���� ũ�� ����ȯ �ϰ� +1
	if (value_one < 5)
	{
		cout << int(value);
	}
	else
	{
		cout << int(value) + 1;
	}

}

//���� ����
#include <iostream>
#include <ctime>

int main() {
	srand(time(NULL));
	int ran;
	ran = rand() % 1000 + 100;
	return 0;
}

//�Լ� �����ε�
//type�� �ٸ� ��� �����ε� ����
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

//call by value, reference, pointer Ȯ���� ������ ��!!!!!!


//�ּҰ������ �ִ����� ���ϴ� �˰��� ������ ��!!


//�迭 �ʱ�ȭ
int arr[100] = { 0, }; //��� 0���� �ʱ�ȭ
fill_n(arr2, 100, 1) //��� 1�� �ʱ�ȭ


///file IO <- .getline() .get()�� file metho�� ���߿� �ʿ��� �� ã�Ƽ� ����
#include <iostream>
#include <fstream>
#include <string>

using namespace std;

int main()
{
	//ofstream�� write��
	ofstream fout;
	fout.open("user.txt");

	while (true)
	{
		string user_name;
		cout << "�̸��� �Է����ֽʽÿ�.quit�� �Է��ϸ� ����˴ϴ�." << endl;
		cin >> user_name;

		if (user_name == "quit")
		{
			break;
		}

		fout << user_name << endl;
	}
	fout.close();

	//ifstream�� read��
	ifstream in("user.txt");

	char user[1000];

	while (in.getline(user, 1000))
	{
		cout << user << endl;
	}
	in.close();
}
*/