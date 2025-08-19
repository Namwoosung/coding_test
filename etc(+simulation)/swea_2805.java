import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Solution {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		for(int testCase = 1; testCase <= T; testCase++) {
			int N = Integer.parseInt(br.readLine());
			int mid = N / 2;
			int result = 0;
			for(int i = 0; i < N; i++) {
				String line = br.readLine();
				for(int j = 0; j < N; j++) {
					if(Math.abs(i - mid) + Math.abs(j - mid) <= mid) result += line.charAt(j) - '0';
				}
			}
			sb.append("#").append(testCase).append(" ").append(result).append("\n");
		}
		System.out.print(sb);
	}
}
