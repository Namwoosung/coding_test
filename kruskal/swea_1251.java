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
		double weight;

		public Edge(int start, int end, double weight) {
			this.start = start;
			this.end = end;
			this.weight = weight;
		}

		@Override
		public int compareTo(Edge other) {
			return Double.compare(this.weight, other.weight);
		}
	}
	
	static PriorityQueue<Edge> graph = new PriorityQueue<>();
	static int[][] islands = new int[1001][2];
	static int[] root = new int[1001];
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		
		for(int testCase = 1; testCase <= T; testCase++) {
			int N = Integer.parseInt(br.readLine());
			int groupCnt = N;
			graph.clear();
			
			st = new StringTokenizer(br.readLine());
			for(int i = 1; i <= N; i++) {
				int x = Integer.parseInt(st.nextToken());
				islands[i][0] = x;
			}
			st = new StringTokenizer(br.readLine());
			for(int i = 1; i <= N; i++) {
				int y = Integer.parseInt(st.nextToken());
				islands[i][1] = y;
				
				// 바로 이전 노드들을 그래프에 입력
				for(int j = 1; j < i; j++) {
					double dist = Math.sqrt(Math.pow(islands[i][0] - islands[j][0], 2) + Math.pow(islands[i][1] - islands[j][1], 2));
					graph.add(new Edge(i, j, dist));
				}
				root[i] = i;
			}
			
			double E = Double.parseDouble(br.readLine());
			
			double result = 0;
			while(groupCnt > 1) {
				Edge now = graph.poll();
				if(union(now.start, now.end)) {
					result += E * now.weight * now.weight;
					groupCnt--;
				}
			}
			
			sb.append("#").append(testCase).append(" ").append(Math.round(result)).append("\n");
		}
		System.out.print(sb);
	}
	
	static boolean union(int n1, int n2) {
		int r1 = find(n1); int r2 = find(n2);
		
		if(r1 == r2) return false;
		root[r2] = r1; return true;
	}
	
	static int find(int n) {
		if(root[n] == n) return n;
		return root[n] = find(root[n]);
	}
	
}