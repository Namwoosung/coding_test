#include <iostream>

using namespace std;

//3���� case�� ���
//1. ������ ���, ���� �� �� ��, 2. ���� ���� ��, �� �Ѹ��� ������ ��, �Ѹ��� �߰�, 3. ���� ������ ��, �� �Ѹ��� ���� ��, �Ѹ��� �߰�
int main() {
	//get input
	int N;
	cin >> N;
	int* honey = new int[N];
	for (int i = 0; i < N; i++) {
		cin >> honey[i];
	}


	//set maxIndex
	int maxIndex = 1;
	for (int i = 1; i < N - 1; i++) {
		if (honey[maxIndex] < honey[i]) maxIndex = i;
	}

	int maxHoney = 0;
	//case 1. ���� �߰�, �� ���� ��
	for (int i = 1; i < N - 1; i++) {
		maxHoney += honey[i];
		if (maxIndex == i) maxHoney += honey[i];
	}

	//case 2.���� ����

	//�� ��° ���� index�� Ž��
	int beeIndex = N - 2;
	while (beeIndex > 1) {
		if (honey[beeIndex] >= honey[beeIndex - 1] * 2) {
			beeIndex--;
		}
		else {
			break;
		}
	}
	int temp = 0;
	for (int i = 0; i < N - 1; i++) {
		if (i < beeIndex) temp += honey[i] * 2;
		else if (i > beeIndex) temp += honey[i];
	}

	if (temp > maxHoney) maxHoney = temp;

	//case 3.���� ������
		//�� ��° ���� index�� Ž��
	beeIndex = 1;
	while (beeIndex < N - 2) {
		if (honey[beeIndex] >= honey[beeIndex + 1] * 2) {
			beeIndex++;
		}
		else {
			break;
		}
	}
	temp = 0;
	for (int i = 0; i < N - 1; i++) {
		if (i < beeIndex) temp += honey[i];
		else if (i > beeIndex) temp += honey[i] * 2;
	}

	if (temp > maxHoney) maxHoney = temp;

	cout << maxHoney;

	delete[] honey;

	return 0;
}