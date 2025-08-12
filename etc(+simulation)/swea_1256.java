import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.TreeSet;

public class Solution {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		
		for(int testCase = 1; testCase <= T; testCase++) {
			int K = Integer.parseInt(br.readLine());
			String line = br.readLine();
			
			TreeSet<String> ts = new TreeSet<String>();
			
			for(int i = 0; i < line.length(); i++) {
				ts.add(line.substring(i, line.length()));
			}
			
			
			
			sb.append("#").append(testCase).append(" ").append(ts.toArray()[K-1]).append("\n");
		}
		System.out.print(sb);
	}
}
