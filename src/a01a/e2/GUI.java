package a01a.e2;

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
        this.logics= new LogicsImpl(size);
        
        JPanel panel = new JPanel(new GridLayout(size,size));
        this.getContentPane().add(panel);
    
        ActionListener al = e -> {
            var button = (JButton) e.getSource();
            var position = cells.get(button);
            button.setText(String.valueOf(logics.hit(position.getX(), position.getY()) == 0 ? "" : "1"));
            for (var e1 : cells.entrySet()) {
                if (logics.isRectangle(e1.getValue().getX(), e1.getValue().getY())) {
                    e1.getKey().setText("*");
                }
                if (logics.isOver()) {
                    e1.getKey().setEnabled(true);
                }
            }

        };
                
        for (int i=0; i<size; i++){
            for (int j=0; j<size; j++){
                final JButton jb = new JButton(" ");
                this.cells.put(jb, new Pair<>(j,i));
                jb.addActionListener(al);
                panel.add(jb);
            }
        }
        this.setVisible(true);
    }
    
}
