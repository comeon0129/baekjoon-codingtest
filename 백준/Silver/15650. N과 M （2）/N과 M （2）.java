import java.io.*;
import java.util.*;

public class Main {
	static int n,m;
	static ArrayList<Integer> arr = new ArrayList<>();
	static StringBuilder sb = new StringBuilder();
	static void chooseNum(int start, int cnt) {
		//1. 종료 조건: m개를 다 뽑았을때만 출력하고 끝낸다!
		if(cnt ==m) {
			for(int i=0; i<arr.size(); i++)
				sb.append(arr.get(i)+" ");
			sb.append("\n");
			return;
		}
		
		for(int i=start; i<=n; i++) {
			arr.add(i);
			//다음 재귀에서는 현재 내가뽑은 숫자 i보다 무조건 1큰 숫자에서부터 뽑기
			chooseNum(i+1, cnt+1);
			arr.remove(arr.size()-1);
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		chooseNum(1,0);
		System.out.println(sb);
	}
}

 