import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main{
	static int N;
	static int result;
	static int length;
	static int[] arr;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		
		int left = Integer.parseInt(st.nextToken()); int right = Integer.parseInt(st.nextToken());
		// 열려있는 벽장이 순서대로 준다는 조건이 없어서 만약 순서가 다르게 들어오면 맞춰줌
		if(right < left) {
			int temp = left; left = right; right = temp;
		}
		
		length = Integer.parseInt(br.readLine());
		arr = new int[length];
		for(int i = 0; i < length; i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}
		
		result = Integer.MAX_VALUE;
		
		turn(0, 0, left, right);
		
		System.out.println(result);
	}

	private static void turn(int index, int value, int left, int right) {
		if(value > result) return; // 백트래킹
		
		if(index >= length) {
			result = value;
			return;
		}
		
		int now = arr[index];
		
		if(now <= left) turn(index + 1, value + (left - now), now, right);
		else if(now >= right) turn(index + 1, value + (now - right), left, now);
		else {
			turn(index + 1, value + (now - left), now, right);
			turn(index + 1, value + (right - now), left, now);
		}
	}
}