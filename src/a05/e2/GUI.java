package a05.e2;

import javax.swing.*;

import a05.e2.LogicsImpl.Operator;

import java.util.*;
import java.util.List;
import java.awt.*;
import java.awt.event.ActionListener;

public class GUI extends JFrame {

    private static final long serialVersionUID = -6218820567019985015L;
    private final Map<JButton, Pair<Integer, Integer>> cells = new HashMap<>();
    private final Logics logics;

    public GUI(int size) {
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(100 * size, 100 * size);
        this.logics = new LogicsImpl(size);

        JPanel grid = new JPanel(new GridLayout(size, size));
        this.getContentPane().add(BorderLayout.CENTER, grid);

        ActionListener al = e -> {
            var jb = (JButton) e.getSource();
            var position = cells.get(jb);
            logics.hit(position.getX(), position.getY());
            redraw();
            if (logics.isOver()) {
                System.exit(0);
            }
        };

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                final JButton jb = new JButton("" + (i + j));
                this.cells.put(jb, new Pair<Integer, Integer>(j, i));
                grid.add(jb);
                jb.addActionListener(al);
            }
        }
        redraw();
        this.setVisible(true);
    }

    private void redraw() {
        for (var e : cells.entrySet()) {
            e.getKey()
                    .setText(logics.getValues(e.getValue()).get() ? "True"  
                                    : logics.getOperator(e.getValue()) == Operator.AND ? "AND"
                                            : logics.getOperator(e.getValue()) == Operator.OR ? "OR"
                                                    : logics.getOperator(e.getValue()) == Operator.XOR ? "XOR" : "False");
        }
    }
}