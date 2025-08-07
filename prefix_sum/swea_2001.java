import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		for(int testCase = 1; testCase <= T; testCase++) {
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken()); int M = Integer.parseInt(st.nextToken());
			int[][] sum = new int[N][N]; // 입력 받을 때 누적합 배열로 받음
			
			// 첫번째 줄 입력받음
			st = new StringTokenizer(br.readLine());
			sum[0][0] = Integer.parseInt(st.nextToken());
			for(int j = 1; j < N; j++) {
				sum[0][j] = sum[0][j-1] + Integer.parseInt(st.nextToken());
			}
			
			// 나머지 줄을 입력받음
			for(int i = 1; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j = 0; j < N; j++) {
					if(j == 0) {
						sum[i][j] = sum[i-1][j] + Integer.parseInt(st.nextToken());
					}else {
						sum[i][j] = sum[i-1][j] + sum[i][j-1] - sum[i-1][j-1] + Integer.parseInt(st.nextToken());
					}
				}
			}
			
			// 가중합을 이용해서 최대값탐색
			// M-1~N까지 하나의 반복문으로 처리하고, if로 검사해도 되지만, if문 나누기 싫어서 M-1인 경우와 M~N인 경우를 따로 처리
			int result = 0;

			// 1. M-1,M-1인 case
			result = Math.max(result, sum[M-1][M-1]); 
			
			// 2. i가 M-1인 case
			for(int j = M; j < N; j++) {
				result = Math.max(result, sum[M-1][j] - sum[M-1][j-M]);
			}
			
			// 3. j가 M-1인 case
			for(int i = M; i < N; i++) {
				result = Math.max(result, sum[i][M-1] - sum[i-M][M-1]);
			}
			
			// 4. i와 j 모두 M이
			for(int i = M; i < N; i++) {
				for(int j = M; j < N; j++) {
					result = Math.max(result, sum[i][j] - sum[i-M][j] - sum[i][j-M] + sum[i-M][j-M]);
				}
			}
			
			
			sb.append("#").append(testCase).append(" ").append(result).append("\n");
		}
		System.out.print(sb);
	}
}
