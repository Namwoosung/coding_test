//1748
//�� �̾��1

//my code
//�ڸ����� ���� �ľ��ϰ�, �ִ� �ڸ��� �� ������ ���������� ����� �����ϴ� ���
//�ִ� �ڸ��� ���޽� ���� ���� remain�� ���� �ش� ����ŭ �ڸ����� ���ϰ� ������
#include <iostream>
#include <string>
#include <cmath>

using namespace std;

int main() {
	string num;
	cin >> num;
	int size = num.length();

	int result = 0;
	for (int i = 0; i < size; i++) {
		if (i < size - 1) {
			result += pow(10, i) * 9 * (i + 1);
		}
		else {
			int remain = stoi(num);
			remain -= pow(10, size - 1);
			remain++;

			result += remain * (i + 1);
		}
	}

	cout << result;
}

/*
�� ���� �ڵ�
�̰� �� �ڸ����� �������� �ش� �ڸ����� ��ŭ �ݺ��ż� ������ �� ����� ��
���� 120�̶��ϸ�
���� �ڸ��� 120��
���� �ڸ��� 111��
���� �ڸ��� 21��
���ڿ����� �̷��� ������ ���̹Ƿ� �� ���ؼ� ���
#include <iostream>

using namespace std;

int main() {
	int n, r;
	cin >> n;
	for (int i = 1; i <= n; i *= 10) {
			r += n - i + 1;
	}
	cout << r << endl;

	return 0;
}
*/