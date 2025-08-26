import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Solution {
	static int[] dx = {0, 0, 1, -1};
    static int[] dy = {1, -1, 0, 0};
    static int[] root = new int[100 * 100];

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();


        for(int testCase = 1; testCase <= 10; testCase++){
        	br.readLine();
        	
        	for(int i = 0; i < 100*100; i++) {
        		root[i] = -1;
        	}
        	
        	String line;
        	int sIndex = 0; int eIndex = 0;
        	for(int i = 0; i < 100; i++) {
        		line = br.readLine();
        		for(int j = 0; j < 100; j++) {
        			char now = line.charAt(j);
        			if(now == '1') continue; // 벽이면 패스
        			else if(now == '2') sIndex = i * 100 + j;
        			else if(now == '3') eIndex = i * 100 + j;
        			
        			root[i * 100 + j] = i * 100 + j;
        			
        			for(int dir = 0; dir < 4; dir++) {
        				int nx = i + dx[dir];
        				int ny = j + dy[dir];
        				if(!(nx >= 0 && nx < 100 && ny >= 0 && ny < 100)) continue;
        				if(root[nx * 100 + ny] == -1) continue;
        				union(i * 100 + j, nx * 100 + ny);
        			}
        		}
        	}
        	find(sIndex); find(eIndex);
        	sb.append("#").append(testCase).append(" ");
        	if(root[sIndex] == root[eIndex]) sb.append(1);
        	else sb.append(0);
        	sb.append("\n");
        }
        System.out.print(sb);
    }

    static int find(int n1){
        if(root[n1] == n1) return n1;

        root[n1] = find(root[n1]);
        return root[n1]; 
    }

    static void union(int n1, int n2){
        int r1 = find(n1);
        int r2 = find(n2);

        if(r1 == r2) return;

        root[r1] = r2;
        return;
    }
}