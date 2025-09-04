import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.StringTokenizer;
 
public class Solution {
	static int[][] board;
	static int[][] store;
	static int N; static int M; static int C;
	
	
    public static void main(String[] args) throws Exception {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringBuilder sb = new StringBuilder();
    	StringTokenizer st;
    	
    	int T = Integer.parseInt(br.readLine());
    	for(int testCase = 1; testCase <= T; testCase++) {
    		st = new StringTokenizer(br.readLine());
    		N = Integer.parseInt(st.nextToken());
    		M = Integer.parseInt(st.nextToken());
    		C = Integer.parseInt(st.nextToken());
    		
    		board = new int[N][N];
    		for(int i = 0; i < N; i++) {
    			st = new StringTokenizer(br.readLine());
    			for(int j = 0; j < N; j++) {
    				board[i][j] = Integer.parseInt(st.nextToken());
    			}
    		}
    		
    		store = new int[N][N];
    		for(int i = 0; i < N; i++) {
    			for(int j = 0 ; j < N - M + 1; j++) {
    				// 각 좌표가 시작점일 경우 최대값을 계산
    				calHoney(i, j);
    			}
    		}
    		
    		int result = 0;
    		int now = 0;
    		for(int i = 0; i < N; i++) {
    			for(int j = 0; j < N - M + 1; j++) {
    				// 각 좌표가 첫 벌통일 경우 계산
    				now = store[i][j];
    				
    				// 같은 행일 경우
    				for(int nj = j + M; nj < N - M + 1; nj++) {
    					result = Math.max(result, now + store[i][nj]);
    				}
    				
    				
    				// 아래행일 경우
    				for(int ni = i+1; ni < N; ni++) {
    					for(int nj = 0; nj < N-M+1; nj++) {
    						result = Math.max(result, now + store[ni][nj]);
    					}
    				}
    				
    			}
    		}
    		sb.append("#").append(testCase).append(" ").append(result).append("\n");
    	}
    	System.out.print(sb);
    }
    
    static void calHoney(int x, int y) {
    	for(int i = 0; i < M; i++) {
    		makeComb(x, y, y + i, board[x][y+i] * board[x][y+i], board[x][y+i], y + M - 1);
    	}
    }
    
    static void makeComb(int x, int standY, int y, int value, int sum, int limit) {
    	if(sum > C) return;
    	if(y > limit) return;
    	
    	store[x][standY] = Math.max(store[x][standY], value);
    	for(int i = y + 1; i <= limit; i++) {
    		makeComb(x, standY, i, value + board[x][i] * board[x][i], sum + board[x][i], limit);
    	}
    }
}