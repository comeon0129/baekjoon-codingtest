import java.io.*;
import java.util.*;

public class Main {
	
	public static int n,m,v;
	public static int[][] graph;
	public static boolean[] visited;
	public static StringBuilder sb = new StringBuilder(); //출력을 모아놓을 친구
	
	
	public static void dfs(int cur) {
		visited[cur] = true;
		sb.append(cur).append(" ");
		for(int next= 1; next<=n; next++) {
			if(graph[cur][next] == 1 && !visited[next]) {
				dfs(next);
			}
		}
	}
	
	public static void bfs(int start) {
		Queue<Integer> q = new LinkedList<>();
		q.add(start);
		visited[start] = true; //맨 시작점 방문 처리!
		
		while(!q.isEmpty()) {
			int cur = q.poll();
			sb.append(cur).append(" "); // 큐에서 뺄 때 기록
			
			for(int next = 1; next <=n; next++) {
				if(graph[cur][next] ==1 && !visited[next]) {
					visited[next] = true; // 큐에 넣을때 방문처리
					q.add(next);
				}
			}
		}
	}
	
    public static void main(String[] args) throws IOException {
//  1. 키보드 입력을 버퍼에 담는다.
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    
//    2. 공백으로 구분된 줄하나 받기
    String inputLine = br.readLine();
//    3. String Tokenizer를 이용해서 공백 기준으로 쪼개기
    StringTokenizer st = new StringTokenizer(inputLine);
    
    n = Integer.parseInt(st.nextToken());
    m = Integer.parseInt(st.nextToken());
    v = Integer.parseInt(st.nextToken());
    
    graph = new int[n+1][n+1];
    visited = new boolean[n+1];
    
    while(m-->0) {
    	inputLine = br.readLine();
    	st = new StringTokenizer(inputLine);
    	int a= Integer.parseInt(st.nextToken());
    	int b= Integer.parseInt(st.nextToken());
    	graph[a][b] = 1;
    	graph[b][a] = 1;
    }
    dfs(v);
    sb.append("\n");
    
    visited = new boolean[n+1];
    bfs(v);
    
    System.out.println(sb);
       
    }
}
