package org.example;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());

        List<Integer> arr = new ArrayList<>();

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < n; i++){
            arr.add(Integer.parseInt(st.nextToken()));
        }

        Collections.sort(arr);

        bw.write(String.valueOf(arr.get(n/2)));
        bw.flush();
    }
}