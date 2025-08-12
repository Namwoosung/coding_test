import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	static int N;
	static int L;
	static int[][] food;
	static int result;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		for(int testCase = 1; testCase <= T; testCase++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken()); L = Integer.parseInt(st.nextToken());
			
			food = new int[N][2];
			for(int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				food[i][0] = Integer.parseInt(st.nextToken());
				food[i][1] = Integer.parseInt(st.nextToken());
			}
			
			result = 0;
			
			for(int i = 1; i <= N; i++) {
				makeCombination(i);
			}
			
			
			sb.append("#").append(testCase).append(" ").append(result).append("\n");
		}
		System.out.print(sb);
	}
	
	// nCr의 조합을 생성
	static void makeCombination(int r) {
		int[] comb = new int[N];
		// 뒤에서 부터 r 개수만큼 1 채우기
		for(int i = 0; i < r; i++) {
			comb[N-1-i] = 1;
		}
		
		boolean flag = true;
		int stand;
		while(flag) {
			// 조합 생성 가능이면, 현재 조합대로 재료를 계산하고 결과 update
			int cal = 0;
			int score = 0;
			for(int i = 0; i < N; i++) {
				if(comb[i] == 1) {
					score += food[i][0];
					cal += food[i][1];
				}
			}
			if(cal <= L) result = Math.max(score, result);
			
			//다음 조합 생성
			stand = N-2;
			while(comb[stand] >= comb[stand + 1]) {
				stand--;
				
				if(stand <= -1) { //내림차순이 되면 종료
					flag = false;
					break;
				}
			}
			
			if(flag) {
				int change = 0;
				for(int i = N-1; i > stand; i--) {
					if(comb[stand] < comb[i]) {
						change = i;
						break;
					}
				}
				
				// 위치 변환
				int temp = comb[stand];
				comb[stand] = comb[change];
				comb[change] = temp;
				
				// stand 뒤 쪽 부분을 다시 오름차순으로 하게 하기 위해 reverse
				int s = stand + 1;
				int e = N-1;
				while(s < e) {
					temp = comb[s];
					comb[s] = comb[e];
					comb[e] = temp;
					
					s++;
					e--;
				}
			}
		}
	}
}
