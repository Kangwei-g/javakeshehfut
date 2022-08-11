package zuoye;

import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class HannoiWindow extends JFrame implements ActionListener, Runnable {
    Tower tower;

    int amountOfDisc = 3, youXiMiaoShu,zanting=1,zuihou=0;
    char[] towerName = {'A', 'B', 'C'};
    JMenuBar bar;
    JTextField wenBen;
    JMenu panZiShuMu, zongSheZhi, panZiYanSe, beiJingYanSe, yinYveKaiGuan,
            panZiDaXiao, zuiDa, zuiXiao;
    JLabel zhuyi;

    JMenuItem three, four, five,
            six, seven, eight, red1, blue1, yellow1, green1,
            red, white, yellow, green, yinYve2, yinYve1, D1, D2, D3, X1, X2, X3;
    JButton renew, kaiShiJiShi, jieShuJiShi;
    JButton autoButton;
    Thread ff, gg;
    boolean Cannoti=false;

    HannoiWindow() throws FileNotFoundException {






        ff = new Thread(this);
        gg = new Thread(this);




        tower = new Tower(towerName);
        tower.setAmountOfDisc(amountOfDisc);
        tower.setMaxDiscWidth(120);
        tower.setMinDiscWidth(50);
        tower.setDiscHeight(16);
        tower.putDiscOnTower();
        add(tower, BorderLayout.CENTER);


        kaiShiJiShi = new JButton("���¼�ʱ");
        jieShuJiShi = new JButton("��ͣ����");

        bar = new JMenuBar();
        panZiShuMu = new JMenu("������Ŀ");
        zongSheZhi = new JMenu("����");
        panZiYanSe = new JMenu("������ɫ");
        beiJingYanSe = new JMenu("������ɫ");
        three = new JMenuItem("3");
        four = new JMenuItem("4");
        five = new JMenuItem("5");
        six = new JMenuItem("6");
        seven = new JMenuItem("7");
        eight = new JMenuItem("8");
        red = new JMenuItem("��ɫ");
        white = new JMenuItem("��ɫ");
        green = new JMenuItem("��ɫ");
        yellow = new JMenuItem("��ɫ");
        red1 = new JMenuItem("��ɫ");
        blue1 = new JMenuItem("��ɫ");
        green1 = new JMenuItem("��ɫ");
        yellow1 = new JMenuItem("��ɫ");
        D1 = new JMenuItem("һ����");
        D2 = new JMenuItem("������");
        D3 = new JMenuItem("������");
        X1 = new JMenuItem("һ��С");
        X2 = new JMenuItem("����С");
        X3 = new JMenuItem("����С");

        yinYve2 = new JMenuItem("�ر�");
        yinYve1 = new JMenuItem("����");
        yinYveKaiGuan = new JMenu("���ֿ���");
        yinYveKaiGuan.add(yinYve1);
        yinYveKaiGuan.add(yinYve2);

        panZiDaXiao = new JMenu("���Ӵ�С");
        zuiDa = new JMenu("�������");
        zuiXiao = new JMenu("������С");
        panZiDaXiao.add(zuiDa);
        panZiDaXiao.add(zuiXiao);
        zuiDa.add(D1);
        zuiDa.add(D2);
        zuiDa.add(D3);
        zuiXiao.add(X1);
        zuiXiao.add(X2);
        zuiXiao.add(X3);

        panZiShuMu.add(five);
        panZiShuMu.add(three);
        panZiShuMu.add(four);
        panZiShuMu.add(five);
        panZiShuMu.add(six);
        panZiShuMu.add(seven);
        panZiShuMu.add(eight);
        panZiYanSe.add(blue1);
        panZiYanSe.add(red1);
        panZiYanSe.add(yellow1);
        panZiYanSe.add(green1);
        beiJingYanSe.add(white);
        beiJingYanSe.add(red);
        beiJingYanSe.add(yellow);
        beiJingYanSe.add(green);

        bar.add(zongSheZhi);
        zhuyi = new JLabel("(������Ϸ��ʼǰ���ã����ֳ���)");
        bar.add(zhuyi);
        zongSheZhi.add(panZiShuMu);
        zongSheZhi.add(panZiYanSe);
        zongSheZhi.add(beiJingYanSe);
        zongSheZhi.add(yinYveKaiGuan);
        zongSheZhi.add(panZiDaXiao);


        setJMenuBar(bar);


        kaiShiJiShi.addActionListener(this);
        jieShuJiShi.addActionListener(this);
        three.addActionListener(this);
        four.addActionListener(this);
        five.addActionListener(this);
        six.addActionListener(this);
        seven.addActionListener(this);
        eight.addActionListener(this);

        red1.addActionListener(this);
        blue1.addActionListener(this);
        yellow1.addActionListener(this);
        green1.addActionListener(this);
        red.addActionListener(this);
        white.addActionListener(this);
        yellow.addActionListener(this);
        green.addActionListener(this);
        yinYve1.addActionListener(this);
        yinYve2.addActionListener(this);
        D1.addActionListener(this);
        D2.addActionListener(this);
        D3.addActionListener(this);
        X1.addActionListener(this);
        X2.addActionListener(this);
        X3.addActionListener(this);

        renew = new JButton("��ʼ��Ϸ");
        renew.addActionListener(this);

        autoButton = new JButton("�Զ���ʾ");
        autoButton.addActionListener(this);

        JPanel north = new JPanel();                //���´�����һ�²���
        JPanel south = new JPanel();
        JPanel cen = new JPanel();
        cen.setLayout(new BorderLayout());
        north.add(renew);
        north.add(autoButton);
        String mess = "��ȫ�����Ӵ�" + towerName[0] + "�����˵�" + towerName[1] +
                "����" + towerName[2] + "��";
        JLabel hintMess = new JLabel(mess, JLabel.CENTER);
        north.add(hintMess);
        JLabel jj = new JLabel("��Ϸʱ��:");
        jj.setForeground(Color.red);
        south.add(jj);
        wenBen = new JTextField(8);
        south.add(wenBen);
        south.add(kaiShiJiShi);
        south.add(jieShuJiShi);
        cen.add(north, BorderLayout.NORTH);
        cen.add(south, BorderLayout.SOUTH);
        add(cen, BorderLayout.NORTH);





        setResizable(false);
        setVisible(true);
        setBounds(60, 60, 660, 410);
        validate();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        ff.start();
        gg.start();

    }

    public void run() {                    //�������ֺͼ�ʱ�����߳�
        if (Thread.currentThread() == ff) {
            byte[] aubuffer = new byte[1024 * 128];
            do {
                AudioInputStream audioInputStream = null;
                SourceDataLine auline = null;
                try {
                    audioInputStream = AudioSystem.getAudioInputStream(
                            new File("D:\\Ideajava\\Swwing\\src\\zuoye\\bgm.wav "));
                    AudioFormat format = audioInputStream.getFormat();
                    DataLine.Info info = new DataLine.Info(SourceDataLine.class, format);
                    auline = (SourceDataLine) AudioSystem.getLine(info);
                    auline.open(format);
                    auline.start();
                    int byteCount = 0;
                    while (byteCount != -1) {
                        byteCount = audioInputStream.read(aubuffer, 0, aubuffer.length);
                        if (byteCount >= 0) {
                            auline.write(aubuffer, 0, byteCount);
                        }
                    }

                } catch (UnsupportedAudioFileException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (LineUnavailableException e) {
                    e.printStackTrace();
                } finally {
                    auline.drain();
                    auline.close();
                }
            } while (true);


        }
        else if (Thread.currentThread() == gg) {
            this.youXiMiaoShu = 0;
            while (true) {

                try {
                    gg.sleep(1000);
                    this.youXiMiaoShu = this.youXiMiaoShu + 1;
                    wenBen.setText(youXiMiaoShu + "s");
                    if(tower.youWin()){
                        gg.stop();
                    }
                   /* if(tower.youWin()){  //����Ļᵯ����Ϸ��ɽ�������ײ���
                        zuihou+=1;
                        JFrame dia=new JFrame("����");
                        dia.setLayout(new BorderLayout());
                        dia.setBounds(40,40,200,100);
                        j =new JLabel("����ʱ"+youXiMiaoShu+"s");
                        dia.add(j,BorderLayout.NORTH );
                        dia.add(a,BorderLayout.SOUTH );
                        dia.setVisible(false);
                        a.addActionListener(this);
                         if (zuihou==1){
                             dia.setVisible(true);
                         }


                    }*/
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }
    }


    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == three) {            //���Ӹ������ƴ��루����
            amountOfDisc = 3;
            tower.setAmountOfDisc(amountOfDisc);
            tower.putDiscOnTower();
        } else if (e.getSource() == four) {
            amountOfDisc = 4;
            tower.setAmountOfDisc(amountOfDisc);
            tower.putDiscOnTower();
        } else if (e.getSource() == five) {
            amountOfDisc = 5;
            tower.setAmountOfDisc(amountOfDisc);
            tower.putDiscOnTower();
        } else if (e.getSource() == six) {
            amountOfDisc = 6;
            tower.setAmountOfDisc(amountOfDisc);
            tower.putDiscOnTower();
        } else if (e.getSource() == seven) {
            amountOfDisc = 7;
            tower.setAmountOfDisc(amountOfDisc);
            tower.putDiscOnTower();
        } else if (e.getSource() == eight) {
            amountOfDisc = 8;
            tower.setAmountOfDisc(amountOfDisc);
            tower.putDiscOnTower();
        } else if (e.getSource() == green1) {         //������ɫ�����ڱ�����ɫ�����ͻ�������
            tower.setbb(Color.green);
            tower.putDiscOnTower();
        } else if (e.getSource() == red1) {
            tower.setbb(Color.red);
            tower.putDiscOnTower();
        } else if (e.getSource() == yellow1) {
            tower.setbb(Color.yellow);
            tower.putDiscOnTower();
        } else if (e.getSource() == blue1) {
            tower.setbb(Color.black);
            tower.putDiscOnTower();
        } else if (e.getSource() == red) {           //������ɫ��ɫ�����ڱ�����ɫ�����ͻ�������
            tower.setBackGround(Color.red);
            tower.putDiscOnTower();
        } else if (e.getSource() == green) {
            tower.setBackGround(Color.green);
            tower.putDiscOnTower();
        } else if (e.getSource() == white) {
            tower.setBackGround(Color.white);
            tower.putDiscOnTower();
        } else if (e.getSource() == yellow) {
            tower.setBackGround(Color.yellow);
            tower.putDiscOnTower();
        } else if (e.getSource() == yinYve1) {        //���ֿ��ش���
            if (!ff.isAlive()) {
                this.ff = new Thread(this);
                ff.start();
            }
        } else if (e.getSource() == yinYve2) {
            ff.stop();
        } else if (e.getSource() == D1) {
            tower.setMaxDiscWidth(100);
            setBounds(60, 60, 660, 410);
            tower.putDiscOnTower();

        } else if (e.getSource() == D2) {
            tower.setMaxDiscWidth(120);
            setBounds(60, 60, 660, 410);
            tower.putDiscOnTower();

        } else if (e.getSource() == D3) {
            tower.setMaxDiscWidth(140);
            setBounds(60, 60, 660, 410);
            tower.putDiscOnTower();

        } else if (e.getSource() == X1) {
            tower.setMinDiscWidth(30);
            setBounds(60, 60, 660, 410);
            tower.putDiscOnTower();

        } else if (e.getSource() == X2) {
            tower.setMinDiscWidth(50);
            setBounds(60, 60, 660, 410);
            tower.putDiscOnTower();

        } else if (e.getSource() == X3) {
            tower.setMinDiscWidth(70);
            setBounds(60, 60, 660, 410);
            tower.putDiscOnTower();




        } else if (e.getSource() == kaiShiJiShi) {       //��ʱ����
            if (gg.isAlive()) {
                this.gg.stop();
            }

            gg = new Thread(this);
            gg.start();

        } else if (e.getSource() == jieShuJiShi) {
            zanting+=1;
            if (zanting%2==0){
                this.gg.suspend();
            }else {
                this.gg.resume();
            }
        } else if (e.getSource() == renew ) {
            this.youXiMiaoShu=0;
            this.zuihou=0;
            if (gg.isAlive()) {
                this.gg.stop();
            }
            gg = new Thread(this);
            gg.start();

            tower.setAmountOfDisc(amountOfDisc);
            tower.putDiscOnTower();}
    /*    else if(e.getSource()==renew){
            tower.setAmountOfDisc(amountOfDisc);
            tower.putDiscOnTower();
            if(Cannoti=true) {
                Thread_ShowWin.createAndStart("��ʱ��", jf, tower, this);
                Cannoti=false;
            }
        }*/
         else if (e.getSource() == autoButton) {
            tower.setAmountOfDisc(amountOfDisc);
            tower.putDiscOnTower();
            int x = this.getBounds().x + this.getBounds().width;
            int y = this.getBounds().y;
            tower.getAutoMoveDisc().setLocation(x, y);
            tower.getAutoMoveDisc().setSize(280, this.getBounds().height);
            tower.getAutoMoveDisc().setVisible(true);
        }
        validate();
    }

    public static void main(String args[]) throws FileNotFoundException {
        new HannoiWindow();
    }
}
