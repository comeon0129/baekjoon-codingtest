//자 어떻게 구현할지 생각이라는 것을 해보자
//우선 클래스를 하나 만들어서 장르랑 재생횟수. 고유번호 값을 묶어서 저장을 하자.
//지금 구해야하는게 우선 장르별 총 재생횟수, 장르별 곡 목록 이렇게 가면 되니까 그렇게 저장할 hashmap 두개를 만들자
//그리고 나서 장르별 총 횟수 기준으로 장르들을 내림차순으로 정렬한 리스트 만들기

import java.util.*;

class Song implements Comparable<Song>{
    public int play;
    public int number;
    
    Song(int play, int number){
        this.play = play;
        this.number = number;
    }
    
    @Override
    public int compareTo(Song o) {
        // 많이 재생된 순으로 정렬(내림차순)
        if(this.play < o.play){
            return 1; 
        }
        else if(this.play == o.play){
            if(this.number < o.number){
                return -1;
            }
            else if(this.number == o.number){
                return 0;
            }
            return 1;
        }
        return -1;
    }
    
}
    
class Solution {
    public int[] solution(String[] genres, int[] plays) {
        HashMap<String, Integer> map1 = new HashMap<>(); //장르별 총횟수를 구하는 map
        HashMap<String, ArrayList<Song>> map2 = new HashMap<>(); //장르별 곡 목록을 구하는 map

        int cnt = genres.length;
        for(int i=0; i<cnt; i++){
            // 1. 장르별 총횟수 구하기
            // getOrDefault를 사용하면 if-else 없이 더 깔끔하게 작성할 수 있습니다.
            map1.put(genres[i], map1.getOrDefault(genres[i], 0) + plays[i]);
            
            //2. 장르별 곡 목록 구해주기
            Song song = new Song(plays[i],i);
            if(!map2.containsKey(genres[i])){
                map2.put(genres[i],new ArrayList<>());
            }
            map2.get(genres[i]).add(song);
        }
        
        //3. 장르별 총 재생횟수 기준으로 장르 내림차순 정렬
        
        List<String> keyList = new ArrayList<>(map1.keySet());
        
        // keyList를 정렬하는데, 정렬 기준(Comparator)을 map1의 value로 설정
        Collections.sort(keyList, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                // map1.get(o2) - map1.get(o1) 이 양수면 o2가 더 크다는 뜻이므로 자리를 바꿈 (내림차순)
                return map1.get(o2).compareTo(map1.get(o1));
            }
        });
         
        //4. 장르 하나씩 순서대로 돌면서 map2에서 list 꺼내오고 그거 정렬해서 2개씩 answer에 반환하기
        ArrayList<Integer> answerList = new ArrayList<>(); // 크기가 가변적이므로 ArrayList 사용
        
        for (String genre : keyList) {
            ArrayList<Song> songList = map2.get(genre);
            
            // Song 클래스에 구현해둔 compareTo를 기준으로 정렬됨
            Collections.sort(songList); 
            
            // 장르에 속한 곡이 하나일 수도 있으므로, 최대 2개까지만 넣음
            int max = Math.min(songList.size(), 2);
            for (int i = 0; i < max; i++) {
                answerList.add(songList.get(i).number);
            }
        }
        
        // ArrayList를 int[] 배열로 변환
        int[] answer = new int[answerList.size()];
        for (int i = 0; i < answerList.size(); i++) {
            answer[i] = answerList.get(i);
        }
        
        return answer;
    }
}