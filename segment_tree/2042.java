import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static long[] segTree;
	static long[] arr;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken()); int M = Integer.parseInt(st.nextToken()); int K = Integer.parseInt(st.nextToken());
		
		arr = new long[N+1];
		for(int i = 1; i <= N; i++) {
			arr[i] = Long.parseLong(br.readLine());
		}
		
		segTree = new long[4 * N];
		makeSegTree(1, N, 1);
		
		for(int tmp = 0; tmp < M + K; tmp++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken()); int b = Integer.parseInt(st.nextToken()); long c = Long.parseLong(st.nextToken());
			if(a == 1) {
				changeValue(1, N, 1, b, c - arr[b]);
				arr[b] = c;
			}else {
				sb.append(getSum(1, N, 1, b, c)).append("\n");
			}
		}
		System.out.print(sb);
    }
	
	static long makeSegTree(int start, int end, int node) {
		if(start == end) {
			segTree[node] = arr[start];
			return segTree[node];
		}
		
		int mid = (start + end) / 2;
		return segTree[node] = makeSegTree(start, mid, node * 2) + makeSegTree(mid + 1, end, node * 2 + 1);
	}
	
	static void changeValue(int start, int end, int node, int index, long diff) {
		int mid = (start + end) / 2;

		if(start == index && end == index) segTree[node] += diff;
		else if(start <= index && index <= end) {
			segTree[node] += diff;
			changeValue(start, mid, node*2, index, diff);
			changeValue(mid + 1, end, node * 2 + 1, index, diff);
		}
	}
	
	static long getSum(int start, int end, int node, int sumStart, long sumEnd) {
		long sum = 0;
		int mid = (start + end) / 2;
		if(sumStart <= start &&  end <= sumEnd) {
			return segTree[node];
		}else if(!(start > sumEnd || sumStart > end)) {
			sum += getSum(start, mid, node * 2, sumStart, sumEnd);
			sum += getSum(mid+1, end, node * 2 + 1, sumStart, sumEnd);
		}
		return sum;
	}
	
}