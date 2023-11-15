//1476
//날짜계산
//brute-force로 풀이, E, S, M을 기준 값만큼 더하다가 모두 같아지면 해당 연도 계산 가능
#include <iostream>

using namespace std;

int main() {
	int E, S, M;
	cin >> E >> S >> M;

	//E와 S와 M이 같을 때 까지 반복, E, S, M중에서 가장 작은 값을 기준값만큼 더함
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