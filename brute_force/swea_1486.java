//아래 풀이는 백트래킹으로 가지를 쳤어도 2^N의 복잡도이므로 비효율적
//비트마스크나 DP 기반 풀이가 더욱 효율적

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;



class Solution {

    static int result = Integer.MAX_VALUE;

    static void dfs(int[] heights, int index, int N, int sum, int B){
        if(sum >= B){
            result = Math.min(result, sum);
            return; // 더 진행하는 것이 의미가 없음
        }

        if(index >= N) return;

        dfs(heights, index+1, N, sum, B); // 현재 인덱스를 포함하지 않는 경우
        dfs(heights, index + 1, N, sum + heights[index], B); //현재 인덱스를 포함하는 경우

    }


    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int T = Integer.parseInt(st.nextToken());

        for(int testCase = 1; testCase <= T; testCase++){
            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken()); int B = Integer.parseInt(st.nextToken());

            int[] heights = new int[N];

            st = new StringTokenizer(br.readLine());
            for(int i = 0; i < N; i++){
                heights[i] = Integer.parseInt(st.nextToken());
            }

            result = Integer.MAX_VALUE;
            dfs(heights, 0, N, 0, B);

            bw.write("#" + testCase + " " + (result - B) + "\n");
        }
        bw.flush();
    }
}