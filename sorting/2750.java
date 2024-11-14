import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        int[] numSet = new int[n];

        for(int i = 0; i < n; i++){
            numSet[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(numSet);
        for (int i : numSet) {
            System.out.println(i);
        }


    }
}
