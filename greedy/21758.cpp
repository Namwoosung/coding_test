#include <iostream>

using namespace std;

//3가지 case를 고려
//1. 꿀통이 가운데, 벌은 양 쪽 끝, 2. 꿀통 왼쪽 끝, 벌 한마리 오른쪽 끝, 한마리 중간, 3. 꿀통 오른쪽 끝, 벌 한마리 왼쪽 끝, 한마리 중간
int main() {
	//get input
	int N;
	cin >> N;
	int* honey = new int[N];
	for (int i = 0; i < N; i++) {
		cin >> honey[i];
	}


	//set maxIndex for 꿀통이 중간에 존재하는 경우
	int maxIndex = 1;
	for (int i = 1; i < N - 1; i++) {
		if (honey[maxIndex] < honey[i]) maxIndex = i;
	}
	int maxHoney = 0;
	//case 1. 꿀통 중간, 벌 양쪽 끝
	for (int i = 1; i < N - 1; i++) {
		maxHoney += honey[i];
	}
	maxHoney += honey[maxIndex];

	//case 2.꿀통 왼쪽
	//두 번째 벌의 index를 탐색
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

	//case 3.꿀통 오른쪽
	//두 번째 벌의 index를 탐색
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
	for (int i = 1; i < N; i++) {
		if (i < beeIndex) temp += honey[i];
		else if (i > beeIndex) temp += honey[i] * 2;
	}
	if (temp > maxHoney) maxHoney = temp;

	cout << maxHoney;

	delete[] honey;

	return 0;
}


/*
접근법은 동일 3가지 case로 나눠서 진행
코드가 더 깔끔함
차이점
1. 여기서는 전체 꿀의 합을 더하고 이후에 빼주면서 계산
2. 나는 2번째 벌의 위치를 수학적으로 찾았다면, 여기서는 모든 경우를 탐색하고 max값을 취함
#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;

int n;
long long v[100001], sum[100001];

int main()
{
	ios::sync_with_stdio(false);
	cin.tie(0); cout.tie(0);
	cin >> n;
	for(int i=1; i<=n; i++){
		cin >> v[i];
		sum[i]=v[i]+sum[i-1];
	}
	long long ans=0;
	for(int i=2; i<n; i++){ // i)
		ans=max(ans, sum[n]-v[1]-v[i]+sum[n]-sum[i]);
	}
	for(int i=2; i<n; i++){ // ii)
		ans=max(ans, sum[n]-v[n]-v[i]+sum[i-1]);
	}
	for(int i=2; i<n; i++){ // iii)
		ans=max(ans, sum[i]-v[1]+sum[n]-sum[i-1]-v[n]);
	}
	cout << ans << "\n";
	return 0;
}
*/