import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());

        int[][] work = new int[n+1][2];
        for(int i = 1; i < n + 1; i++){
            st = new StringTokenizer(br.readLine());
            work[i][0] = Integer.parseInt(st.nextToken());
            work[i][1] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(work, (a, b) -> {
            return Integer.compare(b[0], a[0]);
        });

        PriorityQueue<Integer> remain = new PriorityQueue<>(Collections.reverseOrder());
        int date = work[0][0];
        int result = 0;
        int stand = 0;

        for(int i = date; i > 0; i--){
            //현재 날짜에 해당하는 모든 과제를 담기
            while (i == work[stand][0]){
                remain.add(work[stand][1]);
                stand++;
            }

            if(!remain.isEmpty()) result += remain.poll();
        }

        System.out.println(result);
    }
}
