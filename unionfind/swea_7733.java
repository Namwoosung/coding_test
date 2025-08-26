import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;

public class Solution {
	static int[] root = new int[10000];
	static int[][] board = new int[100][100];
	static int[] dx = {0, 0, 1, -1};
	static int[] dy = {1, -1, 0, 0};	
	static List<Integer>[] Pos = new ArrayList[101];
	static {
		for(int i = 1; i <= 100; i++) {
			Pos[i] = new ArrayList<>();
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		for(int testCase = 1; testCase <= T; testCase++) {
			int N = Integer.parseInt(br.readLine());
			// 이번 테케에서 사용할만큼 static 변수 초기화
			for(int i = 0; i < N * N; i++) {
				root[i] = -1;
			}
			for(int i = 1; i <= 100; i++) {
				Pos[i].clear();
			}
			
			
			// 입력값
			int min = Integer.MAX_VALUE;
			int max = Integer.MIN_VALUE; 
			for(int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j = 0; j < N; j++) {
					board[i][j] = Integer.parseInt(st.nextToken());
					Pos[board[i][j]].add(i * N + j); // 값별로 좌표를 미리 저장
					max = Math.max(max, board[i][j]);
					min = Math.min(min, board[i][j]);
				}
			}
			
			int result = 0;
			int groupCnt = 0;

			// 범위만 탐색
			for(int stand = max; stand >= min; stand--) {
				for(int pos : Pos[stand]) {
					
					// 본인 대표를 본인으로 설정
					root[pos] = pos;
					groupCnt++;
					
					// 4방탐색을 하고, 집합이 존재하면 해당 집합들과 union 시행
					for(int dir = 0; dir < 4; dir++) {
						int nx = pos/N + dx[dir];
						int ny = pos%N + dy[dir];
						if(!(nx >= 0 && nx < N && ny >= 0 && ny < N)) continue;
						if(root[nx*N+ny] == -1) continue;
						
						// 더 작은 집합을 더 큰 집합으로 병합
						if(union(pos, nx*N+ny)) groupCnt--;
					}
				}
				result = Math.max(result, groupCnt);
			}
			sb.append("#").append(testCase).append(" ").append(result).append("\n");
		}
		System.out.print(sb);
	}
	
	static int find(int node) {
		if(root[node] == node) return node;
		
		root[node] = find(root[node]);
		return root[node];
	}
	
	// n1의 집합을 n2에 병합
	static boolean union(int n1, int n2) {
		int r1 = find(n1);
		int r2 = find(n2);
		if(r1 == r2) return false;
		
		root[r1] = r2;
		return true; 
	}
}