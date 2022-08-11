package zuoye;
import javax.swing.*;
import java.awt.*;

public class Disc extends JButton{
   int number;
   TowerPoint point;

   Disc(){
      setBackground(Color.cyan);
   }

   public void setNumber(int n){
       number=n;
   }
   public int getNumber(){
       return number;
   }
   public void setPoint(TowerPoint p){
      point=p;
   }
   public TowerPoint getPoint(){
      return point;
   }
}
