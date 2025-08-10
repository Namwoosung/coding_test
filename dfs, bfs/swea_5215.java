// dp로 푸는 게 더 전형적이지만, 조합 연습을 위해 dfs로 푼 버전
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	static int[][] food;
	static int result;
	static int N;
	static int L;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		
		int T = Integer.parseInt(br.readLine());
		for(int testCase = 1; testCase <= T; testCase++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken()); L = Integer.parseInt(st.nextToken());
			
			food = new int[N][2];
			for(int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken()); int b = Integer.parseInt(st.nextToken());
				food[i][0] = a; food[i][1] = b;
			}
			
			result = 0;
			dfs(0, 0, 0);
			
			sb.append("#").append(testCase).append(" ").append(result).append("\n");
		}
		
		System.out.print(sb);
	}
	
	static void dfs(int index, int score, int cal) {
		if(index == N) { // 검사 끝 도달 시 result만 update
			result = Math.max(result, score);
			return;
		};
		
		if(cal + food[index][1] <= L) { // 포함 가능하면 넣어서 넘기
			dfs(index+1, score+food[index][0], cal+food[index][1]);
		}
		dfs(index+1, score, cal);
		
		
	}
}