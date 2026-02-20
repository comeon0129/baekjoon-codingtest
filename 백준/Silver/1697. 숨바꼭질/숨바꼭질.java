import java.io.*;
import java.util.*;

public class Main {
	static int n,k;
	static boolean[] visited;
	static int[] time;
	
	public static int bfs() {
		if(n == k) return 0;
		Queue<Integer> q = new LinkedList<>();
		q.add(n);
		visited[n] = true;
		
		while(!q.isEmpty()) {
			int x = q.poll();
			
			if(x-1 >= 0 && !visited[x-1]) {
				q.add(x-1);
				visited[x-1] = true;
				time[x-1] = time[x]+1;
				if(x-1 == k)
					return time[x-1];
			}
			if(x+1 <= 100000 &&!visited[x+1]) {
				q.add(x+1);
				visited[x+1] = true;
				time[x+1] = time[x]+1;
				if(x+1 == k)
					return time[x+1];
			}
			if(2*x <= 100000 &&!visited[2*x]) {
				q.add(2*x);
				visited[2*x] = true;
				time[2*x] = time[x]+1;
				if(2*x == k)
					return time[2*x];
			}
		}
		
		return -1;
		
	}
	
    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer st  = new StringTokenizer(br.readLine());
    	n = Integer.parseInt(st.nextToken());
    	k = Integer.parseInt(st.nextToken());
    	visited = new boolean[100001];
    	time = new int[100001];
    	
    	System.out.println(bfs());
    	
    }
    	
}
