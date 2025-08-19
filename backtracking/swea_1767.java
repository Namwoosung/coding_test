import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Solution {
	static int[] dx = {0, 1, 0, -1};
	static int[] dy = {-1, 0, 1, 0};
	static int[][] board;
	static int N;
	static List<int[]> core;
	static int result;
	static int resultNum;


	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());
		for(int testCase = 1; testCase <= T; testCase++) {
			N = Integer.parseInt(br.readLine());
			board = new int[N][N];
			core = new ArrayList<>(); //core들을 저장

			for(int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j = 0; j < N; j++) {
					board[i][j] = Integer.parseInt(st.nextToken());
					if(board[i][j] == 1) {
						core.add(new int[]{i,j});
					}
				}
			}

			result = Integer.MAX_VALUE;
			resultNum = -1;

			connectCore(0, 0);

			sb.append("#").append(testCase).append(" ").append(result).append("\n");
		}
		System.out.print(sb);
	}

	static void connectCore(int index, int cnt) {
		if((core.size() - index) + cnt < resultNum) return; // 남은 코어가 다 연결되어도 불가능하면 백트래킹


		if(index >= core.size()) {
			if(cnt == resultNum) { // 연결된 코어 수가 같으면 전선 개수가 적을 때만 update
				resultNum = cnt;
				int cost = 0;
				for(int i = 0; i < N; i++) {
					for(int j = 0; j < N; j++) {
						if(board[i][j] == 2) cost++;
					}
				}
				result = Math.min(result, cost);
			} else if(cnt > resultNum) { // 연결된 코어 개수가 많으면 무조건 update
				resultNum = cnt;
				int cost = 0;
				for(int i = 0; i < N; i++) {
					for(int j = 0; j < N; j++) {
						if(board[i][j] == 2) cost++;
					}
				}
				result = cost;
			}
		}else {
			// 4가지 방향 case를 모두 검사
			int[] stand = core.get(index);

			connectCore(index+1, cnt); // 현재 코어를 연결하지 않고 전개

			// 4 방향에 대해서는 연결 가능한 경우 연결하고 넘기기
			// 왼쪽 방향
			if(connectOneCore(stand[0], stand[1], 0)) {
				connectCore(index+1, cnt+1);
				recovery(stand[0], stand[1], 0);
			}

			// 아래 방향
			if(connectOneCore(stand[0], stand[1], 1)) {
				connectCore(index+1, cnt+1);
				recovery(stand[0], stand[1], 1);
			}

			// 오른쪽 방향
			if(connectOneCore(stand[0], stand[1], 2)) {
				connectCore(index+1, cnt+1);
				recovery(stand[0], stand[1], 2);
			}

			// 위 방향
			if(connectOneCore(stand[0], stand[1], 3)) {
				connectCore(index+1, cnt+1);
				recovery(stand[0], stand[1], 3);
			}
		}
	}

	static boolean connectOneCore(int x, int y, int dir) {
		int nx = x + dx[dir];
		int ny = y + dy[dir];
		
		boolean flag = true;
		
		while(nx >= 0 && nx < N && ny >= 0 && ny < N) {
			if(board[nx][ny] == 1 || board[nx][ny] == 2) { // 가는 길에 코어가 존재
				flag = false; 
				break;
			}
			board[nx][ny] = 2;
			nx += dx[dir];
			ny += dy[dir];
		}

		if(!flag) { // 연결을 실패한 경우: board 복원 후 false를 리턴
			dir = (dir + 2) % 4;
			nx += dx[dir];
			ny += dy[dir];
			while(!((nx == x) && (ny == y))) {
				board[nx][ny] = 0;
				nx += dx[dir];
				ny += dy[dir];
			}
			return false;
		}
		
		return true;
	}
	
	static void recovery(int x, int y, int dir) {
		int nx = x + dx[dir];
		int ny = y + dy[dir];
		
		while(nx >= 0 && nx < N && ny >= 0 && ny < N) {
			board[nx][ny] = 0;
			nx += dx[dir];
			ny += dy[dir];
		}
	}
}