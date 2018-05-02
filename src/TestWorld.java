import mayflower.*;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

import static com.sun.java.accessibility.util.AWTEventMonitor.addKeyListener;

public class TestWorld extends JPanel
{

    public static void main(String[] args)
    {
        new Mayflower("Project1", 800, 600, new TestStage());


    }
}
