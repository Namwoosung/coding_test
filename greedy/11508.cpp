//11508
//2+1����

//greedy
//���Ϲ޴� ���� 3 ���� �� ���� ���� ���� => ��¶�� ������ �޴� ��ǰ�� ���� ���� �������� �޴� ���� ����
//������ �������� �Ѵ��� ������� ����
#include <iostream>
#include <algorithm>

using namespace std;

int main() {
	int num;
	cin >> num;
	int* price = new int[num];
	for (int i = 0; i < num; i++) {
		cin >> price[i];
	}
	sort(price, price + num, greater<int>());

	int result = 0;
	for (int i = 0; i < num; i++) {
		if (((i + 1) % 3) == 0) continue;
		result += price[i];
	}
	cout << result;

	delete[] price;

	return 0;
}