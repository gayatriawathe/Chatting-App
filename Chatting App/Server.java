import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;
import java.text.*;
import java.net.*;

import javax.swing.*;
import javax.swing.border.*;

public class Server implements ActionListener{

    JTextField text;
    JPanel a1;
    static Box vertical=Box.createVerticalBox();
    //Either To (Extend) any class or To create the (Object) of that class 
    static JFrame f=new JFrame();
    static DataOutputStream dout;
    Server()
    {
        f.setLayout(null);
        JPanel p1=new JPanel();
        p1.setBackground(new Color(7,94,84));
        p1.setBounds(0,0,450,70);
        p1.setLayout(null);
        f.add(p1);

        //Image set on file directory....image put on the directory
        ImageIcon i1=new ImageIcon(ClassLoader.getSystemResource("icons/3.png"));
        //to decrease the size of img(Scale is used)
        Image i2=i1.getImage().getScaledInstance(25, 25, Image.SCALE_DEFAULT);
        //image not directly call...that's why to convert image into image icons
        ImageIcon i3=new ImageIcon(i2);
        //Didn't set the image directly that's why,to creat the obj of Name:Jlabel
        JLabel back=new JLabel(i3);
        //then jlabel obj set on frame
        back.setBounds(5,20,25,25);
        //img add on the panel(p1)
        p1.add(back);

        back.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent ae)
            {
                ////The action should be perform by clicking on Arrow Img(By Mouse click on to the arrow,it must be close)
               //setVisible(false);  
                System.exit(0);
            }
        });


        ImageIcon i4=new ImageIcon(ClassLoader.getSystemResource("icons/1.png"));
        Image i5=i4.getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT);
        ImageIcon i6=new ImageIcon(i5);
        JLabel profile=new JLabel(i6);
        profile.setBounds(40,10,50,50);
        p1.add(profile);

        ImageIcon i7=new ImageIcon(ClassLoader.getSystemResource("icons/video.png"));
        Image i8=i7.getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT);
        ImageIcon i9=new ImageIcon(i8);
        JLabel video=new JLabel(i9);
        video.setBounds(300,20,30,30);
        p1.add(video);

        ImageIcon i10=new ImageIcon(ClassLoader.getSystemResource("icons/phone.png"));
        Image i11=i10.getImage().getScaledInstance(35, 30, Image.SCALE_DEFAULT);
        ImageIcon i12=new ImageIcon(i11);
        JLabel phone=new JLabel(i12);
        phone.setBounds(360,20,35,30);
        p1.add(phone);
        
        ImageIcon i13=new ImageIcon(ClassLoader.getSystemResource("icons/3icon.png"));
        Image i14=i13.getImage().getScaledInstance(10, 25, Image.SCALE_DEFAULT);
        ImageIcon i15=new ImageIcon(i14);
        JLabel morevert=new JLabel(i15);
        morevert.setBounds(420,20,10,25);
        p1.add(morevert);
        
        //With the help of JLabel,We have to write anything on to the Frame 
        JLabel name=new JLabel("Gaitondo");
        name.setBounds(110,15,100,18);
        //To change text color
        name.setForeground(Color.WHITE);
        //To change the size of text
        name.setFont(new Font("SAN_SERIF", Font.BOLD, 18));
        p1.add(name);

        JLabel status=new JLabel("Active Now");
        status.setBounds(110,35,100,18);
        status.setForeground(Color.CYAN);
        status.setFont(new Font("SAN_SERIF", Font.BOLD, 14));
        p1.add(status);

        //Different Panel                  
        a1=new JPanel();    //To append the label into them that's why,It declared it globally 
                            //To access it through another function that's why
        a1.setBounds(5,75,440,570);
        f.add(a1);

        //We have to make anyone text field by the name as JTextField,where user can write their any text
        text=new JTextField();
        text.setBounds(5,655,310,40);
        text.setFont(new Font("SAN_SERIF", Font.PLAIN, 16));
        f.add(text);

        //For Button
        JButton send=new JButton("Send");
        send.setBounds(320,655,123,40);
        send.setBackground(new Color(7,94,84));
        send.setForeground(Color.WHITE);
        send.addActionListener(this);
        send.setFont(new Font("SAN_SERIF", Font.PLAIN, 16));
        f.add(send);


        f.setSize(450,700);
        f.setLocation(200,50);
        f.setUndecorated(true);
        f.getContentPane().setBackground(Color.WHITE);
        f.setVisible(true);
    }
    public void actionPerformed(ActionEvent ae)
    {
        try{
        //Actioned perform by Button click that write inside it
        String out=text.getText();
        JPanel p2=formatLabel(out);
        //Anything would be type on the text field that will be display on to the console after click the send button 
        //System.out.println(out);
        //To set on panel..need broder layout
        a1.setLayout(new BorderLayout());

        //to make panel
        JPanel right=new JPanel(new BorderLayout());
        //to move on right
        right.add(p2, BorderLayout.LINE_END);  //display on right side
        vertical.add(right);      //Multiple msg will be display one upon the other..[vertically align]
        vertical.add(Box.createVerticalStrut(15));//How many Space would be taken between the 2 vertical(eg.15)

        //Every msg have been add on the a1
        a1.add(vertical, BorderLayout.PAGE_START);
        //With the help of writeutf we sent the msg 
        dout.writeUTF(out);
        //Text has been empty after the text has been display on the screen  
        text.setText("");

        //repaint would be done to see the msg for that JFrame obj has created
        f.repaint();
        f.invalidate();;
        f.validate();
        }catch(Exception e)//error or exception has been shown because we use the Server 
        {
            e.printStackTrace();
        }
    }
    //Msg will be display on the proper box
    public static JPanel formatLabel(String out)
    {
        JPanel panel=new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        JLabel output=new JLabel("<html><p style=\"width:150px\">" + out + "</p></html>");
        output.setFont(new Font("Tahona", Font.PLAIN, 16));
        output.setBackground(new Color(37,211,102));
        output.setOpaque(true);
        output.setBorder(new EmptyBorder(15,15,15,50));
        panel.add(output);
        //For Time...The time at which msg will be send
        Calendar cal=Calendar.getInstance();
        SimpleDateFormat sdf=new SimpleDateFormat("HH:mm");
        JLabel time=new JLabel();
        time.setText(sdf.format(cal.getTime()));
        panel.add(time);
        return panel;
 
    }
    public static void main(String[] args) {
        new Server();
        //At the time of making the Server,to catch the coming exception or error use try-catch 
        try{
            ServerSocket skt=new ServerSocket(6001);//port no.has been passed eg.6001
            while(true){  //To accept every msg infinitely 
                Socket s=skt.accept();
                //for Msg receive
                DataInputStream din=new DataInputStream(s.getInputStream());
                //FOr msg sent
                dout=new DataOutputStream(s.getOutputStream());
                while(true){
                    //Protocole use to sent and receive msg infinitely
                    //After receving the msg then it will be use to read the msg...for this we use readutf method,this method return string
                    String msg=din.readUTF();
                    //To display the Replying msg of the Client on to the Frame 
                    JPanel panel=formatLabel(msg);

                    //If we have to add on the left of the Panel
                    JPanel left=new JPanel(new BorderLayout());
                    left.add(panel, BorderLayout.LINE_START);
                    vertical.add(left);
                    //for refreshing the frame
                    f.validate();

                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}