//20300
//����������

//M�� �ּҰ��� �Ǳ����ؼ��� ���� �� + ū ������ ���ذ��� �ּҰ��� Ž��
//���ڰ� Ȧ����� ���� ū ���ڸ� ���� �ΰ�, ���
#include <iostream>
#include <algorithm>

using namespace std;

int main() {
	int num;
	cin >> num;

	long long* M = new long long[num];

	for (int i = 0; i < num; i++) {
		cin >> M[i];
	}

	sort(M, M + num);

	long long result = 0;

	if (num % 2 == 1) {
		result = M[num - 1];
		for (int i = 0; i < num / 2; i++) {
			if (result < M[i] + M[num - 2 - i]) {
				result = M[i] + M[num - 2 - i];
			}
		}
	}
	else {
		for (int i = 0; i < num / 2; i++) {
			if (result < M[i] + M[num - 1 - i]) {
				result = M[i] + M[num - 1 - i];
			}
		}
	}

	cout << result;

	delete[] M;

	return 0;
}