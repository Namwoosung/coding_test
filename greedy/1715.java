import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.PriorityQueue;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int N = Integer.parseInt(br.readLine());
		
		int[] arr = new int[N];
		
		PriorityQueue<Integer> pq = new PriorityQueue<Integer>();
	
		for(int i = 0; i < N; i++) {
			pq.add(Integer.parseInt(br.readLine()));
		}

		int result = 0;
		while(pq.size() > 1) {
			int a = pq.poll();
			int b = pq.poll();
			
			result += (a + b);
			pq.add(a + b);
		}
		
		bw.write(String.valueOf(result));
		bw.flush();
	}
}
