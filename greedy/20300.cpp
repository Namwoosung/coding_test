//20300
//서강근육맨

//M의 최소값이 되기위해서는 작은 순 + 큰 순으로 더해가며 최소값을 탐색
//숫자가 홀수라면 가장 큰 숫자를 따로 두고, 계산
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