import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	static class Node{
		int max;
		int min;
		public Node(int max, int min) {
			this.max = max;
			this.min = min;
		}
	}
	
	static int N;
	static int M;
	static int[] arr;
	static Node[] segTree;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		arr = new int[N+1];
		for(int i = 1; i <= N; i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}
		
		segTree = new Node[4 * N];
		build(1, 1, N);
		
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			Node now = find(1, 1, N, Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
			sb.append(now.min).append(" ").append(now.max).append("\n");
		}
		System.out.println(sb);
	}
	
	static Node build(int index, int start, int end) {
		if(start == end) {
			segTree[index] = new Node(arr[start], arr[start]);
			return segTree[index];
		}
		
		int mid = (start + end) / 2;
		Node leftNode = build(index * 2, start, mid);
		Node rightNode = build(index * 2 + 1, mid + 1, end);
		segTree[index] = new Node(Math.max(leftNode.max, rightNode.max), Math.min(leftNode.min, rightNode.min));
		
		return segTree[index];
	}
	
	static Node find(int index, int start, int end, int left, int right) {
		if(left <= start && end <= right) return segTree[index]; // 탐색 범위에 완전히 포함
		else if (right < start || end < left) return null; // 범위에 아예 포함 x
		else { // 일부 걸친 경우
			int mid = (start + end) / 2;
			Node leftNode  = find(index * 2, start, mid, left, right);
			Node rightNode = find(index * 2 + 1, mid + 1, end, left, right);
			
			if(leftNode == null) return rightNode;
			else if(rightNode == null) return leftNode;
			else return new Node(Math.max(leftNode.max, rightNode.max), Math.min(leftNode.min, rightNode.min));
		}
	}
}
