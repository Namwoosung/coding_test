import java.util.Scanner;

public class Solution {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        StringBuilder sb = new StringBuilder();
        
        int T = sc.nextInt();
        for (int testCase = 1; testCase <= T; testCase++) {
            int N = sc.nextInt();
            int[] arr = new int[N];

            // 0번 index가 출발점
            arr[0] = sc.nextInt();
            int startIndex = 0;
            int endIndex = 0;
            int topIndex = 0;
            boolean isFound = false;
            int result = 0;

            for (int i = 1; i < N; i++) {
                arr[i] = sc.nextInt();

                if (!isFound) { // 아직 봉우리를 못 찾음
                    if (arr[i - 1] < arr[i]) endIndex++; // 오름차순 
                    else { // 더 작은 경우 발견
                        isFound = true;
                        topIndex = i - 1;
                        endIndex++;
                    }
                } else { // 봉우리를 찾고 내려가는 과정
                    if (arr[i - 1] > arr[i]) endIndex++; // 내림차순
                    else { // 다시 올라가는 경우 발견
                        isFound = false;
                        result += (topIndex - startIndex) * (endIndex - topIndex);
                        startIndex = i - 1;
                        topIndex = i - 1;
                        endIndex++;
                    }
                }
            }

            if (isFound) { // 내려가는 과정이 끝까지 간 경우
                result += (topIndex - startIndex) * (endIndex - topIndex);
            }

            sb.append("#").append(testCase).append(" ").append(result).append("\n");
        }
        
        System.out.print(sb);
        sc.close();
    }
}
