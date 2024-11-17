import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int d = Integer.parseInt(st.nextToken());


        int[][] rules = new int[k][3];
        for(int i = 0; i < k; i++){
            st = new StringTokenizer(br.readLine());
            rules[i][0] = Integer.parseInt(st.nextToken());
            rules[i][1] = Integer.parseInt(st.nextToken());
            rules[i][2] = Integer.parseInt(st.nextToken());
        }

        int start = 1;
        int end = n;
        int mid;

        int cnt = 0;
        int result = 0;
        while (start <= end){
            mid = (start + end) / 2;
            cnt = 0;
            for (int i = 0; i < k; i++) {
                int A = rules[i][0], B = rules[i][1], C = rules[i][2];
                if (mid >= A) {
                    cnt += (Math.min(mid, B) - A) / C + 1;
                }
                if (cnt > d) break; // 더 계산할 필요 없음
            }

            if(cnt < d){
                start = mid+1;
            } else{
                result = mid;
                end = mid-1;
            }
        }

        System.out.println(result);

        br.close();
    }
}
