package java_project.src.dragclick_trainer;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.Timer;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.GraphicsEnvironment;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.GraphicsDevice;
import java.awt.Color;
import java.util.ArrayList;

public class Dragclick_trainer {
    public static void main(String[] args){
        GraphicsEnvironment env=GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice screen=env.getDefaultScreenDevice();
        boolean Transparency_supported=screen.isWindowTranslucencySupported(GraphicsDevice.WindowTranslucency.PERPIXEL_TRANSLUCENT);
        //creation de la fenetre
        JFrame f= new JFrame("Dragclic Trainer");
        f.setUndecorated(true);
        if (Transparency_supported) {
            f.setBackground(new Color(255,255,255,1));;//fenetre blanche transparente
            f.getContentPane().setBackground(new Color(255,255,255,1));
            ((JComponent) f.getContentPane()).setOpaque(false);
        }
        java.awt.Rectangle bounds = screen.getDefaultConfiguration().getBounds();
        f.setBounds(bounds);//fenetre en plein ecran
        f.setAlwaysOnTop(true);//mise au dessus
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//si on ferme la fenetre on ferme le programme
        ArrayList<Long> timestamps = new ArrayList<>();
        JLabel statsLabel = new JLabel("<html>Clics:0<br>CPS:0<br>average ms between clics:0</html>");
        f.add(statsLabel);
        long time_window_cps_ms=500;
        Timer timer = new Timer(100, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // ce bloc s'exécute toutes les 100ms
                // recalcule ton CPS ici, puis met à jour le label
                if (timestamps.size()>=2){
                    int i=1;
                    ArrayList<Long> recent_timestamps = new ArrayList<>();
                    while (i <= timestamps.size() && System.currentTimeMillis()-(timestamps.get(timestamps.size()-i))<=time_window_cps_ms){
                        recent_timestamps.add(timestamps.get(timestamps.size()-i));
                        i++;
                        //recuperation des clics dans la derniere seconde
                    }
                    long cps=1000*recent_timestamps.size()/time_window_cps_ms;
                statsLabel.setText("<html>Clics: " + timestamps.size() +"<br> CPS :"+cps+"<br>");}
            }
        });
        f.addMouseListener(new MouseAdapter(){
            @Override 
            public void mousePressed(MouseEvent e){
                timestamps.add(System.currentTimeMillis());
                System.out.println(timestamps.size());
                
            }
        });


        //AFFICHAGE
        //screen.setFullScreenWindow(f);
        timer.start();
        f.setVisible(true);

    }
}
