import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;
//import java.io.FileInputStream;


class Solution {
    public static void main(String[] args) throws Exception {
//        System.setIn(new FileInputStream("/Users/namws/Desktop/coding_test/ForCodingTest_java/input.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int T = Integer.parseInt(st.nextToken());

        for(int testCase = 1; testCase <= T; testCase++){
            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());

            int[] arr1 = new int[N];
            int[] arr2 = new int[M];

            st = new StringTokenizer(br.readLine());
            for(int i = 0; i < N; i++){
                arr1[i] = Integer.parseInt(st.nextToken());
            }
            st = new StringTokenizer(br.readLine());
            for(int i = 0; i < M; i++){
                arr2[i] = Integer.parseInt(st.nextToken());
            }

            if(N > M){ // M과 arr2가 무조건 더 큰 배열이 되도록 강제
                int[] temp = arr1;
                arr1 = arr2;
                arr2 = temp;

                int temp2 = N;
                N = M;
                M = temp2;
            }

            // brute force로 모든 case 탐색
            int result = Integer.MIN_VALUE;
            for(int i = 0; i + N <= M; i++){
                int value = 0;

                for(int j = i; j < i + N; j++){
                    value += arr1[j - i] * arr2[j];
                }

                result = Math.max(result, value);
            }

            bw.write("#" + testCase + " " + result + "\n");
        }
        bw.flush();
    }
}