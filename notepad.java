package Notepad;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.Reader;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

public class notepad extends JFrame implements ActionListener{
    JTextArea area=null;
    notepad(){
        setExtendedState(JFrame.MAXIMIZED_BOTH);

        //Setting icon for notepad application
        ImageIcon i1 = new ImageIcon("src/Notepad/logo1.jpg");
        Image i2 = i1.getImage();
        setIconImage(i2);
        setTitle("Vijay's Notepad");
        menubar();
        setVisible(true);
    }
    public void menubar(){
        JMenuBar menubar = new JMenuBar();

        JMenu menu1 = new JMenu("File");
        JMenuItem new_file = new JMenuItem("New tab");
        new_file.addActionListener(this);
        JMenuItem open_file = new JMenuItem("Open");
        open_file.addActionListener(this);
        JMenuItem save_file = new JMenuItem("Save");
        save_file.addActionListener(this);
        menu1.add(new_file);
        menu1.add(open_file);
        menu1.add(save_file);

        menubar.add(menu1);
        setJMenuBar(menubar);

        area = new JTextArea();
        area.setFont(new Font("SAN_SERIF",Font.BOLD,25));
        JScrollPane scroll_bar = new JScrollPane(area);
        scroll_bar.setBorder(BorderFactory.createEmptyBorder());
        add(scroll_bar);
    }

    @Override
    public void actionPerformed(ActionEvent ae){
        if(ae.getActionCommand().equals("New tab")){
            area.setText("");
        }
        else if(ae.getActionCommand().equals("Open")){
            JFileChooser jfc = new JFileChooser();
            jfc.setAcceptAllFileFilterUsed(false);
            FileNameExtensionFilter fnef = new FileNameExtensionFilter("*txt","txt");
            jfc.addChoosableFileFilter(fnef);
            int action = jfc.showOpenDialog(this);
            if(action != JFileChooser.APPROVE_OPTION){
                return;
            }
            else{
                try{
                    File file = jfc.getSelectedFile();
                    BufferedReader reader = new BufferedReader(new FileReader(file));
                    area.read(reader,null);
                    reader.close();
                }
                catch(Exception e){
                    e.printStackTrace();
                }
            }
        }
        else{
            JFileChooser save = new JFileChooser();
            save.setApproveButtonText("Save");
            int action = save.showOpenDialog(this);
            if(action!=JFileChooser.APPROVE_OPTION){
                return;
            }
            else{
                try{
                File file = new File(save.getSelectedFile() + ".txt");
                BufferedWriter io = new BufferedWriter(new FileWriter(file));
                area.write(io);
                io.close();
                }
                catch(Exception e){
                    e.printStackTrace();
                }
            }

        }
    }

    public static void main(String[] args) {
        new notepad();
    }
}
