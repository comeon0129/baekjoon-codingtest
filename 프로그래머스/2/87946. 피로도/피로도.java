//k: 유저의 현재 피로도
//던전별 dungeon[0]:최소 필요 피로도 dungeon[1]:소모 피로도
//구하는것: 유저가 탐험할 수 있는 최대 던전 수
import java.util.*;
class Solution {
    static int answer = -1;
    static ArrayList<Integer> order = new ArrayList<>();
    static void chooseDungeon(int num, int maxCnt, boolean[] visited, int k, int[][] dungeons){
        if(num == maxCnt){
            //선택된 순서대로 던전 탐사
            int cnt = 0;
            int temp = k;
            for(int i: order){
                if(temp >= dungeons[i][0]){
                    temp -= dungeons[i][1];
                    cnt++;
                }
            }
            answer = Math.max(answer,cnt);
            return;
        }
        for(int i=0; i<maxCnt; i++){
            if(!visited[i]){
                visited[i] = true;
                order.add(i);
                chooseDungeon(num+1,maxCnt,visited,k,dungeons);
                order.remove(order.size()-1);
                visited[i] = false;
            }
        }
        
    }
    public int solution(int k, int[][] dungeons) {
        int maxCnt = dungeons.length;
        boolean[] visited = new boolean[maxCnt];
        //0부터 maxCnt-1번째까지 순열
        chooseDungeon(0,maxCnt,visited,k,dungeons);
        return answer;
    }
}