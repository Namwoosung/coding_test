import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int g = Integer.parseInt(br.readLine());

        long i = 1;
        long j = 2;
        boolean is_empty = true;

        long iSquared = 1;
        long jSquared = 4;

        while (i < j) {
            long sum = iSquared + g;
            if (sum < jSquared) {
                i++;
                iSquared = i * i;
            } else if (sum > jSquared) {
                j++;
                jSquared = j * j;
            } else {
                bw.write(String.valueOf(j) + "\n");
                is_empty = false;
                j++;
                i++;
                jSquared = j * j;
                iSquared = i * i;
            }
        }

        if (is_empty) {
            bw.write("-1\n");
        }

        bw.flush();
        br.close();
        bw.close();
    }
}

