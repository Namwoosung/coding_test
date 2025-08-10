import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.BitSet;
import java.util.StringTokenizer;

public class Solution {
	static BitSet nowComb; // 현재 들어가 있는 재료들을 저장
	static BitSet[] notMatch; // 재료끼리 안 맞는 경우를 저장
	static int result;
	static int N;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		
		int T = Integer.parseInt(br.readLine());
		for(int testCase = 1; testCase <= T; testCase++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken()); int M = Integer.parseInt(st.nextToken());
			nowComb = new BitSet(N+1);
			notMatch = new BitSet[N+1];
			for(int i = 0; i <= N; i++) {
				notMatch[i] = new BitSet(N+1);
			}
			
			for(int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken()); int b = Integer.parseInt(st.nextToken());
				// 함께 존재할 수 없는 조합은 더 큰 인덱스 기준으로 저장
				// 추후 i 번째 인덱스를 검사한다면 0 ~ i-1 인덱스들을 검사하고, 들어갈 수 있으면 들어가게 하려고
				if( a < b) {
					notMatch[b].set(a);
				}else {
					notMatch[a].set(b);
				}
			}
			
			result = 0;
			
			dfs(1);
			
			sb.append("#").append(testCase).append(" ").append(result).append("\n");
		}
		
		System.out.print(sb);
	}
	
	static void dfs(int index) {
		if(index == N+1) { // 검사 끝에 도달
			result++;
			return;
		}
		
		boolean isPossible = true;
		for(int i = 1; i < index; i++) { // 이미 들어와 있는 애들 중에 나랑 맞지 않는 게 하나라도 있으면 불가능
			if(nowComb.get(i) && notMatch[index].get(i)) {
				isPossible = false;
				break;
			}
		}
		
		if(isPossible) {
			// 포함시키고 다음 호출
			nowComb.set(index);
			dfs(index + 1);
			nowComb.clear(index); //포함 안 시키고 호출
			dfs(index + 1);
		}else { 
			dfs(index+1);
		}
	}
}