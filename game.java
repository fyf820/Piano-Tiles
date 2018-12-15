package pkgfinal;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class game extends JFrame implements ActionListener,ItemListener,KeyListener {
    //var for construct gridlayout
    private int ROW=5;
    private int COL1=2;
    private int COL2=3;
    private int COL3=4;
    //cardlayout and panels
    private JPanel panel=new JPanel();
    private JPanel panel1C=new JPanel(new GridLayout(ROW,COL1));
    private JPanel panel1=new JPanel();
    private JPanel panel2C=new JPanel(new GridLayout(ROW,COL2));
    private JPanel panel2=new JPanel();
    private JPanel panel3C=new JPanel(new GridLayout(ROW,COL3));
    private JPanel panel3=new JPanel();
    private JPanel panel4=new JPanel();
    private CardLayout c=new CardLayout();
    //menu
    private JMenuBar bar=new JMenuBar();
    private JMenu file=new JMenu("File");
    private JMenuItem home=new JMenuItem("Home");
    private JMenuItem exit=new JMenuItem("Exit");
    //labels
    private JLabel label1=new JLabel("Difficulty");
    private JLabel label21=new JLabel("Do not touch white tiles");
    private JLabel label22=new JLabel("Do not touch white tiles");
    private JLabel label23=new JLabel("Do not touch white tiles");
    private JLabel label3=new JLabel();
    private JButton begin=new JButton("Begin");
    //panel array
    private JPanel panels1[][]=new JPanel[ROW][COL1];
    private JPanel panels2[][]=new JPanel[ROW][COL2];
    private JPanel panels3[][]=new JPanel[ROW][COL3];
    private Color colors1[][]=new Color[ROW][COL1];
    private Color colors2[][]=new Color[ROW][COL2];
    private Color colors3[][]=new Color[ROW][COL3];
    //color
    private Color color1=Color.BLACK;
    private Color color2=Color.WHITE;
    //JComboBox for difficulty of game
    private String[] choice={"Selection","Easy","Normal","Hard"};
    private JComboBox combo=new JComboBox(choice);
    //JTextArea for how to play game
    private JTextArea text=new JTextArea("Easy: tap left and right to move\n"
            + "Normal: tap left, up and right to move\nHard: tap left, up, down"
            + " and right to mome");
    //count the black tiles
    private int counter=0;
    //the selection of difficulty
    private int hard=0;
    //mark black tiles in roll to help construct the game
    private int black;
    //choice of difficultys
    private boolean choose1;
    private boolean choose2;
    private boolean choose3;
    //check if the player touches the black tile
    private boolean isblack=false;
    
    public game(){
        super("Piano Tile");
        
        setSize(300,600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        setLayout(c);
        //add menu
        setJMenuBar(bar);
        bar.add(file);
        file.add(home);
        file.add(exit);
        //panel of home
        panel.setLayout(new FlowLayout());
        panel.add(label1);
        panel.add(combo);
        panel.add(begin);
        panel.add(text);
        add(panel,"panel");
        //panel of easy
        panel1.setLayout(new BorderLayout());
        panel1.add(label21,BorderLayout.NORTH);
        panel1.add(panel1C,BorderLayout.CENTER);
        add(panel1,"panel1");
        //panel of normal
        panel2.setLayout(new BorderLayout());
        panel2.add(label22,BorderLayout.NORTH);
        panel2.add(panel2C,BorderLayout.CENTER);
        add(panel2,"panel2");
        //panel of hard
        panel3.setLayout(new BorderLayout());
        panel3.add(label23,BorderLayout.NORTH);
        panel3.add(panel3C,BorderLayout.CENTER);
        add(panel3,"panel3");
        //panel of result
        panel4.add(label3);
        add(panel4,"panel4");
        //construct easy
        for(int i=0;i<ROW;i++){
            //random one in a row will be black
            black=(int)(Math.random()*2);
            for(int j=0;j<COL1;j++){
                panels1[i][j]=new JPanel();
                panel1C.add(panels1[i][j]);
                if(j==black){
                    panels1[i][j].setBackground(color1);
                    colors1[i][j]=color1;
                }
                else{
                    panels1[i][j].setBackground(color2);
                    colors1[i][j]=color2;
                }
            }
        }   
        //construct normal
        for(int i=0;i<ROW;i++){
            black=(int)(Math.random()*3);
            for(int j=0;j<COL2;j++){
                panels2[i][j]=new JPanel();
                panel2C.add(panels2[i][j]);
                if(j==black){
                    panels2[i][j].setBackground(color1);
                    colors2[i][j]=color1;
                }
                else{
                    panels2[i][j].setBackground(color2);
                    colors2[i][j]=color2;
                }
            }
        }
        //construct hard
        for(int i=0;i<ROW;i++){
            black=(int)(Math.random()*4);
            for(int j=0;j<COL3;j++){
                panels3[i][j]=new JPanel();
                panel3C.add(panels3[i][j]);
                if(j==black){
                    panels3[i][j].setBackground(color1);
                    colors3[i][j]=color1;
                }
                else{
                    panels3[i][j].setBackground(color2);
                    colors3[i][j]=color2;
                }
            }
        }
        //addlistners
        home.addActionListener(this);
        exit.addActionListener(this);
        begin.addActionListener(this);
        combo.addItemListener(this);
        addKeyListener(this);
    }
    
    @Override
    public void actionPerformed(ActionEvent ae) {
        Object Source=ae.getSource();
        Container con=getContentPane();
        //come back to home from menu and clear counter
        if(Source==home){
            c.show(con,"panel");
            counter=0;
        }
        //exit from home
        else if(Source==exit){
            System.exit(0);
        }
        //jump to game panels from home
        else if(Source==begin){
            if(hard==1){
                c.show(con,"panel1");
                choose1=true;
                choose2=false;
                choose3=false;
            }
            else if(hard==2){
                c.show(con,"panel2");
                choose1=false;
                choose2=true;
                choose3=false;
            }
            else if(hard==3){
                c.show(con,"panel3");
                choose1=false;
                choose2=false;
                choose3=true;
            }
        }
    }

    @Override
    public void itemStateChanged(ItemEvent ie) {
        Object source=ie.getSource();
        int select=ie.getStateChange();
        //get the difficulty from JComboBox
        if(source==combo){
            if(select==ItemEvent.SELECTED)
                hard=combo.getSelectedIndex();
        }
    }

    @Override
    public void keyTyped(KeyEvent ke) {
        
    }

    @Override
    public void keyPressed(KeyEvent ke) {
        int keyCode=ke.getKeyCode();
        //easy mode to check if player touches black tile 
        if(choose1){
            //if taping left and the color of left is black, then game continues
            if(keyCode==KeyEvent.VK_LEFT){
                if(colors1[4][0]==color1){
                    isblack=true;
                    counter++;
                }
                //the tile is not black, game over 
                else{
                    c.show(getContentPane(),"panel4");
                    label3.setText("Your record is:"+counter);
                    label3.setFont(new Font("Arial",Font.BOLD,20));
                }
            }
            //same logic
            else if(keyCode==KeyEvent.VK_RIGHT){
                if(colors1[4][1]==color1){
                    isblack=true;
                    counter++;
                }
                else{
                    c.show(getContentPane(),"panel4");
                    label3.setText("Your record is:"+counter);
                    label3.setFont(new Font("Arial",Font.BOLD,20));
                }
            }
        }
        //normal, same logic as esay
        else if(choose2){
            if(keyCode==KeyEvent.VK_LEFT){
                if(colors2[4][0]==color1){
                    isblack=true;
                    counter++;
                }
                else{
                    c.show(getContentPane(),"panel4");
                    label3.setText("Your record is:"+counter);
                    label3.setFont(new Font("Arial",Font.BOLD,20));
                }
            }
            else if(keyCode==KeyEvent.VK_UP){
                if(colors2[4][1]==color1){
                    isblack=true;
                    counter++;
                }
                else{
                    c.show(getContentPane(),"panel4");
                    label3.setText("Your record is:"+counter);
                    label3.setFont(new Font("Arial",Font.BOLD,20));
                }
            }
            else if(keyCode==KeyEvent.VK_RIGHT){
                if(colors2[4][2]==color1){
                    isblack=true;
                    counter++;
                }
                else{
                    c.show(getContentPane(),"panel4");
                    label3.setText("Your record is:"+counter);
                    label3.setFont(new Font("Arial",Font.BOLD,20));
                }
            }
        }
        //hard, same logic as easy
        else if(choose3){
            if(keyCode==KeyEvent.VK_LEFT){
                if(colors3[4][0]==color1){
                    isblack=true;
                    counter++;
                }
                else{
                    c.show(getContentPane(),"panel4");
                    label3.setText("Your record is:"+counter);
                    label3.setFont(new Font("Arial",Font.BOLD,20));
                }
            }
            else if(keyCode==KeyEvent.VK_UP){
                if(colors3[4][1]==color1){
                    isblack=true;
                    counter++;
                }
                else{
                    c.show(getContentPane(),"panel4");
                    label3.setText("Your record is:"+counter);
                    label3.setFont(new Font("Arial",Font.BOLD,20));
                }
            }
            else if(keyCode==KeyEvent.VK_DOWN){
                if(colors3[4][2]==color1){
                    isblack=true;
                    counter++;
                }
                else{
                    c.show(getContentPane(),"panel4");
                    label3.setText("Your record is:"+counter);
                    label3.setFont(new Font("Arial",Font.BOLD,20));
                }
            }
            else if(keyCode==KeyEvent.VK_RIGHT){
                if(colors3[4][3]==color1){
                    isblack=true;
                    counter++;
                }
                else{
                    c.show(getContentPane(),"panel4");
                    label3.setText("Your record is:"+counter);
                    label3.setFont(new Font("Arial",Font.BOLD,20));
                }
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent ke) {
        //easy
        if(choose1){
            //check if the player moves right
            if(isblack){
                //all the tiles moving down one row, and create a new row on the
                //top, delete bottom row
                for(int i=ROW-1;i>=0;i--){
                    black=(int)(Math.random()*2);
                    for(int j=0;j<COL1;j++){
                        if(i==0){
                            if(j==black){
                                panels1[i][j].setBackground(color1);
                                colors1[i][j]=color1;
                            }
                            else{
                                panels1[i][j].setBackground(color2);
                                colors1[i][j]=color2;
                            }
                        }
                        else{
                            panels1[i][j].setBackground(colors1[i-1][j]);
                            colors1[i][j]=colors1[i-1][j];
                        }
                    }
                }
                isblack=false;
            }
        }
        //normal, same logic as easy
        else if(choose2){
            if(isblack){
                for(int i=ROW-1;i>=0;i--){
                    black=(int)(Math.random()*3);
                    for(int j=0;j<COL2;j++){
                        if(i==0){
                            if(j==black){
                                panels2[i][j].setBackground(color1);
                                colors2[i][j]=color1;
                            }
                            else{
                                panels2[i][j].setBackground(color2);
                                colors2[i][j]=color2;
                            }
                        }
                        else{
                            panels2[i][j].setBackground(colors2[i-1][j]);
                            colors2[i][j]=colors2[i-1][j];
                        }
                    }
                }
                isblack=false;
            }
        }
        //hard, same logic as easy
        else if(choose3){
            if(isblack){
                for(int i=ROW-1;i>=0;i--){
                    black=(int)(Math.random()*4);
                    for(int j=0;j<COL3;j++){
                        if(i==0){
                            if(j==black){
                                panels3[i][j].setBackground(color1);
                                colors3[i][j]=color1;
                            }
                            else{
                                panels3[i][j].setBackground(color2);
                                colors3[i][j]=color2;
                            }
                        }
                        else{
                            panels3[i][j].setBackground(colors3[i-1][j]);
                            colors3[i][j]=colors3[i-1][j];
                        }
                    }
                }
                isblack=false;
            }
        }
    }
}
