package it.unibo.oop.lab.mvcio2;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import it.unibo.oop.lab.mvcio.Controller;
import it.unibo.oop.lab.mvcio.SimpleGUI;

/**
 * A very simple program using a graphical interface.
 * 
 */
public final class SimpleGUIWithFileChooser {

    /*
     * TODO: Starting from the application in mvcio:
     * 
     * 1) Add a JTextField and a button "Browse..." on the upper part of the
     * graphical interface.
     * Suggestion: use a second JPanel with a second BorderLayout, put the panel
     * in the North of the main panel, put the text field in the center of the
     * new panel and put the button in the line_end of the new panel.
     * 
     * 2) The JTextField should be non modifiable. And, should display the
     * current selected file.
     * 
     * 3) On press, the button should open a JFileChooser. The program should
     * use the method showSaveDialog() to display the file chooser, and if the
     * result is equal to JFileChooser.APPROVE_OPTION the program should set as
     * new file in the Controller the file chosen. If CANCEL_OPTION is returned,
     * then the program should do nothing. Otherwise, a message dialog should be
     * shown telling the user that an error has occurred (use
     * JOptionPane.showMessageDialog()).
     * 
     * 4) When in the controller a new File is set, also the graphical interface
     * must reflect such change. Suggestion: do not force the controller to
     * update the UI: in this example the UI knows when should be updated, so
     * try to keep things separated.
     */
    private final JFrame frame = new JFrame("FileChooser");
    public SimpleGUIWithFileChooser(Controller ctrl){
        final Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        final int sw = (int) screen.getWidth();
        final int sh = (int) screen.getHeight();
        frame.setSize(sw / 2, sh / 2);
        JPanel canvas = new JPanel();
        JPanel innerPn = new JPanel();
        canvas.setLayout(new BorderLayout());
        innerPn.setLayout(new BorderLayout());
        JTextField tf = new JTextField(ctrl.getFilePath());
        tf.setEditable(false);
        JButton browse = new JButton("Browse...");
        browse.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                JFileChooser fc = new JFileChooser();
                fc.setSelectedFile(ctrl.getFile());
                final int result = fc.showSaveDialog(frame);
                switch(result)  {
                
                    case JFileChooser.APPROVE_OPTION :
                        ctrl.setFile(fc.getSelectedFile());
                        tf.setText(ctrl.getFilePath());
                        break;
                    case JFileChooser.CANCEL_OPTION :
                        break;
                    default:
                        JOptionPane.showMessageDialog(frame, result, "NO!", JOptionPane.ERROR_MESSAGE);
                }
            }
            
        });
        innerPn.add(tf,BorderLayout.CENTER);
        innerPn.add(browse, BorderLayout.LINE_END);
        JButton save = new JButton("Save");
        save.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
               try {
                ctrl.writeOnFile(tf.getText());
            } catch (IOException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
            }
            
        });
        canvas.add(innerPn, BorderLayout.NORTH);
        canvas.add(save, BorderLayout.SOUTH);
        frame.setContentPane(canvas);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationByPlatform(true);
    } 
    
    void display() {
        frame.setVisible(true);
    }
    
    public static void main(final String... args) {
        SimpleGUIWithFileChooser gui = new SimpleGUIWithFileChooser(new Controller());
        gui.display();
     }

}
