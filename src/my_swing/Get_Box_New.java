package my_swing;

import main.MethodClient;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;

import javax.swing.ImageIcon;
import java.awt.Color;
import javax.swing.GroupLayout.Alignment;
import javax.swing.GroupLayout;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.Font;
import java.awt.Dimension;

public class Get_Box_New extends javax.swing.JPanel {

    public Get_Box_New() {
        initComponents();
    }

    public void setMessage(String user, String ms,ImageIcon image) {
        txt.setText(ms);
        if (image != null) {
            Image img;
            if (image.getIconWidth() > image.getIconHeight()) {
                img = image.getImage().getScaledInstance(60, -1, Image.SCALE_SMOOTH);
            } else {
                img = image.getImage().getScaledInstance(-1, 60, Image.SCALE_SMOOTH);
            }
            image = new ImageIcon(img);
            profile.setIcon(image);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txt = new javax.swing.JTextField(){
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g;
                g2.setColor(new java.awt.Color(195, 191, 191));
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON);
                g2.drawRoundRect(0, 0, getWidth()-1, getHeight()-1, 20, 20);
            }
        };
        txt.setBackground(new Color(0, 255, 255));
        jLayeredPane1 = new javax.swing.JLayeredPane();
        border = new javax.swing.JLabel();
        profile = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));
        setMaximumSize(new java.awt.Dimension(600, 72));
        setPreferredSize(new Dimension(519, 69));

        txt.setEditable(false);
        txt.setFont(new Font("Dialog", Font.BOLD, 16)); // NOI18N
        txt.setText("your message here");
        txt.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 10, 1, 10));
        txt.setMaximumSize(new java.awt.Dimension(510, 33));
        txt.setMinimumSize(new java.awt.Dimension(510, 33));
        txt.setSelectionColor(new java.awt.Color(131, 188, 227));

        jLayeredPane1.setLayout(new javax.swing.OverlayLayout(jLayeredPane1));

        border.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        border.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/border_small.png"))); // NOI18N
        jLayeredPane1.add(border);

        profile.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        profile.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/profile_small.png"))); // NOI18N
        jLayeredPane1.add(profile);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        layout.setHorizontalGroup(
        	layout.createParallelGroup(Alignment.LEADING)
        		.addGroup(layout.createSequentialGroup()
        			.addContainerGap()
        			.addComponent(jLayeredPane1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        			.addGap(6)
        			.addComponent(txt, GroupLayout.PREFERRED_SIZE, 437, GroupLayout.PREFERRED_SIZE)
        			.addGap(85))
        );
        layout.setVerticalGroup(
        	layout.createParallelGroup(Alignment.TRAILING)
        		.addGroup(layout.createSequentialGroup()
        			.addContainerGap()
        			.addComponent(jLayeredPane1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        			.addGap(5))
        		.addGroup(Alignment.LEADING, layout.createSequentialGroup()
        			.addGap(23)
        			.addComponent(txt, GroupLayout.DEFAULT_SIZE, 42, Short.MAX_VALUE)
        			.addContainerGap())
        );
        this.setLayout(layout);
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel border;
    private javax.swing.JLayeredPane jLayeredPane1;
    private javax.swing.JLabel profile;
    private javax.swing.JTextField txt;
    // End of variables declaration//GEN-END:variables
}