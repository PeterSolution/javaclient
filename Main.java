import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.Socket;

public class Main {
    private static String ips;
    public static void main(String[] args) {

        int port = 4211;

        String status="Connection ...";
        JFrame frame=new JFrame();

        JPanel fullscr=new JPanel();
        fullscr.setLayout(new BoxLayout(fullscr,BoxLayout.Y_AXIS));

        JPanel top=new JPanel();
        JLabel label1=new JLabel("IP: ");

        JTextArea ipbox=new JTextArea();
        ipbox.setPreferredSize(new Dimension(100,30));

        JLabel label2=new JLabel(status);


        top.add(label1);
        top.add(ipbox);
        top.add(label2);

        fullscr.add(top);

        JPanel mid=new JPanel();

        JTextArea area=new JTextArea();

        mid.add(area);

        fullscr.add(mid);

        JPanel bot=new JPanel();
        JButton button1=new JButton("Join to server");
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ips=ipbox.getText();
                try(Socket socket = new Socket(ips,port);
                    BufferedReader serresponse=new BufferedReader(new InputStreamReader(socket.getInputStream()));
                )
                {
                    String txttoread;
                    while ((txttoread=serresponse.readLine())!=null){
                        area.setText(txttoread);
                    }
                }
                catch (Exception ee) {

                }
            }
        });
        JButton button2=new JButton("Get book");
        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        fullscr.add(button1);

        fullscr.add(bot);

        frame.add(fullscr);
        frame.setVisible(true);
    }
}