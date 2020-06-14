import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/*
����������
������ ������ �̷������ Ŭ���������� subjectŬ������ ��ӹ޾Ƽ� ��ɺ����� �̷������ �뺸�ϵ��� Ȱ��
observer�������̽� �̿�
���� ��üȭ�� �κ��� ScoreRecord���� ���� ���ص� �ǵ����Ѵ�
scoreRecord�� subject�� ���� ������ �������̽��� ����

����� �������� -> �ܹ��� �������� -> ���� ����. ���յ� ����
����. observer interface����
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

// �߻�ȭ Ŭ���� : �뺸 ��� ����
abstract class Subject {
   private List<Observer> observers = new ArrayList<Observer>(); //���������� ���� �� �ֵ��� List
   
   //�߰�
   public void attach(Observer observer) {
      observers.add(observer);
   }
   
   //����
   public void detach(Observer observer) {
      observers.remove(observer);
   }

   //�뺸�ؾ��� ��
   public void notifyObservers() {
      for( Observer o : observers) o.update(); //�������� �������ִ� ������Ʈ�Լ��� ���鼭 ������Ʈ �� �� �ֵ���
   }
}

//���� ������ �̷���� 
class ScoreRecord extends Subject{
   private List<Integer> scores = new ArrayList<Integer>();

   public void addScore(int score) { // ���ο� ���� �߰�
      scores.add(score);
      notifyObservers(); //�뺸 ��� �߰� -> �տ��� ���� �ٲ��� scoreRecord������ �ٲ�� ����
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
      Collections.sort(record, Comparator.reverseOrder()); //��������
      System.out.println("Reverse Order : ");
      for(int i=0; i < record.size(); i++) {
         System.out.print(record.get(i)+ " ");
      }
      System.out.println();
   }

}