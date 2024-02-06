package a02b.e2;

import javax.swing.*;
import java.util.*;
import java.util.List;
import java.awt.*;
import java.awt.event.ActionListener;

public class GUI extends JFrame {
    
    private static final long serialVersionUID = -6218820567019985015L;
    private final Map<JButton, Pair<Integer,Integer>> cells = new HashMap<>();
    private final Logics logics;
    
    public GUI(int size) {
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(50*size, 50*size);
        this.logics = new LogicsImpl(size);
        
        JPanel panel = new JPanel(new GridLayout(size,size));
        this.getContentPane().add(panel);
        
        ActionListener al = e -> {
            //var jb = (JButton) e.getSource();
            logics.move();
            redraw();
            if (logics.isOver()) {
                System.exit(0);
            }
        };
                
        for (int i=0; i<size; i++){
            for (int j=0; j<size; j++){
                final JButton jb = new JButton(" ");
                this.cells.put(jb, new Pair<Integer,Integer>(j, i));
                jb.addActionListener(al);
                panel.add(jb);
            }
        }
        redraw();
        this.setVisible(true);
    }

    private void redraw(){
        var asterisch= logics.getPosition();
        for (var entry : cells.entrySet()) {
            entry.getKey().setText(entry.getValue().equals(asterisch)?"*":
            logics.isDirectionL(entry.getValue().getX(), entry.getValue().getY())?"L":
            logics.isDirectionR(entry.getValue().getX(), entry.getValue().getY())?"R":"");
        }
    }
    
}
