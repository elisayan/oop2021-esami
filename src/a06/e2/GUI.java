package a06.e2;

import javax.swing.*;

import java.util.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class GUI extends JFrame {
    
    private static final long serialVersionUID = -6218820567019985015L;
    private final Map<JButton, Pair<Integer,Integer>> cells = new HashMap<>();
    private final JButton quit = new JButton("QUIT");
    private final Logics logics;
        
    public GUI(int size) {
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(100*size, 100*size);
        this.logics=new LogicsImpl(size);
        
        JPanel grid = new JPanel(new GridLayout(size,size));
        this.getContentPane().add(BorderLayout.CENTER,grid);
        this.getContentPane().add(BorderLayout.SOUTH,quit);
        
        quit.addActionListener(e -> {
            logics.setAdvance();
            for (var entry : cells.entrySet()) {
                entry.getKey().setText(logics.isAdvance(entry.getValue().getX(), entry.getValue().getY()) ? "*"
                        : logics.isSelected(entry.getValue().getX(), entry.getValue().getY()) ? "o" : "");
                entry.getKey().setEnabled(false);
            }
            if (logics.isOver()) {
                System.exit(0);
            }
       	});
        
        ActionListener al = e -> {
        	var jb = (JButton)e.getSource();
        	var position = cells.get(jb);
            
            jb.setText(logics.hit(position.getX(), position.getY()) ? "o" : "");
        };
                
        for (int i=0; i<size; i++){
            for (int j=0; j<size; j++){
                final JButton jb = new JButton("  ");
                this.cells.put(jb, new Pair<Integer,Integer>(j, i));
                grid.add(jb);
                jb.addActionListener(al);
            }
        }
        this.setVisible(true);
    }
}