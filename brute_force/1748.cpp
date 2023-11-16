//1748
//수 이어쓰기1

//my code
//자리수를 먼저 파악하고, 최대 자리수 전 까지는 수학적으로 계산이 가능하니 계산
//최대 자리수 도달시 현재 남은 remain을 구해 해당 수만큼 자리수를 곱하고 더해줌
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
더 좋은 코드
이건 각 자리수를 기준으로 해당 자리수가 얼만큼 반복돼서 나오는 지 계산한 것
예로 120이라하면
일의 자리는 120번
십의 자리는 111번
백의 자리는 21번
문자열에서 이렇게 점유될 것이므로 다 더해서 계산
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