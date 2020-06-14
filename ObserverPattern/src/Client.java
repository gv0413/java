import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/*
옵저버패턴
데이터 변경이 이루어지는 클래스에서는 subject클래스를 상속받아서 기능변경이 이루어질때 통보하도록 활용
observer인터페이스 이용
실제 구체화된 부분은 ScoreRecord에서 알지 못해도 되도록한다
scoreRecord는 subject를 통해 옵저버 인터페이스와 소통

양방향 연관관계 -> 단방향 연관관계 -> 의존 낮춤. 결합도 낮춤
은닉. observer interface통해
*/


public class Client {
   public static void main(final String[] args) {
      ScoreRecord scoreRecord = new ScoreRecord();
      DataSheetView dataSheetView3 = new DataSheetView(scoreRecord, 3);
      DataSheetView dataSheetView5 = new DataSheetView(scoreRecord, 5);
      MinMaxView minMaxView = new MinMaxView(scoreRecord);
      DescendingView dv = new DescendingView(scoreRecord);

      scoreRecord.attach(dataSheetView3);
      scoreRecord.attach(dataSheetView5);
      scoreRecord.attach(minMaxView);
      scoreRecord.attach(dv);

      for (int index = 1; index <= 5; index++) {
         final int score = index * 10;
         System.out.println("Adding " + score);
         scoreRecord.addScore(score);
      }
   }
}

interface Observer {
   abstract public void update(); 
}

// 추상화 클래스 : 통보 대상 관리
abstract class Subject {
   private List<Observer> observers = new ArrayList<Observer>(); //여러가지로 받을 수 있도록 List
   
   //추가
   public void attach(Observer observer) {
      observers.add(observer);
   }
   
   //삭제
   public void detach(Observer observer) {
      observers.remove(observer);
   }

   //통보해야할 때
   public void notifyObservers() {
      for( Observer o : observers) o.update(); //옵저버가 가지고있는 업데이트함수를 돌면서 업데이트 할 수 있도록
   }
}

//값의 변경이 이루어짐 
class ScoreRecord extends Subject{
   private List<Integer> scores = new ArrayList<Integer>();

   public void addScore(int score) { // 새로운 점수 추가
      scores.add(score);
      notifyObservers(); //통보 기능 추가 -> 앞에서 뭐가 바껴도 scoreRecord에서는 바뀔게 없음
   }

   public List<Integer> getScoreRecord() {
      return scores;
   }
}

class DataSheetView implements Observer{
   private final ScoreRecord scoreRecord;
   private final int viewCount;

   public DataSheetView(final ScoreRecord scoreRecord, final int viewCount) {
      this.scoreRecord = scoreRecord;
      this.viewCount = viewCount;
   }

   public void update() {
      List<Integer> record = scoreRecord.getScoreRecord();
      displayScores(record, viewCount);
   }

   private void displayScores(List<Integer> record, final int viewCount) {
      System.out.println("List of" + viewCount + " entries: ");
      for(int i=0; i < viewCount && i < record.size(); i++) {
         System.out.print(record.get(i)+ " ");
      }
      System.out.println();
   }
}

class MinMaxView implements Observer {
   private ScoreRecord scoreRecord ;

   public MinMaxView(ScoreRecord scoreRecord) {
      this.scoreRecord = scoreRecord;
   }

   public void update() {
      List<Integer> record = scoreRecord.getScoreRecord();
      displayMinMax(record);
   }

   private void displayMinMax(List<Integer> record) {
      int min = Collections.min(record, null);
      int max = Collections.max(record, null);
      System.out.println("Min: "+min+ " Max: "+max);
   }

}


class DescendingView implements Observer {
   private ScoreRecord scoreRecord ;

   public DescendingView(ScoreRecord scoreRecord) {
      this.scoreRecord = scoreRecord;
   }

   public void update() {
      List<Integer> record = scoreRecord.getScoreRecord();
      displayDescending(record);
   }

   private void displayDescending(List<Integer> record) {
      Collections.sort(record, Comparator.reverseOrder()); //내림차순
      System.out.println("Reverse Order : ");
      for(int i=0; i < record.size(); i++) {
         System.out.print(record.get(i)+ " ");
      }
      System.out.println();
   }

}