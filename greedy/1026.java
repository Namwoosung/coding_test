import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int num = Integer.parseInt(st.nextToken());


        Integer[] a = new Integer[num];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i< num; i++){
            a[i] = Integer.parseInt(st.nextToken());
        }

        Integer[] b = new Integer[num];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i< num; i++){
            b[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(a);
        Arrays.sort(b, Comparator.reverseOrder());

        int sum = 0;
        for(int i = 0; i < num; i++){
            sum += a[i] * b[i];
        }

        System.out.println(sum);
    }
}
