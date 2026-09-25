package java_project.src.dragclick_trainer;

import javax.swing.JComponent;
import javax.swing.JFrame;
import java.awt.GraphicsEnvironment;
import java.awt.GraphicsDevice;
import java.awt.Color;

public class Dragclick_trainer {
    public static void main(String[] args){
        GraphicsEnvironment env=GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice screen=env.getDefaultScreenDevice();
        boolean Transparency_supported=screen.isWindowTranslucencySupported(GraphicsDevice.WindowTranslucency.PERPIXEL_TRANSLUCENT);

        JFrame f= new JFrame("Dragclic Trainer");
        f.setUndecorated(true);
        if (Transparency_supported) {
            f.setBackground(new Color(255,255,255,1));;//fenetre blanche transparente
            f.getContentPane().setBackground(new Color(255,255,255,1));
            ((JComponent) f.getContentPane()).setOpaque(false);
        }
        java.awt.Rectangle bounds = screen.getDefaultConfiguration().getBounds();
        f.setBounds(bounds);
        f.setAlwaysOnTop(true);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);



        //AFFICHAGE
        //screen.setFullScreenWindow(f);
        f.setVisible(true);

    }
}
