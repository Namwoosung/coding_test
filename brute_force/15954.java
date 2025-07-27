import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		int[] arr = new int[N];
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		double m = 0;
		double sum = 0;
		double sd = 0;
		double result = Double.MAX_VALUE;
		//완전탐색
		for(int i = 0; i <= N - K; i++) { // 시작점
			sum = 0;
			
			for(int j = i; j < N; j++) { // 배열 끝까지 탐색
				sum += arr[j];
				
				int nowLen = j - i + 1;
				
				if( nowLen >= K) { // 길이가 K 이상이 된 경우 표준편차를 계산
					m = sum / nowLen;
					
					sd = 0;
					for(int k = i; k <= j; k++) {
						sd += Math.pow(arr[k] - m, 2);
					}
					sd /= nowLen;
					sd = Math.sqrt(sd);
					
					result = Math.min(result, sd);
				}
				
			}
		}
		System.out.print(result);
	}
}



/**
// 더 효율적인 풀이, 가중합을 이용해서 O(N^3) -> O(N^2)

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());

		int[] arr = new int[N + 1];
		double[] prefixSum = new double[N + 1];
		double[] prefixSqSum = new double[N + 1];

		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
			prefixSum[i] = prefixSum[i - 1] + arr[i];
			prefixSqSum[i] = prefixSqSum[i - 1] + arr[i] * arr[i];
		}

		double result = Double.MAX_VALUE;

		for (int len = K; len <= N; len++) {
			for (int i = 0; i + len <= N; i++) {
				int j = i + len;

				double sum = prefixSum[j] - prefixSum[i];
				double sqSum = prefixSqSum[j] - prefixSqSum[i];
				double mean = sum / len;

				// 표준편차: sqrt( (제곱합 / N) - (평균^2) )
				double variance = (sqSum / len) - (mean * mean);
				double stdDev = Math.sqrt(variance);

				result = Math.min(result, stdDev);
			}
		}

		System.out.print( result);
	}
}

 */