import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());

        int[] liquid = new int[n];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i <  n; i++){
            liquid[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(liquid);

        int start = 0, end = n-1;
        int stand = Integer.MAX_VALUE;
        int low= 0, max = 0;
        while (start < end){
            int now =  liquid[start] + liquid[end];
            if(Math.abs(now) < Math.abs(stand)){
                low = liquid[start];
                max = liquid[end];
                stand = Math.abs(now);
            }

            if(now > 0){
                end--;
            }else if(now < 0){
                start++;
            }else{
                break;
            }
        }

        System.out.println(low + " " + max);

        br.close();
    }
}
