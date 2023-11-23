//11508
//2+1세일

//greedy
//세일받는 것은 3 묶음 중 가장 낮은 가격 => 어쨋든 세일을 받는 물품을 제일 높은 가격으로 받는 것이 유리
//정렬을 역순으로 한다음 순서대로 결제
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