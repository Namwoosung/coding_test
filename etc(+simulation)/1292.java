import java.io.*;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int a = Integer.parseInt(st.nextToken());
        int b= Integer.parseInt(st.nextToken());


        int[] number = new int[1001];
        number[0] = 0; number[1] = 1;
        int stand = 1;
        int now = 0;
        for(int i = 2; i <= 45; i++){
            now = stand + i;

            for(int j = stand + 1; j <= now; j++){
                if(j > 1000) break;
                number[j] = i;
            }
            if(b < now) break;
            stand = now;
        }

        int result = 0;
        for(int i = a; i <=b; i++){
            result += number[i];
        }

        bw.write(String.valueOf(result));
        bw.flush();

    }
}


/*
더 쉬운 풀이, list에 값을 넣을때 복잡하게 넣을 필요 없이 그냥 2중 for문으로 해결 가능
 import java.io.*;
 import java.util.*;

 public class Main
 {
 public static void main (String[] args) throws java.lang.Exception
 {
 BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
 ArrayList<Integer> list = new ArrayList<Integer>();
 StringTokenizer st = new StringTokenizer(br.readLine());


 int a = Integer.parseInt(st.nextToken());
 int b = Integer.parseInt(st.nextToken());
 int sum=0;

 for(int i = 0; i < 1000; i++) {
 for(int j = 0; j <= i; j++) {
 list.add(i + 1);
 }
 }

 for(int i = a-1; i <= b-1; i++) {
 sum += list.get(i);
 }

 System.out.println(sum);
 }
 }
 */
