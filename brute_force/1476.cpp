//1476
//��¥���
//brute-force�� Ǯ��, E, S, M�� ���� ����ŭ ���ϴٰ� ��� �������� �ش� ���� ��� ����
#include <iostream>

using namespace std;

int main() {
	int E, S, M;
	cin >> E >> S >> M;

	//E�� S�� M�� ���� �� ���� �ݺ�, E, S, M�߿��� ���� ���� ���� ���ذ���ŭ ����
	while ((E != S) || (S != M) || (E != M)) {
		if ((E <= S) && (E <= M)) {
			E += 15;
		}
		else if ((S <= E) && (S <= M)) {
			S += 28;
		}
		else {
			M += 19;
		}
	}

	cout << E;

	return 0;
}