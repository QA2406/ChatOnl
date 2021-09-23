package my_swing;

import main.MethodClient;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.GroupLayout.Alignment;
import javax.swing.GroupLayout;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.Color;
import java.awt.Font;

public class Send_Box_New extends javax.swing.JPanel {

    public Send_Box_New() {
        initComponents();
    }

    public void setMessage( String ms) {
    	txt.setText(ms);
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txt = new javax.swing.JTextField(){
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g;
                g2.setColor(new java.awt.Color(72, 173, 243));
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON);
                g2.drawRoundRect(0, 0, getWidth()-1, getHeight()-1, 20, 20);
            }
        };
        txt.setBackground(new Color(245, 245, 245));

        setBackground(new Color(255, 255, 255));
        setMaximumSize(new java.awt.Dimension(600, 72));

        txt.setEditable(false);
        txt.setFont(new Font("Dialog", Font.BOLD, 17)); // NOI18N
        txt.setHorizontalAlignment(javax.swing.JTextField.TRAILING);
        txt.setText("Your message here ..");
        txt.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 10, 1, 10));
        txt.setMaximumSize(new java.awt.Dimension(510, 33));
        txt.setMinimumSize(new java.awt.Dimension(510, 33));
        txt.setSelectionColor(new java.awt.Color(131, 188, 227));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        layout.setHorizontalGroup(
        	layout.createParallelGroup(Alignment.TRAILING)
        		.addGroup(layout.createSequentialGroup()
        			.addContainerGap(21, Short.MAX_VALUE)
        			.addComponent(txt, GroupLayout.PREFERRED_SIZE, 398, GroupLayout.PREFERRED_SIZE)
        			.addContainerGap())
        );
        layout.setVerticalGroup(
        	layout.createParallelGroup(Alignment.LEADING)
        		.addGroup(layout.createSequentialGroup()
        			.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        			.addComponent(txt, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE)
        			.addContainerGap())
        );
        this.setLayout(layout);
    }// </editor-fold>//GEN-END:initComponents
    private javax.swing.JTextField txt;
    // End of variables declaration//GEN-END:variables
}