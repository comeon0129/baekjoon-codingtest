import java.io.*;
import java.util.*;

public class Main {
	static int n,l,r;
	static int[][] land;
	static int[][] copyLand;
	static boolean[][] visited;
	
	static boolean inRange(int x, int y) {
		return 0<=x && x<n && 0<=y && y<n;
	}
	
	static int bfs(int a, int b) {
		int[] dx = {-1,1,0,0}; //상하좌우 순서
		int[] dy = {0,0,-1,1};
		Queue<int[]> q = new LinkedList<>();
		q.add(new int[] {a,b});		
		visited[a][b] = true;
	
		ArrayList<int[]> arr = new ArrayList<>();
		int people = land[a][b];
		int zone = 1; 
		arr.add(new int[] {a,b});

		while(!q.isEmpty()) {
			int[] pos = q.poll();
			int x = pos[0];
			int y = pos[1];
			
			for(int i=0; i<4; i++) {
				int nx = x+dx[i];
				int ny = y+dy[i];
				if(inRange(nx,ny) && Math.abs(land[nx][ny]-land[x][y]) >= l && Math.abs(land[nx][ny]-land[x][y]) <=r && !visited[nx][ny]) {
					visited[nx][ny] = true;
					q.add(new int[] {nx,ny});
					
					arr.add(new int[] {nx,ny});
					people += land[nx][ny];
					zone++;
				}
			}
		}
		
		int change = people / zone;
		for(int i=0; i<arr.size(); i++) {
			int[] pos = arr.get(i);
			land[pos[0]][pos[1]] = change;
		}
		
		return arr.size();
	}
	
    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer st  = new StringTokenizer(br.readLine());
    	n = Integer.parseInt(st.nextToken());
    	l = Integer.parseInt(st.nextToken());
    	r = Integer.parseInt(st.nextToken());
    	land = new int[n][n];
    	for(int i=0; i<n; i++) {
    		st = new StringTokenizer(br.readLine());
    		for(int j=0; j<n; j++) {
    			land[i][j] = Integer.parseInt(st.nextToken());
    		}
    	}
    	int days = 0;
    	
    	while(true) {
    		boolean isMove = false;
    		copyLand = new int[n][n];
    		visited = new boolean[n][n];
    		for(int i=0; i<n; i++) {
    			for(int j=0; j<n; j++) {
    				copyLand[i][j] = land[i][j];
    			}
    		}
    		for(int i=0; i<n; i++) {
    			for(int j=0; j<n; j++) {
    				if(!visited[i][j]) {
    					int count =bfs(i,j);
    					if(count >1)
    						isMove = true;
    				}
    			}
    		}
    		if(!isMove)
    			break;
    		days++;
    	}
    	System.out.println(days);
    }
}

