#include <iostream>

using namespace std;

//3���� case�� ���
//1. ������ ���, ���� �� �� ��, 2. ���� ���� ��, �� �Ѹ��� ������ ��, �Ѹ��� �߰�, 3. ���� ������ ��, �� �Ѹ��� ���� ��, �Ѹ��� �߰�
int main() {
	//get input
	int N;
	cin >> N;
	int* honey = new int[N];
	for (int i = 0; i < N; i++) {
		cin >> honey[i];
	}


	//set maxIndex for ������ �߰��� �����ϴ� ���
	int maxIndex = 1;
	for (int i = 1; i < N - 1; i++) {
		if (honey[maxIndex] < honey[i]) maxIndex = i;
	}
	int maxHoney = 0;
	//case 1. ���� �߰�, �� ���� ��
	for (int i = 1; i < N - 1; i++) {
		maxHoney += honey[i];
	}
	maxHoney += honey[maxIndex];

	//case 2.���� ����
	//�� ��° ���� index�� Ž��
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

	//case 3.���� ������
	//�� ��° ���� index�� Ž��
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
���ٹ��� ���� 3���� case�� ������ ����
�ڵ尡 �� �����
������
1. ���⼭�� ��ü ���� ���� ���ϰ� ���Ŀ� ���ָ鼭 ���
2. ���� 2��° ���� ��ġ�� ���������� ã�Ҵٸ�, ���⼭�� ��� ��츦 Ž���ϰ� max���� ����
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