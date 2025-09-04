import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;

public class Solution {
	
	static class Bacteria{
		int x;
		int y;
		int cnt;
		int dir;
		boolean isMoved;
		int mixCnt;

		public Bacteria(int x, int y, int cnt, int dir) {
			this.x = x;
			this.y = y;
			this.cnt = cnt;
			this.dir = dir;
		}
	}
	
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	
	static int N;
	static int M;
	static int K;
	
	static Set<Bacteria> bacterias = new HashSet<>();
	static Map<Integer, Bacteria> bacteriaPos = new HashMap<>();
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		for(int testCase = 1; testCase <= T; testCase++) {
			bacterias.clear();
			
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken()); M = Integer.parseInt(st.nextToken()); K = Integer.parseInt(st.nextToken());
			for(int i = 0; i < K; i++) {
				st = new StringTokenizer(br.readLine());
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				int cnt = Integer.parseInt(st.nextToken());
				int dir = Integer.parseInt(st.nextToken()) - 1;
				bacterias.add(new Bacteria(x, y, cnt, dir));
			}
			
			for(int time = 0; time < M; time++) {
				bacteriaPos.clear();
				
				// 이동 먼저 처리
				Iterator<Bacteria> iter = bacterias.iterator();
				while(iter.hasNext()) {
					Bacteria now = iter.next();
					now.x += dx[now.dir];
					now.y += dy[now.dir];
					now.mixCnt = now.cnt;
					
					if(now.x == 0 || now.x == N-1 || now.y == 0 || now.y == N-1) {
						now.cnt /= 2;
						if(now.dir == 0) now.dir = 1;
						else if(now.dir == 1) now.dir = 0;
						else if(now.dir == 2) now.dir = 3;
						else now.dir = 2;
					}
					
					// 이동 결과 0이라면 삭제 처리
					if(now.cnt == 0) iter.remove();
					else{ // 아니라면 중복 여부를 검사 및 처리
						if(bacteriaPos.containsKey(now.x * N + now.y)) {
							Bacteria other = bacteriaPos.get(now.x * N + now.y);
							
							if(other.mixCnt > now.mixCnt) {
								other.cnt += now.cnt;
							}else {
								other.cnt += now.cnt;
								other.dir = now.dir;
								other.mixCnt = now.mixCnt;
							}
							iter.remove();
						}else {
							bacteriaPos.put(now.x * N + now.y, now);
						}
					}
				}
			}
			
			int result = 0;
			for(Bacteria now : bacterias) {
				result += now.cnt;
			}
			sb.append("#").append(testCase).append(" ").append(result).append("\n");
		}
		System.out.print(sb);
	}
}
