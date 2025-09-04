import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Solution {
	static final int END = 1000000;
	
	static class Pos{
		int x;
		int y;
		int cnt;

		public Pos(int x, int y, int cnt) {
			this.x = x;
			this.y = y;
			this.cnt = cnt;
		}
	}
	static Pos sPos;
	
	static int[][] board;
	static boolean[][] isVisited;
	static int[] dx = {0, 0, 1, -1};
	static int[] dy = {1, -1, 0, 0};
	
	static Deque<Pos> queue = new ArrayDeque<>();
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb= new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		for(int testCase = 1; testCase <= T; testCase++) {
			queue.clear();
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken()); int M = Integer.parseInt(st.nextToken());
			
			// 입력값을 int로 치환해서 저장
			board = new int[N][M];
			for(int i = 0; i < N; i++) {
				String line = br.readLine();
				for(int j = 0; j < M; j++) {
					if(line.charAt(j) == 'D') board[i][j] = END;
					else if(line.charAt(j) == '*') {
						queue.add(new Pos(i, j, 1));
						board[i][j] = Integer.MAX_VALUE;
					}
					else if(line.charAt(j) == 'X') board[i][j] = -1;
					else if(line.charAt(j) == 'S') {
						sPos = new Pos(i, j, 1);
						board[i][j] = Integer.MAX_VALUE;
					}
					else {
						board[i][j] = Integer.MAX_VALUE;
					}
				}
			}
			
			// 먼저 악마 행동을 먼저 저장
			while(!queue.isEmpty()) {
				Pos now = queue.poll();
				if(board[now.x][now.y] <= now.cnt) continue;
				board[now.x][now.y] = now.cnt;

				for(int dir = 0; dir < 4; dir++) {
					int nx = now.x + dx[dir]; int ny = now.y + dy[dir];
					if(!(nx >= 0 && nx < N && ny >= 0 && ny < M)) continue;
					if(board[nx][ny] == -1 || board[nx][ny] == END) continue;
					if(board[nx][ny] <= now.cnt + 1) continue;
					queue.add(new Pos(nx, ny, now.cnt+1));
				}
			}
			
			// 수연 행도 시작. 이동 하다가 현재 턴이 손아귀 턴 이후라면 이동 x
			int result = 0;
			queue.add(sPos);
			isVisited = new boolean[N][M];
			isVisited[sPos.x][sPos.y] = true;
			
			while(!queue.isEmpty()) {
				Pos now = queue.poll();
				if(board[now.x][now.y] == END) {
					result = now.cnt;
					break;
				}
				
				for(int dir = 0; dir < 4; dir++) {
					int nx = now.x + dx[dir]; int ny = now.y + dy[dir];
					if(!(nx >= 0 && nx < N && ny >= 0 && ny < M)) continue;
					if(isVisited[nx][ny]) continue;
					if(board[nx][ny] <= now.cnt + 1) continue; // 갈 수 없는 지역인 경우
					queue.add(new Pos(nx, ny, now.cnt+1));
					isVisited[nx][ny] = true;
				}
				
			}
			
			sb.append("#").append(testCase).append(" ");
			if(result == 0) sb.append("GAME OVER\n");
			else sb.append(result - 1).append("\n");
		}
		System.out.print(sb);
    }
}
