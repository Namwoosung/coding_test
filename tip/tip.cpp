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

int main()
{
	float z = Plus(13.2, 12.5);
	cout << z;
}


//pointer ����
/*
pointer�� �ּ� ���� ����Ű�� ����
int *p = NULL;
int num = 15;
p = &num;
�� ���� �����ϸ� p�� num�̶�� ������ �ּҸ� �����ϴ� ������ ��, *p�� ���� �����ϸ� p��� �����ͺ����� ������ �޸𸮿� ����� ���� ����

int�� ������ 4byte ũ��, double�� ������ 8byte ũ�⸦ �Ҵ�޵��� pointer ������ �Ҵ�޴� ũ�Ⱑ ���������� -> 32bit �ý����̸� 4byte, 64bit �ý����̸� 8byte

pointer������ �޸� �ּҸ� �����ϴ� �����ε�, �׷� �� pointer ������ int *, double *�� ���� �ڷ����� ���� �޶����°�?
int�� �ش� �޸𸮺��� 4byte, double�� �ش� �޸𸮺��� 8byte�� �о�ߵǴ� �Ͱ� ���� �ڷ��� ���� �����ϴ� �޸�ũ�Ⱑ �ٸ��⿡ pointer������ �ڷ����� ���� �о�ߵǴ� ������ �޶����� ����

(*p)++ //�̷��� �ϸ� p�� �����ϴ� ���� �����ϴ� ���� ����
*p++ //�̷��� �ϸ� �ȵ�, ���� �����ڴ� �������� �����ں��� �켱������ �ʱ⿡, p�� �޸𸮰��� �����ǰ� ���Ŀ� ������ -> �׷��� �����Ⱚ�� ��µ� ��


�߰��� �迭�� pointer������
int arr[5] = {1, 2, 3, 4, 5};
�̷��� �����ϸ� arr�̶�� ������ arr[0]�� ����� �޸� �ּҸ� ����Ų��.
arr++�� �����ϸ� arr�� int�� pointer�����̱⿡ arr[0]�� ���� 4byte�ּҸ� ������ arr[1]�� �����ϴ� ��

�߰��� ��ü�� �����ϴ� ����� �ش� calss type������ .���� �����ϴ� ��İ� class type�� pointer ������ ->�� �����Ѵ� ����� �ִ�.
class Test{
public:
	int a;
	int b;
};

int main(){
	Test T;
	Test* pT = &T;
}
���� ���� ����Ǿ� ������ T.a, (&T)->a, pT->a, (*pT).a ��� ��� ������ ���� ����̴�.

*/




//call by value, reference, pointer ����
//call by value ����
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
Swpa�� �ص� a�� b�� �ٲ��� �ʴ� ����
���� a�� b�� ����Ǹ� �ش� ���� Stack�� ����, ���� Swap�� ȣ�� -> �׷� Swap�� ���������� x�� y, tmp�� ����ǰ� �ش� ������ b�Ʒ��� ���̰� ��
-> ���⼭ x�� y�� a�� b�� ���� �����ؼ� ������ ��, ���� �ٸ� �޸� ������ �Ҵ�Ǿ� ����
=> �� x�� y�� ���� �����ϴ��� �ش� �޸� ���������� ��ȭ�� ������ �� a�� b�� ���� �״��
*/

//call by reference ���� <- C���� �������� �ʰ�, C++�� �߰��� ����
void Swap(int &x, int &y) {
	int tmp = x;
	x = y;
	y = tmp;
}

/*
call by reference�� ��Ī�� ������ �����̴�.
int main(){
	int a = 7;
	int &b = a;
}
���� ���� �����ϸ� b�� a�� ������ �޸𸮸� ����ϰ�, ������ ���� ������ �� �״�� ��Ī�� ������ �Ѵ�
���� �ϳ��� ���� �ٲ� 2���� ������ �޸𸮸� ����ϴ� �����ϰ� ����ȴ�.
�� call by reference�� �Ķ���ͷ� �޴� ���� ���� stack�� �Ҵ�Ǵ� ���� �ƴ϶� �Ѿ�� ���� ��Ī���� ������ ������ �޸� ������ ���´�
���� �ش� �Լ� �������� ������ main�Լ��� a�� b���� ������ �ִ� ��
*/

//call by pointer ���� <- ���� C���� call by reference�� ������ ���
void Swap(int* x, int* y) {
	int tmp = *x;
	*x = *y;
	*y = tmp;
}
/*
���������ϸ� call by value�� ���� �Ķ���ͷ� �Ѿ�� ���� �����ؼ� ���� stack�� �����ϴ� ���� ����
�׷��� ���⼭�� �޸� ���� �޾Ƽ� �ش� �޸� ���� ���� pointer������ stack�� ����
�� main���� a�� b�� �ּҸ� �Ķ���ͷ� �ѱ�� stack�� int *�� ���� x�� y�� �Ʒ��� ���� ����� �ű⿡ a�� b�� �ּҸ� ����
���� x�� y�� �޸𸮸� �� ������ �ش� ���� �����ϸ�, ���� main�� �ִ� a�� b�� ���� ����Ǵ� ��
*/




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


/* �ڷᱸ�� Ư¡ ��
C++ �ڷᱸ���� list, vector, stack, queue�� ����
list�� double linked list����, vector�� �迭�� ����(���� �Ҵ� ������), stack�� queue�� ��ǻ� �迭�� ��?
list�� �߰��� ���԰� ������ ���� ��� ����ϴ� �ڷᱸ��
vector�� ���԰� ������ �������� �������� �ַ� �̷������, memory access�� ���� ��쿡 ����ϴ� �ڷᱸ��
�޸� �������� vector�� list�߿� vector�� �� ȿ�����̴�
*/



//default parameter
//����Ʈ ���� �ڿ������� ��������� �� -> ���� �����ϰ�, �ڴ� �������� �ʴ� ���� �Ұ���
int sum(int a, b = 10) {
	return a + b;
}

int main(){
	int a = 5;
	cout << sum(a) << endl; //���� �Ķ���͸� �ѱ� ��, �ϳ��� �ѱ�� b�� default������ ������

	return 0;
}



//local�� global variable
/*
global�� local�� ���� �̸��� ������ �����ϸ� local�� ���� Ž����
�� ���� �������� Ž���س����� �ش� ������ ã��, access��
*/




//��ʹ� �޸𸮰������� ���� ��ȿ����, but ���α׷��Ӱ� �����ϱ�� �� ������ �� ����



//C++���� sort��� <- ���߿� vector ���İ�, 3��° ���ڷ� comapre �Լ��� �ѱ�� ��ĵ� �Բ� ����
#icnlude <algorithm>

int arr[10] = { 1, 2, 3, };
sort(arr, arr + 10); //��������



//date_5���� ������ ��