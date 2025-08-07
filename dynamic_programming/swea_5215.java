import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Solution {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		
		for(int testCase = 1; testCase <= T; testCase++) {
			st = new StringTokenizer(br.readLine());
			int N  = Integer.parseInt(st.nextToken()); int L = Integer.parseInt(st.nextToken());
			
			
			int[][] arr = new int[N+1][2]; // 햄버거 재료들을 저장
			for(int i = 1; i <= N; i++) {
				st = new StringTokenizer(br.readLine());
				arr[i][0]= Integer.parseInt(st.nextToken()); arr[i][1] = Integer.parseInt(st.nextToken());
			}
			
			int[][] dp = new int[N+1][L+1]; //dp 배열
			
			for(int j = arr[1][1]; j <= L; j++) { // 첫 번째 item은 먼저 update
				dp[1][j] = arr[1][0];
			}
			for(int i = 2 ; i <= N; i++) { //2번째 item부터 마지막까지 dp를 통한 update
				for(int j = 0; j < arr[i][1]; j ++) { // 현재 재로 칼로리 보다 작은 경우는 무조건 넣을 수 없
					dp[i][j] = dp[i-1][j];
				}
				for(int j = arr[i][1]; j <= L; j++) {
					dp[i][j] = Math.max(dp[i-1][j], dp[i-1][j-arr[i][1]] + arr[i][0]); // 현재 재료를 넣은 경우와 안 넣은 경우 중 큰 경우로 updates
				}
			}
			
			sb.append("#").append(testCase).append(" ").append(dp[N][L]).append("\n");
			
		}
		System.out.print(sb);
	}
}
