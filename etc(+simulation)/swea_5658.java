import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class Solution {
	static TreeSet<Integer> set = new TreeSet<>();
	
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		for(int testCase = 1; testCase <= T; testCase++) {
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken()); int K = Integer.parseInt(st.nextToken());
			String line = br.readLine();
			// 마짐가 인덱스 처리를 쉽게 하가 위해 line을 2배
			line += line;
			
			// 한 번의 길이
			int len = N / 4;
			
			// 값을 TreeSet에 저장
			set.clear();
			for(int i = 0; i < N; i++) {
				set.add(Integer.parseInt(line.substring(i, i  + len), 16));
			}
			
			int result = Integer.MAX_VALUE;
			for(int i = 0; i < K; i++) {
				result = set.lower(result);
			}
			sb.append("#").append(testCase).append(" ").append(result).append("\n");
		}
		System.out.print(sb);
	}
}