import java.io.*;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());

        int[] numSet = new int[n];

        for(int i = 0; i < n; i++){
            numSet[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(numSet);
        for (int i : numSet) {
            bw.write(i + "\n");
        }
        bw.flush();

        bw.close();
        br.close();
    }
}
