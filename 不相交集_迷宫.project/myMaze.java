
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
    int m=50,n=50;     //迷宫尺寸
    int rSize = 10;    //迷宫房间尺寸（正方形）
    int d = 10;        //迷宫起点距离窗口距离（离左、上的距离d*sSize）
    JPanel panel = new JPanel();
    JPanel pan = new JPanel();
    JButton button = new JButton("刷新");
    JButton butt = new JButton("答案");
    Answer answer = new Answer();

    static int wHeight = 700;      //窗口尺寸
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
            g.setColor(Color.white);    //背景成白色
            g.fillRect(0,0,wWidth,wHeight);
            g.setColor(Color.black);
            for(int i=0;i<n;i++){      
               for(int j=0;j<m;j++){
                 g.drawRect(rSize*(i+d),rSize*(j+d),rSize,rSize);   //画房间           
               }
            }
            g.setColor(Color.white);           //迷宫的出口和入口打开
            g.drawLine(rSize*d,rSize*d,rSize*(d+1)-1,rSize*d);    //入口横墙
            g.drawLine(rSize*d,rSize*d,rSize*d,rSize*(d+1)-1);    //入口竖墙
            g.drawLine((n-1+d)*rSize+1,(m+d)*rSize,(n+d)*rSize,(m+d)*rSize);  //出口横墙
            g.drawLine((n+d)*rSize,(m-1+d)*rSize+1,(n+d)*rSize,(m+d)*rSize);  //出口竖墙

            DisjSets dSets = new DisjSets(m*n);   //不相交集
            Random random = new Random();
           
            while(dSets.find(0)!=dSets.find(m*n-1)){       //房间0、mn-1没有连通
                 int a = random.nextInt(m*n);         //随机生成一个房间号 a0<=a<=mn-1
                 List<Integer> neighbor = new ArrayList<Integer>();   //a的相邻房间用list存放
                 if(a-n>=0)              //分别判断a的上、下、左、右房间是否存在，若存在放入neighbor
                     neighbor.add(a-n);
                 if(a+1<((int)(a/n)+1) * n)
                     neighbor.add(a+1);
                 if(a+n<m*n)
                     neighbor.add(a+n);
                 if(a-1>((int)(a/n))*n)
                     neighbor.add(a-1);
                 int index = random.nextInt(neighbor.size());    //b房间是a的相邻房间号我们考察这两个房间是否联通
                 int b= neighbor.get(index);
                 if(dSets.find(a)==dSets.find(b))  //a、b连通的话考察新的房间
                 continue;
                 else{                        //a、b不连通的话则让他们连通
                     int seta = dSets.find(a);            
                     int setb = dSets.find(b);
                     dSets.union(seta,setb);
                     int s = Math.min(a,b);   //拆墙，首先找到较小房间号
                     int x1,y1,x2,y2;         //计算墙的坐标
                     if(Math.abs(a-b)==1){     //房间号差一，隔开他们的是竖墙
                        if(s<n)           //起点
                           x1 = (s+1+d)*rSize;
                        else
                           x1 = (s%n+1+d)*rSize;
                        y1 = ((int)(s/n)+d)*rSize+1;
                        x2 = x1;   //终点
                        y2 = ((int)(s/n)+1+d)*rSize-1;                      
                     }
                     else{            //否则是横墙
                        if(s<n)         //起点
                           x1 = (s+d)*rSize+1;
                        else
                           x1 = (s%n+d)*rSize+1;
                        y1 = ((int)(s/n)+1+d)*rSize;
                        if(s<n)        //终点
                           x2 = (s+1+d)*rSize-1;
                        else
                           x2 = (s%n+1+d)*rSize-1;
                        y2 = y1;
                     }
                     g.setColor(Color.white);     //拆墙，用白线把墙抹掉
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
        maze.setTitle("迷宫");
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




