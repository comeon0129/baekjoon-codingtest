import java.io.*;
import java.util.*;

class Shark {
	int r,c; //위치
	int s; //속력
	int d; //이동방향
	int z; //크기
	
	public Shark(int r, int c, int s, int d, int z) {
		this.r = r;
		this.c = c;
		this.s = s;
		this.d = d;
		this.z = z;
		// TODO Auto-generated constructor stub
	}
}

public class Main {
	static int R,C,M; // R: 행 C: 열 M: 상어의 개수
	
	static int[] dx = {-1,1,0,0}; //상 하 우 좌 순서
	static int[] dy = {0,0,1,-1};
	static int[] reverse = {1,0,3,2}; //방향 뒤집힐때 사용
	
	
	static ArrayList<Shark> sharks = new ArrayList<>();
	
	static ArrayList<Shark>[][] sea;
	
	static boolean inRange(int x, int y) {
		return 1<= x && x<=R && 1<=y && y<=C;
	}
	
    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer st = new StringTokenizer(br.readLine());
    	
    	R = Integer.parseInt(st.nextToken());
    	C = Integer.parseInt(st.nextToken());
    	M = Integer.parseInt(st.nextToken());
    	
    	for(int i=0; i<M; i++) {
    		st = new StringTokenizer(br.readLine());
    		int r = Integer.parseInt(st.nextToken());
    		int c = Integer.parseInt(st.nextToken());
    		int s = Integer.parseInt(st.nextToken());
    		int d = Integer.parseInt(st.nextToken()) -1;
    		int z = Integer.parseInt(st.nextToken());
    		sharks.add(new Shark(r,c,s,d,z));
    	}
    	
    	sea = new ArrayList[R+1][C+1];
    	
    	for(int i=1; i<=R; i++) {
    		for(int j=1; j<=C; j++) {
    			sea[i][j] = new ArrayList<>();
    		}
    	}
    	
    	//1. 바다안에 상어 채우기
    	for(Shark shark: sharks) {
    		sea[shark.r][shark.c].add(shark);
    	}
    	
    	int fisherMan = 0;
    	int cnt = 0;
    	
    	while(fisherMan < C) {
    		//2. 낚시왕 한칸 이동
    		fisherMan++;
    		//3. 낚시왕이 있는 열에 있는 상어 중에서 땅과 제일 가까운 상어 잡기
    		for(int i=1; i<=R; i++) {
    			if(!sea[i][fisherMan].isEmpty()) {
    				cnt+= sea[i][fisherMan].get(0).z;
    				sharks.remove(sea[i][fisherMan].get(0)); //상어무리에서 해당 상어 삭제
    				sea[i][fisherMan].clear(); //바다 비우기
    				break;
    			}
    		}
    		//4. 상어 이동 시작
    		Shark[][] nextSea = new Shark[R+1][C+1];
    		ArrayList<Shark> aliveSharks = new ArrayList<>();
    		
    		for(Shark shark: sharks) {
    			// 상어의 방향이 좌 또는 우인 경우 (시간초과를 막기 위해 s를 주기로 나눠줌) 
    			int move;
    			if(shark.d == 2 || shark.d == 3)
    				move = shark.s % (2*(C-1));
    			else {
    				move = shark.s % (2*(R-1));
    			}
    			while(move >0) {
    				int nx = shark.r+dx[shark.d];
    				int ny = shark.c+dy[shark.d];
    				
    				if(!inRange(nx,ny)) {
    					shark.d = reverse[shark.d]; //방향 전환 하고
    					nx = shark.r+dx[shark.d]; //해당방향에 맞게 이동한거 갱신
    					ny = shark.c+dy[shark.d];
    				}
    				shark.r = nx;
    				shark.c = ny;
    				
    				move--;
    			}
    			// 새로운 칸에 이미 상어가 있는지 확인
    			if(nextSea[shark.r][shark.c] == null) {
    				nextSea[shark.r][shark.c] = shark;
    			}
    			//이미 상어가 있다면 크기 비교
    			else {
    				if(nextSea[shark.r][shark.c].z < shark.z) {
    					nextSea[shark.r][shark.c] = shark;
    				}
    			}
    		}
    		//5. 이동 완료 후 데이터 동기화
    		sharks.clear();
    		for(int i=1; i<=R; i++) {
    			for(int j=1; j<=C; j++) {
    				sea[i][j].clear();
    				if(nextSea[i][j] != null) {
    					sea[i][j].add(nextSea[i][j]);
    					sharks.add(nextSea[i][j]);
    				}
    			}
    		}
    	}
    	
    	System.out.println(cnt);
    }
}
