// 중요! 문자로 표현된 숫자를 int로 가장 효율적으로 변환하는 방법: number.charAt(i) - '0'

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br. readLine());

        String number = st.nextToken();
        int result = 0;
        for(int i = 0; i < number.length(); i++){
            result += number.charAt(i) - '0';
        }

        bw.write(String.valueOf(result));
        bw.flush();
    }
}