import java.io.*;
import java.util.*;

public class Main {
	static int n,m;
	static boolean[] visited;
	static ArrayList<Integer> arr = new ArrayList<>();
	static void chooseNum(int cnt) {
		if(cnt ==m) {
			for(int i=0; i<arr.size(); i++)
				System.out.print(arr.get(i)+" ");
			System.out.println();
			return;
		}
		
		for(int i=1; i<=n; i++) {
			if(!visited[i]) {
				visited[i] = true;
				arr.add(i);
				chooseNum(cnt+1);
				visited[i] = false;
				arr.remove(arr.size()-1);
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		visited = new boolean[n+1];
		chooseNum(0);
	}
}
