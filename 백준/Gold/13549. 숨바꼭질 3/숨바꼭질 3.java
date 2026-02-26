import java.io.*;
import java.util.*;

public class Main {
	
	static int n,k; // n: 수빈이 위치, k: 동생 위치
	
	static int bfs() {
		int[] time = new int[100001];
		for(int i=0; i<=100000; i++) {
			time[i] = -1;
		}
		
		time[n] = 0;
		
		Deque<Integer> q = new LinkedList<>();
		
		q.add(n);
		
		while(!q.isEmpty()) {
			int x = q.poll();
			
			if(2*x <= 100000 && time[2*x] == -1) {
				time[2*x] = time[x];
				q.addFirst(2*x);
			}
			
			if(x-1 >= 0 && time[x-1] == -1) {
				time[x-1] = time[x]+1;
				q.addLast(x-1);
			}
			
			if(x+1 <= 100000 &&time[x+1] == -1) {
				time[x+1] = time[x]+1;
				q.addLast(x+1);
			}
			
			
		}
		return time[k];
	}
	
	
	
    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer st = new StringTokenizer(br.readLine());
    	n = Integer.parseInt(st.nextToken());
    	k = Integer.parseInt(st.nextToken());
    	
    	System.out.println(bfs());
    }
    	
}