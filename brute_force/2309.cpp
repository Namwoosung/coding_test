//2309
//�ϰ�������
//brute-force�� Ǯ��
//�� ���ϰ� 100���� ���̸� ���� remain�� index�� ���� ���
#include <iostream>
#include <algorithm>

using namespace std;

int main() {
	int num[9];
	int total = 0;
	for (int i = 0; i < 9; i++) {
		cin >> num[i];
		total += num[i];
	}

	sort(num, num + 9);

	int remain = total - 100;
	bool found = false;

	for (int i = 0; i < 8; i++) {
		for (int j = i + 1; j < 9; j++) {
			if (num[i] + num[j] == remain) {
				found = true;
				for (int k = 0; k < 9; k++) {
					if ((k != i) && (k != j)) {
						cout << num[k] << endl;
					}
				}
			}
			if (found) break;
		}
		if (found) break;
	}

	return 0;
}