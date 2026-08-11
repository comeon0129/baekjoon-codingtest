import java.util.*;

class Solution {
    public int[] solution(int[] fees, String[] records) {
        Map<String, Integer> inTimeMap = new HashMap<>(); //입차 시간 기록
        Map<String, Integer> totalTimeMap = new HashMap<>();
        
        for(String r : records){
            String[] s = r.split(" ");
            String time = s[0];
            String car = s[1];
            String state = s[2];
            
            //제일 중요한 포인트! split으로 깔끔하게 분 단위 파싱
            String[] t = time.split(":");
            int minutes = Integer.parseInt(t[0]) * 60 + Integer.parseInt(t[1]);
            
            if(state.equals("IN")){
                inTimeMap.put(car,minutes); //입차 시간 기록
            }
            //OUT인경우: 누적 시간에 (현재 시간- 입차 시간) 더하고, 입차 맵에서 제거
            else{
                
                int inTime = inTimeMap.remove(car);
                totalTimeMap.put(car, totalTimeMap.getOrDefault(car,0) + (minutes - inTime));
            }
            
        }
        
        //두번째로 중요한 포인트!예외처리 23:59 출차 처리 로직
        int endOfDay = 23*60+59;
        
        for(String car : inTimeMap.keySet()){
            int inTime = inTimeMap.get(car);
            totalTimeMap.put(car, totalTimeMap.getOrDefault(car,0) + (endOfDay - inTime));
        }

        //3. 차량번호 순서대로 정렬하기(오름차순)
        List<String> cars = new ArrayList<>(totalTimeMap.keySet());
        Collections.sort(cars);

        //4. 요금 계산 (올림 처리 주의)
        int[] answer = new int[cars.size()];
        for(int i=0; i<cars.size(); i++){
            int time = totalTimeMap.get(cars.get(i));
            if(time <= fees[0]) { //기본 시간 이하
                answer[i] = fees[1];
            }
            else{
                answer[i] = fees[1] + (int) Math.ceil((time - fees[0]) / (double) fees[2]) * fees[3];
            }
        }
        
        return answer;
    }
}