import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Solution{
	// 고객은 PQ로 관리. 오래 기다린 순서로 정렬
	static class Customer implements Comparable<Customer>{
		int id;
		int arrivalTime;
		int watingTime;
		int infoNum;
		int RepairNum;

		public Customer(int id, int arrivalTime) {
			this.id = id;
			this.arrivalTime = arrivalTime;
		}

		@Override
		public int compareTo(Customer o) {
			if(this.watingTime != o.watingTime) return this.watingTime - o.watingTime;
			if(this.infoNum != o.infoNum) return this.infoNum - o.infoNum;
			return this.id - o.id;
		}
	}
	
	static int[] infoProcessTime = new int[10];
	static int[] infoEndTime = new int[10];
	static int[] repairProcessTime = new int[10];
	static int[] repairEndTime = new int[10];
	static PriorityQueue<Customer> customers = new PriorityQueue<>();
	
    public static void main(String[] args) throws Exception {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer st;
    	StringBuilder sb = new StringBuilder();
    	
    	int T = Integer.parseInt(br.readLine());
    	for(int testCase = 1; testCase <= T; testCase++) {
    		customers.clear();
    		
    		// 입력값 처리
    		st = new StringTokenizer(br.readLine());
    		int N = Integer.parseInt(st.nextToken());
    		int M = Integer.parseInt(st.nextToken());
    		int K = Integer.parseInt(st.nextToken());
    		int targetInfo = Integer.parseInt(st.nextToken());
    		int targetRep = Integer.parseInt(st.nextToken());

    		st = new StringTokenizer(br.readLine());
    		for(int i = 1; i <= N; i++) {
    			infoEndTime[i] = 0;
    			infoProcessTime[i] = Integer.parseInt(st.nextToken());
    		}
    		st = new StringTokenizer(br.readLine());
    		for(int i = 1; i <= M; i++) {
    			repairEndTime[i] = 0;
    			repairProcessTime[i] = Integer.parseInt(st.nextToken());
    		}
    		st = new StringTokenizer(br.readLine());
    		for(int i = 1; i <= K; i++) {
    			customers.add(new Customer(i, Integer.parseInt(st.nextToken())));
    		}

    		// 먼저 접수 창고 먼저 처리
    		for(int i = 0; i < K; i++) {
    			Customer now = customers.poll();
    			
    			boolean flag = false;
    			int minInfo = 0; int minInfoTime = Integer.MAX_VALUE;
    			for(int j = 1; j <= N; j++) {
    				if(minInfoTime > infoEndTime[j]) {
    					minInfo = j;
    					minInfoTime = infoEndTime[j];
    				}
    				
    				// 아직 처리 중이면 pass
    				if(infoEndTime[j] > now.arrivalTime) continue;
    				
    				// j번 창구에서 처리 진행
    				infoEndTime[j] = now.arrivalTime + infoProcessTime[j];;
    				now.infoNum = j;
    				now.watingTime = infoEndTime[j];
    				flag = true;
    				break;
    			}
    			
    			// 만약 모든 창구가 일 중이었다면, 가장 먼저 끝나는 창구로 배정
    			if(!flag) {
    				infoEndTime[minInfo] += infoProcessTime[minInfo];
    				now.infoNum = minInfo;
    				now.watingTime = infoEndTime[minInfo];
    			}
    			
    			customers.add(now);
    		}
    		
    		int result = 0;
    		// 정비 접수 처리
    		while(!customers.isEmpty()) {
    			Customer now = customers.poll();
    			
    			boolean flag = false;
    			int minRepair = 0; int minRepairTime = Integer.MAX_VALUE;
    			for(int i = 1; i <= M; i++) {
    				if(minRepairTime > repairEndTime[i]) {
    					minRepair = i;
    					minRepairTime = repairEndTime[i];
    				}
    				
    				if(repairEndTime[i] > now.watingTime) continue;
    				
    				//i번째 창구에서 처리
    				repairEndTime[i] = now.watingTime + repairProcessTime[i];
    				now.RepairNum = i;
    				flag = true;
    				if(now.infoNum == targetInfo && now.RepairNum == targetRep) result += now.id;
    				break;
    			}
    			
    			if(!flag) {
    				repairEndTime[minRepair] += repairProcessTime[minRepair];
    				now.RepairNum = minRepair;
    				if(now.infoNum == targetInfo && now.RepairNum == targetRep) result += now.id;
    			}
    		}
    		
    		if(result == 0) result = -1;
    		
    		sb.append("#").append(testCase).append(" ").append(result).append("\n");
    	}
    	System.out.print(sb);
    }
}