//19941
//햄버거 분배

#include <iostream>
#include <string>

using namespace std;

//모든 사람이 한 가지 기준으로 햄버거를 먹으면 최대값
//ex) 자신 기준 왼쪽부터 탐색해 햄버거를 먹는다
int main() {
	int N, K;
	cin >> N >> K;

	string order;
	cin >> order;

	int result = 0;
	int start = 0, end = 0;
	for (int i = 0; i < order.size(); i++) {
		//사람이라면 주변 탐색 시작
		if (order[i] == 'P') {
			//탐색 위치 지정
			start = ((i - K) > 0) ? (i - K) : 0;
			end = ((i + K) < order.size() - 1) ? (i + K) : order.size() - 1;
			//왼쪽부터 탐색하다가 햄버거가 있으면 먹고 임의로 문자열 변경
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