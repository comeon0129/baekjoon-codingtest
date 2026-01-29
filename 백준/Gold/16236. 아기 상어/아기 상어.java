import java.io.*;
import java.util.*;

class Shark {
	int x,y,size,eatCount,time;
	public Shark(int x, int y, int size, int eatCount, int time) {
		this.x = x;
		this.y = y;
		this.size = size;
		this.eatCount = eatCount;
		this.time = time;
	}
}

class Fish implements Comparable<Fish> {
	int x,y, dist;
	
	public Fish(int x, int y, int dist) {
		this.x = x;
		this.y = y;
		this.dist = dist;
	}
	
	
	@Override
	public int compareTo(Fish o) {
		if(this.dist != o.dist) return this.dist - o.dist;
		if(this.x != o.x) return this.x - o.x;
		return this.y - o.y;
	}
	
}


public class Main {
	public static int n; 
	public static int[][] sea;
	public static Shark shark;
	public static boolean[][] visited;
	public static int[][] time;
	public static ArrayList<Fish> fishes;
	
	public static boolean inRange(int x, int y) {
		return 0<=x && x<n && 0<=y && y<n;
	}
	
	public static void bfs() {
		Queue<int[]> q = new LinkedList<>();
		visited = new boolean[n][n];
		time = new int[n][n];
		fishes = new ArrayList<>();
		
		q.add(new int[] {shark.x, shark.y});
		visited[shark.x][shark.y] = true;
		
		int[] dx = {-1,1,0,0}; //상하좌우 순서
		int[] dy = {0,0,-1,1};
		
//		1.현재 상어의 위치에서 갈 수 있는 곳들 전부 탐사하기. 시간은 time 배열에, 
		while(!q.isEmpty()) {
			int[] pos = q.poll();
			int x = pos[0];
			int y = pos[1];
			
			for(int i=0; i<4; i++) {
				int nx = x+dx[i];
				int ny = y+dy[i];
				
				if(inRange(nx,ny) && sea[nx][ny] <= shark.size && !visited[nx][ny]) { //수정사항1.visited[][] 빼먹음 
					visited[nx][ny] = true;
					time[nx][ny] = time[x][y]+1;
					q.add(new int[]{nx,ny});
					if(sea[nx][ny] != 0 && sea[nx][ny] <shark.size) { //수정사항2. 상어보다 사이즈가 작을때라는 조건 빼먹음
						fishes.add(new Fish(nx, ny, time[nx][ny]));
					}
				}
			}
		}
//		2.물고기들 순서에 맞게 정렬하기
		Collections.sort(fishes);
		
		if(fishes.size() == 0) { //한 마리도 잡지 못하는 경우
			return;
		}
		else {
			sea[fishes.get(0).x][fishes.get(0).y] = 0;
			shark.time += fishes.get(0).dist;
			shark.eatCount ++;
			if(shark.eatCount == shark.size) { //상어 사이즈랑 먹은 횟수가 같아지면
				shark.size++;
				shark.eatCount = 0;
			}
			shark.x = fishes.get(0).x;
			shark.y = fishes.get(0).y;
			bfs();
		}
	}
	
    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer st = new StringTokenizer(br.readLine());
    	n = Integer.parseInt(st.nextToken());
    	sea = new int[n][n];
    	for(int i=0; i<n; i++) {
    		st = new StringTokenizer(br.readLine());
    		for(int j=0; j<n; j++) {
    			sea[i][j] = Integer.parseInt(st.nextToken());
    			if(sea[i][j] == 9) { //상어라면
    				shark = new Shark(i, j, 2, 0, 0);
    				sea[i][j] = 0; //수정 사항 3. 상어는 이제 이동하니까 바다에서 날려줘야함.
    			}
    		}
    	}
    	bfs();
    	System.out.println(shark.time);
    }
}
