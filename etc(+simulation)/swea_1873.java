import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.StringTokenizer;

public class Solution {
	static char[][] board;
	static int N;
	static int M;

	// 순서대로 상우하좌
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, 1, 0, -1};
	
	static class Tank{
		int x;
		int y;
		int dir;
		
		public Tank(int x, int y, int dir) {
			this.x = x;
			this.y = y;
			this.dir = dir;
		}
	}
	
	static Tank tank;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		for(int testCase = 1; testCase <= T; testCase++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken()); M = Integer.parseInt(st.nextToken());
			
			board = new char[N][M];
			for(int i = 0; i < N; i++) {
				String line = br.readLine();
				board[i] = line.toCharArray();
				for(int j = 0; j < M; j++) {
					if(board[i][j] == '^') tank = new Tank(i, j, 0);
					else if(board[i][j] == '>') tank = new Tank(i, j, 1);
					else if(board[i][j] == 'v') tank = new Tank(i, j, 2);
					else if(board[i][j] == '<') tank = new Tank(i, j, 3);
				}
			}
		
			int K = Integer.parseInt(br.readLine());
			String command = br.readLine();
			
			for(int i = 0; i < K; i++){
				char now = command.charAt(i);
				if(now == 'U') goUp();
				else if(now == 'D') goDown();
				else if(now == 'L') goLeft();
				else if(now == 'R') goRight();
				else if(now == 'S') shoot();
			}

			sb.append("#").append(testCase).append(" ");
			for(int i = 0 ; i < N; i++) {
				for(int j = 0; j < M; j++) {
					if(tank.x == i && tank.y == j) {
						if(tank.dir == 0) sb.append('^');
						else if(tank.dir == 1) sb.append('>');
						else if(tank.dir == 2) sb.append('v');
						else if(tank.dir == 3) sb.append('<');
					}else {
						sb.append(board[i][j]);
					}
				}
				sb.append("\n");
			}
		}
		
		System.out.print(sb);
	}
	
	static void goUp() {
		tank.dir = 0;
		int nx = tank.x + dx[tank.dir]; int ny = tank.y + dy[tank.dir];
		
		if(!(nx >= 0 && nx < N && ny >= 0 && ny < M)) return;
		if(board[nx][ny] == '.') {
			board[tank.x][tank.y] = '.';
			tank.x = nx;
			tank.y = ny;
		}		
	}
	
	static void goDown() {
		tank.dir = 2;
		int nx = tank.x + dx[tank.dir]; int ny = tank.y + dy[tank.dir];
		
		if(!(nx >= 0 && nx < N && ny >= 0 && ny < M)) return;
		if(board[nx][ny] == '.') {
			board[tank.x][tank.y] = '.';
			tank.x = nx;
			tank.y = ny;
		}		
	}
	
	static void goLeft() {
		tank.dir = 3;
		int nx = tank.x + dx[tank.dir]; int ny = tank.y + dy[tank.dir];
		
		if(!(nx >= 0 && nx < N && ny >= 0 && ny < M)) return;
		if(board[nx][ny] == '.') {
			board[tank.x][tank.y] = '.';
			tank.x = nx;
			tank.y = ny;
		}		
	}
	
	static void goRight() {
		tank.dir = 1;
		int nx = tank.x + dx[tank.dir]; int ny = tank.y + dy[tank.dir];
		
		if(!(nx >= 0 && nx < N && ny >= 0 && ny < M)) return;
		if(board[nx][ny] == '.') {
			board[tank.x][tank.y] = '.';
			tank.x = nx;
			tank.y = ny;
		}		
	}
	
	static void shoot() {
		int nx = tank.x + dx[tank.dir]; int ny = tank.y + dy[tank.dir];
		
		while(nx >= 0 && nx < N && ny >= 0 && ny < M) {
			if(board[nx][ny] == '*') {
				board[nx][ny] = '.';
				return;
			}else if(board[nx][ny] == '#') return;
			
			nx += dx[tank.dir];
			ny += dy[tank.dir];
		}
	}
}
