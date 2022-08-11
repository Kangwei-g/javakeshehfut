package zuoye;
import javax.swing.*;
import java.awt.*;
import java.io.FileNotFoundException;



public class Tower extends JPanel{
    int amountOfDisc=3;
    Disc [] disc;
    int maxDiscWidth,minDiscWidth,discHeight;
    char [] towerName;
    TowerPoint [] pointA,pointB,pointC;
    HandleMouse handleMouse;
    AutoMoveDisc autoMoveDisc;




    Color backGround =new Color(200,226,226);
    Color bbackGround =new Color( 0,255,255);
    Tower(char [] towerName) throws FileNotFoundException {
        handleMouse=new HandleMouse(this);        
        this.towerName=towerName;
        setLayout( null);
        setBackground( backGround);
    }
    public boolean youWin() {
        if(pointB[0].isHaveDisc()||pointC[0].isHaveDisc())
            return true;
        else return false;
    }

public void setbb(Color a){
        bbackGround=a;
}
    public void setBackGround(Color a){
        backGround=a;
    }
    public void setAmountOfDisc(int number){
        if(number<=1)
           amountOfDisc=1;
        else
           amountOfDisc=number;
    }
    public void setMaxDiscWidth(int m){
        maxDiscWidth=m;
    }    
    public void setMinDiscWidth(int m){
        minDiscWidth=m;
    }
    public void setDiscHeight(int h){
        discHeight=h;
    }
    public AutoMoveDisc getAutoMoveDisc(){
       return autoMoveDisc;
    }
    public void putDiscOnTower(){

        setBackground( backGround);
        removeDisk();
        int n=(maxDiscWidth-minDiscWidth)/amountOfDisc;
        disc=new Disc[amountOfDisc];


        for(int i=0;i<disc.length;i++){
           disc[i]=new Disc();
           disc[i].setNumber(i);
           int diskwidth=minDiscWidth+i*n;
           disc[i].setSize(diskwidth,discHeight);
           disc[i].addMouseListener(handleMouse);
           disc[i].addMouseMotionListener(handleMouse);

        }
        for (int i = 0; i < disc.length; i++) {
            disc[i].setBackground(bbackGround);

        }
        pointA=new TowerPoint[amountOfDisc];
        pointB=new TowerPoint[amountOfDisc]; 
        pointC=new TowerPoint[amountOfDisc];
        int vertialDistance=discHeight;
        for(int i=0;i<pointA.length;i++){
            pointA[i]=new TowerPoint(160,100+vertialDistance);
            vertialDistance=vertialDistance+discHeight;
        }
        vertialDistance=discHeight;
        for(int i=0;i<pointB.length;i++){
            pointB[i]=new TowerPoint(2*160,100+vertialDistance);
            vertialDistance=vertialDistance+discHeight;
        }
        vertialDistance=discHeight;
        for(int i=0;i<pointC.length;i++){
            pointC[i]=new TowerPoint(3*160,100+vertialDistance);
            vertialDistance=vertialDistance+discHeight;
        }
        for(int i=0;i<pointA.length;i++){
            pointA[i].putDisc(disc[i],this);
        }
        handleMouse.setPointA(pointA); 
        handleMouse.setPointB(pointB);
        handleMouse.setPointC(pointC);
        autoMoveDisc=new AutoMoveDisc(this);
        autoMoveDisc.setTowerName(towerName);
        autoMoveDisc.setAmountOfDisc(amountOfDisc);
        autoMoveDisc.setPointA(pointA);
        autoMoveDisc.setPointB(pointB);
        autoMoveDisc.setPointC(pointC); 
        validate();
        repaint(); 
     }
     public void removeDisk(){
        if(pointA!=null){
            for(int i=0;i<pointA.length;i++){
               pointA[i].removeDisc(pointA[i].getDiscOnPoint(),this);
               pointB[i].removeDisc(pointB[i].getDiscOnPoint(),this);
               pointC[i].removeDisc(pointC[i].getDiscOnPoint(),this);
            }
        }
     }
     public void paintComponent(Graphics g){                     
        super.paintComponent(g);
        int x1,y1,x2,y2;
        x1=pointA[0].getX();
        y1=pointA[0].getY()-discHeight/2;
        x2=pointA[amountOfDisc-1].getX();
        y2=pointA[amountOfDisc-1].getY()+discHeight/2;  
        g.drawLine(x1,y1,x2,y2);     
        x1=pointB[0].getX();
        y1=pointB[0].getY()-discHeight/2;
        x2=pointB[amountOfDisc-1].getX();
        y2=pointB[amountOfDisc-1].getY()+discHeight/2;  
        g.drawLine(x1,y1,x2,y2); 
        x1=pointC[0].getX();
        y1=pointC[0].getY()-discHeight/2;
        x2=pointC[amountOfDisc-1].getX();
        y2=pointC[amountOfDisc-1].getY()+discHeight/2;  
        g.drawLine(x1,y1,x2,y2);    
        g.setColor(Color.blue);
        x1=pointA[amountOfDisc-1].getX()-maxDiscWidth/2;
        y1=pointA[amountOfDisc-1].getY()+discHeight/2;
        x2=pointC[amountOfDisc-1].getX()+maxDiscWidth/2;
        y2=pointC[amountOfDisc-1].getY()+discHeight/2; 
        int length=x2-x1,height=6; 
        g.fillRect(x1,y1,length,height);
        int size=5;
        for(int i=0;i<pointA.length;i++){                                   
           g.fillOval(pointA[i].getX()-size/2,pointA[i].getY()-size/2,size,size);
           g.fillOval(pointB[i].getX()-size/2,pointB[i].getY()-size/2,size,size);
           g.fillOval(pointC[i].getX()-size/2,pointC[i].getY()-size/2,size,size);
        }
        g.drawString(towerName[0]+"��",
                  pointA[amountOfDisc-1].getX(),pointA[amountOfDisc-1].getY()+50);
        g.drawString(towerName[1]+"��",
                  pointB[amountOfDisc-1].getX(),pointB[amountOfDisc-1].getY()+50);
        g.drawString(towerName[2]+"��",
                  pointC[amountOfDisc-1].getX(),pointC[amountOfDisc-1].getY()+50);
    }

}
