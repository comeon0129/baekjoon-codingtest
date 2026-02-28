import java.io.*;
import java.util.*;

public class Main {
	static int n,m;
	static int[][] map;
	static boolean[][][] visited;
	static int[][][] time;
	
	static boolean inRange(int x, int y) {
		return 0<= x && x<n && 0<= y && y<m;
	}
	
	static int bfs() {
		visited = new boolean[n][m][2];
		time = new int[n][m][2];
		int[] dx = {-1,1,0,0};
		int[] dy = {0,0,-1,1};
		
		Queue<int[]> q = new LinkedList<>();
		
		q.add(new int[] {0,0,0});
		visited[0][0][0] = true;
		time[0][0][0] = 1;
		
		while(!q.isEmpty()) {
			int[] pos = q.poll();
			int x = pos[0];
			int y = pos[1];
			int broken = pos[2];
			for(int i=0; i<4; i++) {
				int nx = x+dx[i];
				int ny = y+dy[i];
				
				if(!inRange(nx,ny))
					continue;
				
				//이동할 수 있는 곳으로 가는 경우
				if(map[nx][ny] == 0) {
					// 벽 부순적이 없고
					if(broken == 0) {
						//방문한적도 없다면
						if(!visited[nx][ny][0]) {
							visited[nx][ny][0] = true;
							time[nx][ny][0] = time[x][y][0]+1;
							q.add(new int[] {nx,ny,0});
						}
					}
					else {
						if(!visited[nx][ny][1]) {
							visited[nx][ny][1] = true;
							time[nx][ny][1] = time[x][y][1]+1;
							q.add(new int[] {nx,ny,1});
						}
					}
				}
				//벽으로 가는 경우
				else {
					if(broken == 0) {
						if(!visited[nx][ny][1]) {
							visited[nx][ny][1] = true;
							time[nx][ny][1] = time[x][y][0]+1;
							q.add(new int[] {nx,ny,1});
						}
					}
				}
			}
		}
		
		//둘다 방문이 가능한경우
		if(visited[n-1][m-1][0] && visited[n-1][m-1][1])
			return Math.min(time[n-1][m-1][0], time[n-1][m-1][1]);
		
		if(visited[n-1][m-1][0])
			return time[n-1][m-1][0];
		
		if(visited[n-1][m-1][1])
			return time[n-1][m-1][1];
		
		return -1;
	}
	
    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer st = new StringTokenizer(br.readLine());
    	n = Integer.parseInt(st.nextToken());
    	m = Integer.parseInt(st.nextToken());
    	map = new int[n][m];
    	for(int i=0; i<n; i++) {
    		String input = br.readLine();
    		for(int j=0; j<m; j++) {
    			map[i][j]=  input.charAt(j) - '0';
    		}
    	}
    	System.out.println(bfs());
    	
    }
    	
}