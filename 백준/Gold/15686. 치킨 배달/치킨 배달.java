import java.io.*;
import java.util.*;

public class Main {
	public static int n,m; //n: 도시 가로 세로 크기, m: 최대로 고를 치킨집 수
	
	public static ArrayList<int[]> house = new ArrayList<>();
	public static ArrayList<int[]> chicken = new ArrayList<>();
	
	public static int minResult = Integer.MAX_VALUE;
	public static boolean[] open;
	
	public static void calcMin() {
		
		int sum = 0;
		for(int i=0; i<house.size(); i++) {
			int min = Integer.MAX_VALUE;
			int[] house_pos = house.get(i);
			int x = house_pos[0];
			int y = house_pos[1];
			int dist = 0;
			for(int j=0; j<chicken.size(); j++) {
				if(open[j]) {
					int [] chicken_pos = chicken.get(j);
					dist = Math.abs(x-chicken_pos[0])+Math.abs(y-chicken_pos[1]);
					if(dist<min)
						min = dist;
				}
			}
			sum+=min;
		}
		if(sum < minResult)
			minResult = sum;
		
		return;
	}
	
	public static void chooseChicken(int start, int depth) {
		if(depth == m) {
			calcMin();
			return;
		}
		
		for(int i=start; i<chicken.size(); i++) {
			open[i] = true;
			chooseChicken(i+1,depth+1);
			open[i] = false;
		}
		
		return;
	}
	
    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer st = new StringTokenizer(br.readLine());
    	n = Integer.parseInt(st.nextToken());
    	m = Integer.parseInt(st.nextToken());
    	for(int i=0; i<n; i++) {
    		st = new StringTokenizer(br.readLine());
    		for(int j=0; j<n; j++) {
    			int temp = Integer.parseInt(st.nextToken());
    			if(temp == 1)
    				house.add(new int[] {i,j});
    			else if(temp == 2)
    				chicken.add(new int[] {i,j});
    		}
    	}
    	open = new boolean[chicken.size()];
    	chooseChicken(0,0);
    	System.out.println(minResult);
    }
}