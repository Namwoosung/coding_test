import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.BitSet;
import java.util.StringTokenizer;

public class Main {
	static boolean[][] board;
	static int N; static int M;
	static boolean isFound = false;
	
	// 좌표 우상 - 우 - 우하를 의미
	static int[] dx = {-1, 0, 1};
	static int[] dy = {1, 1, 1};
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); M = Integer.parseInt(st.nextToken());
		
		// 입력 처리
		board = new boolean[N][M];
		for(int i = 0; i < N; i++) {
			String line = br.readLine();
			for(int j = 0; j < M; j++) {
				if(line.charAt(j) == '.') board[i][j] = true; // . 이면 갈 수 있음
			}
		}
		
		// 각 행의 시작점으로 탐색 시작
		for(int i = 0; i < N; i++) {
			isFound = false; // 가지치기를 위한 flag
			checkRoute(i, 0); // 파이프의 현재 좌표
		}
		
		int result = 0;
		for(int i = 0 ; i < N; i++) {
			if(!board[i][M-1]) result++; // 파이프가 도달한 경우들을 카운팅
		}
		System.out.println(result);
	}
	
	static void checkRoute(int x, int y) {
		if(isFound) return; // 이미 찾은 경로가 있으면 종료
		
		board[x][y] = false;
		int nx = x; int ny = y; // 다음 후보 좌표
		for(int dir = 0; dir < 3; dir++) {
			nx = x + dx[dir]; ny = y + dy[dir];
			if(!(nx >= 0 && nx < N && ny >= 0 && ny < M)) continue; // 다음 좌표가 범위 밖이면 pass
			if(!board[nx][ny]) continue; // 갈 수 없거나 갈 필요 없으면 pass
			
			//위 2가지 case가 아니면 갈 수 있음 => 우선순위 대로 해당 경로로 이동
			checkRoute(nx, ny);
			if(isFound) return; // 중간에 길이 찾아졌으면 나오기
		}
		
		if(y == M-1) { // 끝 도달 시 종료
			isFound = true;
			return; 
		}
	}
}
