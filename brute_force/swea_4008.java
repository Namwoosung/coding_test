import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	static int N;
	static int[] oper;
	static int[] num;
	static int maxResult;
	static int minResult;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		for(int testCase = 1; testCase <= T; testCase++) {
			N = Integer.parseInt(br.readLine());
			
			oper = new int[N-1];
			num = new int[N];
			
			// 연산자를 4번 받음 => 만약 2 1 0 1 이라면 [0, 0, 1, 3]과 같이 저장
			int index = 0;
			st = new StringTokenizer(br.readLine());
			for(int i = 0; i < 4; i++) {
				int now = Integer.parseInt(st.nextToken());
				for(int j = 0; j < now; j++) {
					oper[index] = i;
					index++;
				}
			}
			
			st = new StringTokenizer(br.readLine());
			for(int i = 0; i < N; i++) {
				num[i] = Integer.parseInt(st.nextToken());
			}
			
			maxResult = Integer.MIN_VALUE;
			minResult = Integer.MAX_VALUE;
			
			nextPermutation();

			int result = maxResult - minResult;

			sb.append("#").append(testCase).append(" ").append(result).append("\n");
		}
		System.out.print(sb);
	}
	
	// 순열을 nextPermutation으로 검사(중복되는 순열은 배제하기 위함)
	static void nextPermutation() {
		// 기존 배열로 먼저 계산 한 번 진행
		calResult();
		
		// nextPermutation을 진행
		while(true) {
			//값이 작아지는 부분을 탐색, 해당 지점이 기준이 됨
			int stand = N - 3;
			while(oper[stand] >= oper[stand+1]) {
				stand--;
			
				if(stand < 0) { // 순열 전체를 검사했으면 종료
					return;
				}
			}
			
			// 맨 뒤에서 부터 탐색, 기준보다 큰 값을 target으로 설정
			int target = 0;
			for(int i = N - 2; i > stand; i--) {
				if(oper[stand] < oper[i]) {
					target = i; break;
				}
			}
			
			// 값 바꾸기
			int temp = oper[stand]; oper[stand] = oper[target]; oper[target] = temp;
			
			//stand 뒤를 오름차순으로 변경
			int s = stand + 1; int e = N-2;
			while(s < e) {
				temp = oper[s]; oper[s] = oper[e]; oper[e] = temp;
				s++; e--;
			}
			// 만들어진 수열로 계산진행
			calResult();
		}
	}
	
	static void calResult() {
		int stand = num[0];
		
		for(int i = 0; i < N-1; i++) {
			int now = num[i+1];
			
			switch(oper[i]) {
			case 0:
				stand += now;
				break;
			case 1:
				stand -= now;
				break;
			case 2:
				stand *= now;
				break;
				
			case 3:
				stand /= now;
				break;
			}
		}
		
		maxResult = Math.max(stand, maxResult);
		minResult = Math.min(stand, minResult);
	}
}
