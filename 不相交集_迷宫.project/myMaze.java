
import java.awt.event.*;
import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class myMaze extends JFrame{ 
    private static final long serialVersionUID = 4581809619616228704L;
    int m=50,n=50;     //�Թ��ߴ�
    int rSize = 10;    //�Թ�����ߴ磨�����Σ�
    int d = 10;        //�Թ������봰�ھ��루�����ϵľ���d*sSize��
    JPanel panel = new JPanel();
    JPanel pan = new JPanel();
    JButton button = new JButton("ˢ��");
    JButton butt = new JButton("��");
    Answer answer = new Answer();

    static int wHeight = 700;      //���ڳߴ�
    static int wWidth = 700;
    public myMaze(){
        init();
        panel.setLayout(null);
        add(panel,BorderLayout.CENTER);
    }

    public void init(){
        pan.setBackground(Color.white);
        Function listen = new Function();
        answer.setSize(rSize,d,m,n);
        listen.setMaze(this);
        button.addActionListener(listen);
    }

    public void paint(Graphics g){

            answer.setGra(g);
            g.setColor(Color.white);    //�����ɰ�ɫ
            g.fillRect(0,0,wWidth,wHeight);
            g.setColor(Color.black);
            for(int i=0;i<n;i++){      
               for(int j=0;j<m;j++){
                 g.drawRect(rSize*(i+d),rSize*(j+d),rSize,rSize);   //������           
               }
            }
            g.setColor(Color.white);           //�Թ��ĳ��ں���ڴ�
            g.drawLine(rSize*d,rSize*d,rSize*(d+1)-1,rSize*d);    //��ں�ǽ
            g.drawLine(rSize*d,rSize*d,rSize*d,rSize*(d+1)-1);    //�����ǽ
            g.drawLine((n-1+d)*rSize+1,(m+d)*rSize,(n+d)*rSize,(m+d)*rSize);  //���ں�ǽ
            g.drawLine((n+d)*rSize,(m-1+d)*rSize+1,(n+d)*rSize,(m+d)*rSize);  //������ǽ

            DisjSets dSets = new DisjSets(m*n);   //���ཻ��
            Random random = new Random();
           
            while(dSets.find(0)!=dSets.find(m*n-1)){       //����0��mn-1û����ͨ
                 int a = random.nextInt(m*n);         //�������һ������� a0<=a<=mn-1
                 List<Integer> neighbor = new ArrayList<Integer>();   //a�����ڷ�����list���
                 if(a-n>=0)              //�ֱ��ж�a���ϡ��¡����ҷ����Ƿ���ڣ������ڷ���neighbor
                     neighbor.add(a-n);
                 if(a+1<((int)(a/n)+1) * n)
                     neighbor.add(a+1);
                 if(a+n<m*n)
                     neighbor.add(a+n);
                 if(a-1>((int)(a/n))*n)
                     neighbor.add(a-1);
                 int index = random.nextInt(neighbor.size());    //b������a�����ڷ�������ǿ��������������Ƿ���ͨ
                 int b= neighbor.get(index);
                 if(dSets.find(a)==dSets.find(b))  //a��b��ͨ�Ļ������µķ���
                 continue;
                 else{                        //a��b����ͨ�Ļ�����������ͨ
                     int seta = dSets.find(a);            
                     int setb = dSets.find(b);
                     dSets.union(seta,setb);
                     int s = Math.min(a,b);   //��ǽ�������ҵ���С�����
                     int x1,y1,x2,y2;         //����ǽ������
                     if(Math.abs(a-b)==1){     //����Ų�һ���������ǵ�����ǽ
                        if(s<n)           //���
                           x1 = (s+1+d)*rSize;
                        else
                           x1 = (s%n+1+d)*rSize;
                        y1 = ((int)(s/n)+d)*rSize+1;
                        x2 = x1;   //�յ�
                        y2 = ((int)(s/n)+1+d)*rSize-1;                      
                     }
                     else{            //�����Ǻ�ǽ
                        if(s<n)         //���
                           x1 = (s+d)*rSize+1;
                        else
                           x1 = (s%n+d)*rSize+1;
                        y1 = ((int)(s/n)+1+d)*rSize;
                        if(s<n)        //�յ�
                           x2 = (s+1+d)*rSize-1;
                        else
                           x2 = (s%n+1+d)*rSize-1;
                        y2 = y1;
                     }
                     g.setColor(Color.white);     //��ǽ���ð��߰�ǽĨ��
                     g.drawLine(x1,y1,x2,y2);                    
                 }                       
            }
            answer.setArr(dSets.getElement());   
            butt.addActionListener(answer);
            pan.add(button);
            pan.add(butt);
            add(pan,BorderLayout.NORTH);        
    }

    public static void main(String []args){
        myMaze maze = new myMaze();      
        maze.setTitle("�Թ�");
        maze.setSize(wWidth,wHeight);
        maze.setVisible(true);
        maze.setLocationRelativeTo(null);
        maze.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }    
}

class Function implements ActionListener{
    myMaze newMaze;
    public void setMaze(myMaze n){
        newMaze = n;
    }
    public void actionPerformed(ActionEvent e){
        newMaze.repaint();
    }
}

class Answer implements ActionListener{
    Graphics gra;
    private int rSize,d,m,n;
    private int arr[];

    public void setGra(Graphics s){
       gra = s;
    }
    public void setSize(int a,int b,int size_m,int size_n){
       rSize = a;
       d = b;
       m = size_m;
       n = size_n;
    }
    public void setArr(int s[]){
       arr = new int[s.length];
       for(int i=0;i<s.length;i++){
          arr[i] = s[i];
       }
    }

    public void find(int num1,int num2){
          int a = arr[num1];
          int b = arr[num2];
       do{
          int x1,y1,x2,y2;

          if(a<n)
             x1 = (a+d)*rSize+1;
          else
             x1 = (a%n+d)*rSize+1;
          y1 = ((int)(a/n)+1+d)*rSize;
          if(a<n)
             x2 = (a+1+d)*rSize-1;
          else
             x2 = (a%n+1+d)*rSize-1;
          y2 = y1;
          gra.setColor(Color.red);
          gra.drawLine(x1,y1,x2,y2);

          if(b<n)
             x1 = (a+d)*rSize+1;
          else
             x1 = (b%n+d)*rSize+1;
          y1 = ((int)(a/n)+1+d)*rSize;
          if(b<n)
             x2 = (b+1+d)*rSize-1;
          else
             x2 = (b%n+1+d)*rSize-1;
          y2 = y1;
          gra.setColor(Color.red);
          gra.drawLine(x1,y1,x2,y2);  

          if(a>=0){
             a=arr[a];
          }     
          if(b>=0){
             b=arr[b];
          } 
       }while(a!=b);
    }
    public void actionPerformed(ActionEvent e){
       find(0,m*n-1);
    }   
}




