import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N, M, K;
	static long[] arr;
	static long[] segTree;
	static long[] lazy;
	
    public static void main(String[] args) throws Exception {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer st = new StringTokenizer(br.readLine());
    	StringBuilder sb = new StringBuilder();
    	
    	N = Integer.parseInt(st.nextToken());
    	M = Integer.parseInt(st.nextToken());
    	K = Integer.parseInt(st.nextToken());
    	
    	arr = new long[N+1];
    	for(int i = 1; i <= N; i++) {
    		arr[i] = Long.parseLong(br.readLine());
    	}
    	
    	segTree = new long[4 * N];
    	lazy = new long[4 * N];
    	build(1, 1, N);
    	
    	for(int tc = 0; tc < M + K; tc++) {
    		st = new StringTokenizer(br.readLine());
    		int cmd = Integer.parseInt(st.nextToken());
    		int left = Integer.parseInt(st.nextToken());
    		int right = Integer.parseInt(st.nextToken());
    		if(cmd == 1) {
    			long diff = Long.parseLong(st.nextToken());
    			update(1, 1, N, left, right, diff);
    		}else {
    			sb.append(query(1, 1, N, left, right)).append("\n");
    		}
    	}
    	System.out.print(sb);
    }
    
    static long build(int node, int start, int end) {
    	if(start == end) return segTree[node] = arr[start];
    
    	int mid = (start + end) / 2;
    	return segTree[node] = build(node * 2, start, mid) + build(node * 2 + 1, mid + 1, end);
    }
    
    static long query(int node, int start, int end, int left, int right) {
    	if(left > end || right < start) return 0;
    	
    	pop(node, start, end);
    	
    	if(start >= left && end <= right) {
    		return segTree[node];
    	}else {
    		int mid = (start + end) / 2;
    		return query(node * 2, start, mid, left, right) + query(node * 2 + 1, mid + 1, end, left, right);
    	}
    }
    
    static void update(int node, int start, int end, int left, int right, long diff) {
    	if(left > end || right < start) return;
    	
    	if(start >= left && end <= right) {
    		push(node, diff);
    	}else {
    		int nowLeft = Math.max(start, left);
    		int nowRight = Math.min(end, right);
    		segTree[node] += (nowRight - nowLeft + 1) * diff;
    		
    		int mid = (start + end) / 2;
    		update(node * 2, start, mid, left, right, diff);
    		update(node * 2 + 1, mid + 1, end, left, right, diff);
    		
    	}
    	
    }
    
    static void push(int node, long value) {
    	lazy[node] += value;
    }
    
    static void pop(int node, int start, int end) {
    	if(lazy[node] == 0) return;
    	
    	segTree[node] += lazy[node] * (end - start + 1);
    	if(node * 2 + 1 < 4 * N) {
    		lazy[node * 2] += lazy[node];
    		lazy[node * 2 + 1] += lazy[node];
    	}
    	lazy[node] = 0;
    }
}
