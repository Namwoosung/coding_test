import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.StringTokenizer;

public class Solution {
	static int[] root;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		for(int testCase = 1; testCase <= T; testCase++) {
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken()); int M = Integer.parseInt(st.nextToken());
			
			root = new int[N+1];
			for(int i = 1; i <= N; i++) {
				root[i] = i;
			}
			
			sb.append("#").append(testCase).append(" ");
			for(int i = 0; i < M ; i++) {
				st = new StringTokenizer(br.readLine());
				int command = Integer.parseInt(st.nextToken());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				
				if(command == 0) union(a,b);
				else {
					if(find(a) == find(b)) sb.append(1);
					else sb.append(0);
				}
				
				
			}
			sb.append("\n");
		}
		System.out.print(sb);
	}
	
	static void union(int n1, int n2) {
		int r1 = find(n1);
		int r2 = find(n2);
		
		if(r1 == r2) return;
		
		root[r1] = r2;
	}
	
	static int find(int n) {
		if(root[n] == n) return n;
		root[n] = find(root[n]);
		return root[n];
	}
}