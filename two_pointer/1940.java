import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        int m = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int[] list = new int[n];
        for(int i = 0; i < n; i++){
            list[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(list);

        int result = 0;
        int i = 0; int j = n-1;
        while(i < j){
            if(list[i] + list[j] == m){
                result++;
                i++;
                j--;
            }
            else if(list[i] + list[j] < m){
                i++;
            } else{
                j--;
            }
        }

        System.out.println(result);

        br .close();
    }
}

