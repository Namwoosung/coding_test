import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.BitSet;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
	static class Edge implements Comparable<Edge>{
		int start;
		int end;
		int weight;
		public Edge(int start, int end, int weight) {
			this.start = start;
			this.end = end;
			this.weight = weight;
		}
		@Override
		public int compareTo(Edge other) {
			return this.weight - other.weight;
		}
	}
	
	static int[] root = new int[100001];
	static PriorityQueue<Edge> graph = new PriorityQueue<>();
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		for(int testCase = 1; testCase <= T; testCase++) {
			st = new StringTokenizer(br.readLine());
			int V = Integer.parseInt(st.nextToken()); int E = Integer.parseInt(st.nextToken());
			for(int i = 1; i <= V; i++) {
				root[i] = i;
			}
			int groupCnt = V;
			graph.clear();
			
			for(int i = 0; i < E; i++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				int c = Integer.parseInt(st.nextToken());
				
				graph.add(new Edge(a, b, c));
			}
			
			long result = 0;
			while(groupCnt > 1) {
				Edge now = graph.poll();
				if(union(now.start, now.end)) {
					result += now.weight;
					groupCnt--;
				}
				
			}
			sb.append("#").append(testCase).append(" ").append(result).append("\n");
		}
		System.out.print(sb);
	}
	
	static boolean union(int n1, int n2) {
		int r1 = find(n1); int r2 = find(n2);
		if(r1 == r2) return false;
		root[r1] = r2;
		return true;
	}
	
	static int find(int n) {
		if(root[n] == n) return n;
		return root[n] = find(root[n]);
	}
}