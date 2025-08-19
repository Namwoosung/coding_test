import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Solution {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		// 먼저 제곱 수를 미리 저장
		List<Long> powNum = new ArrayList<>();
		long num = 0;
		while(num * num <= 1000000000000L) {
			powNum.add(num * num);
			num++;
		}
		
		int T = Integer.parseInt(br.readLine());
		for(int testCase = 1; testCase <= T; testCase++) {
			long N = Long.parseLong(br.readLine());
			
			long result = 0;
			long stand = 0;
			while(N != 2) {
				stand = (int) Math.sqrt(N); // N기준 자기 이하의 제곱수
				if(stand * stand == N) { // 제곱수와 일치하는 경우
					N = (long)Math.sqrt(N);
					result++;
				}else { //제곱수가 아닌 경우 -> 자기 이상의 제곱수로 만들어줌
					stand++;
					result += (stand * stand) - N;
					N = stand * stand;
				}
			}
			sb.append("#").append(testCase).append(" ").append(result).append("\n");
		}
		System.out.print(sb);
	}
}