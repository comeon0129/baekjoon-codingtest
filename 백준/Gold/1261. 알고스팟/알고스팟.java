import java.io.*;
import java.util.*;

public class Main {
	static int m,n; // m: 가로, n: 세로
	
	static int[][] maze;
	
	static int bfs() {
		int[] dx = {-1,1,0,0};
		int[] dy = {0,0,-1,1};
		
		int[][] dist = new int[n][m];
		
		for(int i=0; i<n; i++) {
			for(int j=0; j<m; j++) {
				dist[i][j] = -1;
			}
		}
		dist[0][0] = 0;
		
		Deque<int[]> q = new LinkedList<>();
		
		q.add(new int[] {0,0});
		
		while(!q.isEmpty()) {
			int[] pos = q.poll();
			int x = pos[0];
			int y = pos[1];
			
			for(int i=0; i<4; i++) {
				int nx = x+dx[i];
				int ny = y+dy[i];
				
				if(inRange(nx,ny) && dist[nx][ny] == -1) {
					// 비용이 낮은 0은 앞에 넣고
					if(maze[nx][ny] == 0) {
						dist[nx][ny] = dist[x][y];
						q.addFirst(new int[] {nx,ny});
					}
					else {
						// 비용이 높은 1은 큐에 뒤에넣기
						dist[nx][ny] = dist[x][y]+1;
						q.addLast(new int[] {nx,ny});
					}
				}
			}
		}
//		//그렇게 해서 비용이 낮은 0방향으로의 탐색이 항상 먼저 이뤄질 것이기에 이게 맞다.
//		//한번 방문한 곳은 dist가 다시 갱신되지 않으니까 말이다.
//		System.out.println("디버깅용 코드!");
//		for(int i=0; i<n; i++) {
//			for(int j=0; j<m; j++) {
//				System.out.print(dist[i][j]+" ");
//			}
//			System.out.println();
//		}
//		System.out.println("여기까지 디버깅용");
		return dist[n-1][m-1];
		
	}
	
	static boolean inRange(int x, int y) {
		return 0<=x && x<n && 0<=y && y<m;
	}
	
    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer st = new StringTokenizer(br.readLine());
    	m = Integer.parseInt(st.nextToken());
    	n = Integer.parseInt(st.nextToken());
    	maze = new int[n][m];
    	for(int i=0; i<n; i++) {
    		String input = br.readLine();
    		for(int j=0; j<m; j++) {
    			maze[i][j] = input.charAt(j)-'0';
    		}
    	}
    	
    	System.out.println(bfs());
    	
    }
    	
}
