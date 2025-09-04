import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;

public class Main {
	static class Rec{
		int height;
		int startIndex;
		public Rec(int height, int startIndex) {
			this.height = height;
			this.startIndex = startIndex;
		}
	}
	
	public static void main(String[] args) throws Exception {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	
    	int N = Integer.parseInt(br.readLine());
    	
    	int result = 0;
    	Deque<Rec> candiate = new ArrayDeque<>();
    	for(int i = 0; i < N; i++) {
    		int num = Integer.parseInt(br.readLine());
    		
    		if(candiate.isEmpty() || candiate.peekLast().height < num) candiate.add(new Rec(num, i));
    		else {
    			int prevIndex = i;
    			
    			while(!candiate.isEmpty()) {
    				if(candiate.peekLast().height <= num) break;
    				else {
    					Rec now = candiate.pollLast();
    					result = Math.max(result, now.height * (i - now.startIndex));
    					prevIndex = now.startIndex;
    				}
    			}
    			if(candiate.isEmpty() || candiate.peekLast().height != num) candiate.add(new Rec(num, prevIndex));
    		}
    		
    	}
		while(!candiate.isEmpty()) {
			Rec now = candiate.pollLast();
			result = Math.max(result, now.height * (N - now.startIndex));
		}
    	
    	System.out.println(result);
    }
}