package a04.e2;

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
        this.logics = new LogicsImpl(size);
        
        JPanel grid = new JPanel(new GridLayout(size,size));
        this.getContentPane().add(BorderLayout.CENTER,grid);
        this.getContentPane().add(BorderLayout.SOUTH,quit);
        
        quit.addActionListener(e -> {
            if (logics.getResult().isPresent()) {
                System.out.println("Result: "+logics.getResult().get());
                System.exit(0);
            }
        });
        
        ActionListener al = e -> {
        	var jb = (JButton)e.getSource();
            var position = cells.get(jb);
            if (logics.hit(position.getX(), position.getY())) {                
            	jb.setEnabled(false);
            }
        };
                
        for (int i=0; i<size; i++){ 
            for (int j=0; j<size; j++){
                var pos = new Pair<>(j,i);
                final JButton jb = new JButton(logics.isCellNumber(pos) ? logics.getCellNumber(pos).get()
                        : logics.getCellOperation(pos).get().getName());
                this.cells.put(jb, pos);
                grid.add(jb);
                jb.addActionListener(al);
            }
        }
        this.setVisible(true);
    }
}