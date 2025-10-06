import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		
		String[] arr = new String[1000];
		
		for(int testCase = 1; testCase <= T; testCase++) {
			int N = Integer.parseInt(br.readLine());
			st = new StringTokenizer(br.readLine());
			for(int i = 0; i < N; i++) {
				arr[i] = st.nextToken();
			}
			
			sb.append("#").append(testCase).append(" ");
			
			int p1 = 0;
			int p2 = N / 2;
			if(N % 2 == 1) p2++;
			
			for(int i = 0; i < N / 2; i ++) {
				sb.append(arr[p1]).append(" ").append(arr[p2]).append(" ");
				p1++;
				p2++;
			}
			if(N % 2 == 1) {
				sb.append(arr[p1]).append(" ");
			}
			
			sb.append("\n");
		}
		System.out.print(sb);
	}
}
