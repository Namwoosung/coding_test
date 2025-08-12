import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	static class Fish{
		int x;
		int y;
		int num;
		int dir;
		boolean alive;
		
		public Fish(int x, int y, int num, int dir) {
			this.x = x;
			this.y = y;
			this.num = num;
			this.dir = dir;
			this.alive = true;
		}
		
		public Fish(Fish other) {
			this.x = other.x;
			this.y = other.y;
			this.num = other.num;
			this.dir = other.dir;
			this.alive = other.alive;
		}
	}
	
	static class Shark{
		int x;
		int y;
		int dir;

		public Shark(int x, int y, int dir) {
			this.x = x;
			this.y = y;
			this.dir = dir;
		}
		
		public Shark(Shark other) {
			this.x = other.x;
			this.y = other.y;
			this.dir = other.dir;
		}
	}
	
	static int[] dx = {-1, -1, 0, 1, 1, 1, 0, -1};
	static int[] dy = {0, -1, -1, -1, 0, 1, 1, 1};
	static int result = 0;
	static Fish[][] board;
	static Fish[] fishes;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		board = new Fish[4][4];
		fishes = new Fish[16];
		for(int i = 0; i < 4; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < 4; j++) {
				Fish fish = new Fish(i, j, Integer.parseInt(st.nextToken()),  Integer.parseInt(st.nextToken()) -1);
				board[i][j] = fish;
				fishes[fish.num - 1] = fish;
			}
		}
		
		// 0 번을 먹고 시작
		int score = board[0][0].num;
		Shark shark = new Shark(0, 0, board[0][0].dir);
		board[0][0].alive = false;
		board[0][0] = null;
		
		result = score;
		
		dfs(shark, score, fishes);
		
		System.out.println(result);
	}
	
	
	static void dfs(Shark shark, int score, Fish[] fishes) {
		
		Fish[][] newBoard = new Fish[4][4];
		Fish[] newFishes = new Fish[16];
		for(int i = 0; i < 4; i++) {
			for(int j = 0; j < 4; j++) {
				Fish temp = new Fish(fishes[i*4+j]);
				newFishes[i*4+j] = temp;
				if(temp.alive) {
					newBoard[temp.x][temp.y] = temp;
				}
			}
		}
		Shark newShark = new Shark(shark);
		
		
		// 물고기 이동 먼저 시작
		for(int i = 0; i < 16; i++) {
			Fish fish = newFishes[i];
			if(!fish.alive) continue;
			
			int x = fish.x;
			int y = fish.y;
			
			int nx = x + dx[fish.dir];
			int ny = y + dy[fish.dir];
			
			int standDir = fish.dir;
			
			while(true) {
				if(!(nx >= 0 && nx < 4 && ny >= 0 && ny < 4) || (nx == newShark.x && ny == newShark.y)) {
					
					fish.dir = (fish.dir + 1) % 8;
					if(fish.dir == standDir) break;
					nx = x + dx[fish.dir];
					ny = y + dy[fish.dir];
					
				} else {
					if(newBoard[nx][ny] == null || !newBoard[nx][ny].alive) {
						newBoard[nx][ny] = fish;
						fish.x = nx; fish.y = ny;
						newBoard[x][y] = null;
					} else {
						Fish tempFish = newBoard[nx][ny];
						newBoard[nx][ny] = fish;
						newBoard[x][y] = tempFish;
						fish.x = nx; fish.y = ny;
						tempFish.x = x; tempFish.y = y;
					}
					break;
				}
			}
		}
		
		// 상어 이동
		int nx = newShark.x + dx[newShark.dir];
		int ny = newShark.y + dy[newShark.dir];
		
		while(nx >= 0 && nx < 4 && ny >= 0 && ny < 4) {
			if(newBoard[nx][ny] != null && newBoard[nx][ny].alive) {
				Fish temp = newBoard[nx][ny];
				int sharkDir = newShark.dir;
				
				score += temp.num;
				newBoard[nx][ny] = null;
				temp.alive = false;
				newShark.dir = temp.dir;
				
				result = Math.max(result, score);
				dfs(newShark, score, newFishes);
				
				score -= temp.num;
				newBoard[nx][ny] = temp;
				temp.alive = true;
				newShark.dir = sharkDir;
			}
			
			nx += dx[newShark.dir];
			ny += dy[newShark.dir];
		}
	}
}
