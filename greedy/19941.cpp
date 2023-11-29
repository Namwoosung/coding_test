//19941
//�ܹ��� �й�

#include <iostream>
#include <string>

using namespace std;

//��� ����� �� ���� �������� �ܹ��Ÿ� ������ �ִ밪
//ex) �ڽ� ���� ���ʺ��� Ž���� �ܹ��Ÿ� �Դ´�
int main() {
	int N, K;
	cin >> N >> K;

	string order;
	cin >> order;

	int result = 0;
	int start = 0, end = 0;
	for (int i = 0; i < order.size(); i++) {
		//����̶�� �ֺ� Ž�� ����
		if (order[i] == 'P') {
			//Ž�� ��ġ ����
			start = ((i - K) > 0) ? (i - K) : 0;
			end = ((i + K) < order.size() - 1) ? (i + K) : order.size() - 1;
			//���ʺ��� Ž���ϴٰ� �ܹ��Ű� ������ �԰� ���Ƿ� ���ڿ� ����
			while (start <= end) {
				if (order[start] == 'H') {
					result++;
					order.replace(start, 1, "O");
					break;
				}
				start++;
			}
		}
	}

	cout << result;

	return 0;
}