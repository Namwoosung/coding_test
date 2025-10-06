import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	static final long MOD = 1234567891L;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		
		long[] factorial = new long[1000001];
		factorial[0] = 1;
		for(int i = 1; i <= 1000000; i++) {
			factorial[i] = (factorial[i-1] * i) % MOD;
		}
		
		for(int testCase = 1; testCase <= T; testCase++) {
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int R = Integer.parseInt(st.nextToken());
			
			long result = factorial[N] % MOD;
			result *= calPow((factorial[N - R] * factorial[R]) % MOD, MOD-2) % MOD;
			result = result % MOD;
			
			sb.append("#").append(testCase).append(" ").append(result).append("\n");
		}
		System.out.print(sb);
	}
	
	static long calPow(long num, long pow) {
		if(pow == 0) return 1;
		long half = calPow(num, pow / 2);
		long result = (half * half) % MOD;
		if(pow % 2 == 1) result = (result * num) % MOD;
		
		return result;
	}
}
