import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.StringTokenizer;
 
public class Solution {
	static class Visited{
		String path;
		int pos;
		public Visited(String path, int pos) {
			this.path = path;
			this.pos = pos;
		}
		
		@Override
		public int hashCode() {
			return Objects.hash(path, pos);
		}
		
		@Override
		public boolean equals(Object obj) {
			Visited visited = (Visited)obj;
			return (this.path.equals(visited.path)) && (this.pos == visited.pos);
		}
	}
	
	static int[] dx = {0, 0, 1, -1};
	static int[] dy = {1, -1, 0, 0};
	static String[][] board;
	static Set<Visited> store = new HashSet<>();
	static Set<String> result = new HashSet<>();
	
    public static void main(String[] args) throws Exception {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringBuilder sb = new StringBuilder();
    	StringTokenizer st;
    	
    	int T = Integer.parseInt(br.readLine());
    	for(int testCase = 1; testCase <= T; testCase++) {
    		board = new String[4][4];
    		store.clear();
    		result.clear();
    		
    		for(int i = 0; i < 4; i++) {
    			st = new StringTokenizer(br.readLine());
    			for(int j = 0; j < 4; j++) {
    				board[i][j] = st.nextToken();
    			}
    		}
    		
    		for(int i = 0; i < 4; i++) {
    			for(int j = 0; j < 4; j++) {
    				checkBoard(i, j, 1, "");
    			}
    		}
    		
    		sb.append("#").append(testCase).append(" ").append(result.size()).append("\n");
    	}
    	System.out.print(sb);
    }
    
    static void checkBoard(int x, int y, int index, String path) {
    	String now = path + board[x][y];
    	if(index == 7) {
    		result.add(now);
    		return;
    	}
    	
    	Visited nowVisit = new Visited(now, x * 4 + y);
    	if(store.contains(nowVisit)) return;
    	
    	store.add(nowVisit);
    	for(int dir = 0; dir < 4; dir++) {
    		int nx = x + dx[dir];
    		int ny = y + dy[dir];
    		if(!(nx >= 0 && nx < 4 && ny >= 0 && ny < 4)) continue;
    		
    		checkBoard(nx, ny, index+1, now);
    	}
    }
}