package zuoye;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.*;
import java.nio.charset.StandardCharsets;

public class AutoMoveDisc extends JDialog implements ActionListener{
    int amountOfDisc=3;
    TowerPoint [] pointA,pointB,pointC;
    char [] towerName;
    Container con;
    StringBuffer moveStep;
    JTextArea showStep;
    JButton bStart,bStop,bContinue;
    JMenu bClose;
    JMenuItem item;
    Timer time;
    File file =new File("a.txt");
    int i=0,number=0;
    AutoMoveDisc(Container con){
        setModal(true);
        setTitle("自动演示搬盘子过程");
        this.con=con;
        item=new JMenuItem("将文本保存到a.txt");
        moveStep=new StringBuffer();
        time=new Timer(1000,this);
        time.setInitialDelay(10);
        showStep=new JTextArea(10,12); 
        bStart=new JButton("演示");
        bStop=new JButton("暂停");
        bContinue=new JButton("继续");
        bClose=new JMenu("保存");
        bClose.add(item);
        bStart.addActionListener(this);
        bStop.addActionListener(this);
        bContinue.addActionListener(this);
        item.addActionListener(this);
        JMenuBar south=new JMenuBar();   //特意改变了一下south的类型，可以有item
        south.setLayout(new FlowLayout());
        south.add(bStart);
        south.add(bStop);
        south.add(bContinue);
        south.add(bClose);

        add(new JScrollPane(showStep),BorderLayout.CENTER);
        add(south,BorderLayout.SOUTH);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        towerName=new char[3];
        addWindowListener(new WindowAdapter(){
                           public void windowClosing(WindowEvent e){
                              time.stop();
                              setVisible(false);
                           }
                         });
    }
    public void setPointA(TowerPoint [] pointA){
        this.pointA=pointA;
    }
    public void setPointB(TowerPoint [] pointB){
        this.pointB=pointB;
    }
    public void setPointC(TowerPoint [] pointC){
        this.pointC=pointC;
    }
    public void setTowerName(char name[]){
         if(name[0]==name[1]||name[0]==name[2]||name[1]==name[2]){
           towerName[0]='A';
           towerName[1]='B';
           towerName[2]='C';
         }
         else  
           towerName=name;
    }
    public void setAmountOfDisc(int n){
         amountOfDisc=n;
    }
   public void actionPerformed(ActionEvent e) {
      if(e.getSource()==time){
            number++;
            char cStart,cEnd;
            if(i<=moveStep.length()-2){
              cStart=moveStep.charAt(i);
              cEnd=moveStep.charAt(i+1);
              showStep.append("("+number+")从"+cStart+"座搬一个盘子到"+cEnd+"座\n");
              autoMoveDisc(cStart,cEnd);
            }
            i=i+2;
            if(i>=moveStep.length()-1){
                time.stop();
            }  
      } 
      else if(e.getSource()==bStart){
           if(moveStep.length()==0){ 
             if(time.isRunning()==false){
                 i=0;
                 moveStep=new StringBuffer();
                 setMoveStep(amountOfDisc,towerName[0],towerName[1],towerName[2]);
                 number=0;
                 time.start();    
             }
           }
      }
      else if(e.getSource()==bStop){
           if(time.isRunning()==true)
                time.stop();    
      }
      else if(e.getSource()==bContinue){
           if(time.isRunning()==false)
                time.restart();    
      }
      else if(e.getSource()==item){        //把关闭改成了保存
          time.stop();
          byte fileContent[]=showStep.getText()   //读取JArea里面的文本信息，下面为写入文件
                  .getBytes();
          try {
              OutputStream out =new FileOutputStream(file);
             out.write(fileContent);
              out.close();
          } catch (FileNotFoundException fileNotFoundException) {
              fileNotFoundException.printStackTrace();
          } catch (IOException ioException) {
              ioException.printStackTrace();
          }

          //setVisible(false);

      }  
   }
   private void setMoveStep(int amountOfDisc,char one,char two,char three){
      if(amountOfDisc==1){
             moveStep.append(one);
             moveStep.append(three);   
      }
      else{
             setMoveStep(amountOfDisc-1,one,three,two);
             moveStep.append(one);
             moveStep.append(three);
             setMoveStep(amountOfDisc-1,two,one,three);   
      } 
   }
   private void autoMoveDisc(char cStart,char cEnd){
      Disc disc=null; 
      if(cStart==towerName[0]){
               for(int i=0;i<pointA.length;i++){
                  if(pointA[i].isHaveDisc()==true){
                      disc=pointA[i].getDiscOnPoint();
                      pointA[i].setHaveDisc(false);      
                      break;
                  }
               }
      }
      if(cStart==towerName[1]){
               for(int i=0;i<pointB.length;i++){
                  if(pointB[i].isHaveDisc()==true){
                      disc=pointB[i].getDiscOnPoint();
                      pointB[i].setHaveDisc(false); 
                      break;
                  }
               }
      }
      if(cStart==towerName[2]){
               for(int i=0;i<pointC.length;i++){
                  if(pointC[i].isHaveDisc()==true){
                      disc=pointC[i].getDiscOnPoint();
                      pointC[i].setHaveDisc(false); 
                      break;
                  }
               }
      }
      TowerPoint endPoint=null;
      int i=0;     
      if(cEnd==towerName[0]){
               for(i=0;i<pointA.length;i++){
                  if(pointA[i].isHaveDisc()==true){ 
                     if(i>0){                                                                               endPoint=pointA[i-1];
                        break;
                     }
                     else if(i==0)
                        break;
                  }
               }
               if(i==pointA.length)
                 endPoint=pointA[pointA.length-1];
      }
      if(cEnd==towerName[1]){
               for(i=0;i<pointB.length;i++){
                  if(pointB[i].isHaveDisc()==true){                                                      if(i>0){                                                                               endPoint=pointB[i-1];
                        break;
                     }
                     else if(i==0)
                        break;
                  }
               }
               if(i==pointB.length)
                 endPoint=pointB[pointB.length-1];
      }
      if(cEnd==towerName[2]){
               for(i=0;i<pointC.length;i++){
                  if(pointC[i].isHaveDisc()==true){                                                      if(i>0){                                                                               endPoint=pointC[i-1];
                        break;
                     }
                     else if(i==0)
                        break;
                  }    
               }
               if(i==pointC.length)
                 endPoint=pointC[pointC.length-1];
      }
      if(endPoint!=null&&disc!=null){
             endPoint.putDisc(disc,con);
             endPoint.setHaveDisc(true);
      } 
   }
} 
