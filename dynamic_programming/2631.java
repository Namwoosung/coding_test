import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int N = Integer.parseInt(br.readLine());
		
		int[] arr = new int[N];
		for(int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}
		
		// 현재 index까지 가장 길게 찾을 수 있는 오름차순의 부분 수열 길이를 저장
		// ex) 3 7 5 2 6 1 4 라면 
		// arrLength[4]는 3~6까지 3 5 6이 가장 긴 부분 수열 => 3
		// arrLength[6]은 3 4 혹은 2 4 혹은 1 4 만 가능 => 2
		int[] arrLength = new int[N];
		int result = 0;
		
		for(int i = 0; i < N; i++) {
			arrLength[i] = 1;
			for(int j = i-1; j >=0; j--) {
				
				// 검사하는 i 인덱스 기준 가장 긴 부분 수열 길이를 찾아서 저장
				// 가장 긴 부분 수열을 result에 저장
				if( arr[i] > arr[j]) {
					arrLength[i] = Math.max(arrLength[i], arrLength[j] + 1);
					result = Math.max(result, arrLength[i]);
				}
				
			}
		}
		System.out.print(N - result); // 정렬 된 부분 말고 나머지 부분만 옮긴다고 생각하고 N - result가 정답
	}
}


/*
// 찾아본 더 효율적인 코드
// 가장 긴 부분 수열을 찾거나 활용해야 하는 문제라면, 2 중 for문을 사용하는 것보다 Collections.binarySearch() 함수를 사용하는 것이 더 효과적일 것 같음
import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];
        for(int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        ArrayList<Integer> list = new ArrayList<>();
        for(int i = 0; i < N; i++) {
            int num = arr[i];
            int idx = Collections.binarySearch(list, num);
            if(idx < 0) idx = -(idx + 1);
            if(idx == list.size()) {
                list.add(num);
            } else {
                list.set(idx, num);
            }
        }

        System.out.println(N - list.size());
    }
}
 */