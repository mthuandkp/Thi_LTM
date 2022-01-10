/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.awt.Color;
import java.awt.Container;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import org.json.JSONObject;

/**
 *
 * @author mthuan
 */
public class Chat_Room extends javax.swing.JFrame {

    Socket socket;
    String name;
    BufferedReader input;
    BufferedWriter output;

    /**
     * Creates new form Chat_Room
     */
    public Chat_Room() {
        initComponents();
        initDisplay();
    }

    public Chat_Room(Socket s, String n) {
        this.socket = s;
        this.name = n;
        initComponents();
        initDisplay();

    }

    /**
     * This method is called from within the constructor to initialize the form. WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        time = new javax.swing.JLabel();
        date = new javax.swing.JLabel();
        uname = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        chatPanel = new javax.swing.JPanel();
        messagePane = new javax.swing.JScrollPane();
        jPanel1 = new javax.swing.JPanel();
        inputChat = new javax.swing.JTextField();
        time1 = new javax.swing.JLabel();
        sendBtnPanel = new javax.swing.JPanel();
        sendBtn = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(800, 800));
        getContentPane().setLayout(new javax.swing.BoxLayout(getContentPane(), javax.swing.BoxLayout.LINE_AXIS));

        jPanel2.setBackground(new java.awt.Color(115, 82, 246));
        jPanel2.setPreferredSize(new java.awt.Dimension(150, 585));
        jPanel2.setLayout(new java.awt.BorderLayout());

        jPanel3.setBackground(new java.awt.Color(141, 113, 250));
        jPanel3.setPreferredSize(new java.awt.Dimension(233, 230));

        time.setFont(new java.awt.Font("Arimo", 1, 24)); // NOI18N
        time.setForeground(new java.awt.Color(254, 254, 254));
        time.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        time.setText("20:05:17");

        date.setFont(new java.awt.Font("Arimo", 1, 24)); // NOI18N
        date.setForeground(new java.awt.Color(254, 254, 254));
        date.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        date.setText("28/01/2001");

        uname.setFont(new java.awt.Font("Arimo", 1, 24)); // NOI18N
        uname.setForeground(new java.awt.Color(252, 232, 130));
        uname.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        uname.setText("jLabel1");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(time, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(date, javax.swing.GroupLayout.DEFAULT_SIZE, 233, Short.MAX_VALUE)
            .addComponent(uname, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(uname)
                .addGap(55, 55, 55)
                .addComponent(date)
                .addGap(18, 18, 18)
                .addComponent(time)
                .addContainerGap(67, Short.MAX_VALUE))
        );

        jPanel2.add(jPanel3, java.awt.BorderLayout.PAGE_START);

        jPanel5.setBackground(new java.awt.Color(115, 82, 246));

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 233, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        jPanel2.add(jPanel5, java.awt.BorderLayout.PAGE_END);

        getContentPane().add(jPanel2);

        chatPanel.setBackground(new java.awt.Color(254, 254, 254));

        messagePane.setBackground(new java.awt.Color(254, 254, 254));

        jPanel1.setBackground(new java.awt.Color(115, 82, 246));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(142, 49, 215)));

        inputChat.setFont(new java.awt.Font("Times New Roman", 0, 24)); // NOI18N

        time1.setFont(new java.awt.Font("Arimo", 1, 18)); // NOI18N
        time1.setForeground(new java.awt.Color(254, 254, 254));
        time1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        time1.setText("Nhập nội dung chat:");

        sendBtnPanel.setBackground(new java.awt.Color(115, 82, 246));

        sendBtn.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        sendBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/icons8-send-60.png"))); // NOI18N
        sendBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                sendBtnMouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                sendBtnMouseExited(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                sendBtnMouseEntered(evt);
            }
        });

        javax.swing.GroupLayout sendBtnPanelLayout = new javax.swing.GroupLayout(sendBtnPanel);
        sendBtnPanel.setLayout(sendBtnPanelLayout);
        sendBtnPanelLayout.setHorizontalGroup(
            sendBtnPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(sendBtn, javax.swing.GroupLayout.DEFAULT_SIZE, 46, Short.MAX_VALUE)
        );
        sendBtnPanelLayout.setVerticalGroup(
            sendBtnPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(sendBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(time1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(inputChat, javax.swing.GroupLayout.PREFERRED_SIZE, 364, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 17, Short.MAX_VALUE)
                        .addComponent(sendBtnPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 12, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(time1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(inputChat, javax.swing.GroupLayout.DEFAULT_SIZE, 46, Short.MAX_VALUE)
                    .addComponent(sendBtnPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout chatPanelLayout = new javax.swing.GroupLayout(chatPanel);
        chatPanel.setLayout(chatPanelLayout);
        chatPanelLayout.setHorizontalGroup(
            chatPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(messagePane)
        );
        chatPanelLayout.setVerticalGroup(
            chatPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(chatPanelLayout.createSequentialGroup()
                .addComponent(messagePane, javax.swing.GroupLayout.DEFAULT_SIZE, 469, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        getContentPane().add(chatPanel);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void sendBtnMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_sendBtnMouseEntered
        // TODO add your handling code here:
        sendBtnPanel.setBackground(new Color(135, 101, 227));
    }//GEN-LAST:event_sendBtnMouseEntered

    private void sendBtnMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_sendBtnMouseExited
        // TODO add your handling code here:
        sendBtnPanel.setBackground(new Color(115, 82, 246));
    }//GEN-LAST:event_sendBtnMouseExited

    private void sendBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_sendBtnMouseClicked
        // TODO add your handling code here:
        String mess = inputChat.getText();
        if (mess.equals("")) {
            JOptionPane.showMessageDialog(null, "Thử gõ vài từ xem ...");
            return;
        }
        Container conn = null;
        //Them vao khnug chat
        try{
            conn = (Container) messagePane.getViewport().getComponents()[0];
        }catch(Exception e){
            conn = new Container();
        }
        if (conn == null) {
            conn = new Container();
        }

        //JPanel newMess = new RightMess(name, mess);
        JPanel newMess = new RightMess("Bạn", mess);
        
        conn.add(newMess);
        conn.setLayout(new BoxLayout(conn, BoxLayout.Y_AXIS));

        messagePane.getViewport().setView(conn);

        JScrollBar vertical = messagePane.getVerticalScrollBar();
        vertical.setValue(vertical.getMaximum());

        try {
            output = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            JSONObject obj = new JSONObject();
            obj.put("cmd", "CHAT_MESSAGE");
            obj.put("data", mess);

            output.write(obj.toString());
            output.newLine();
            output.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
        inputChat.setText("");
    }//GEN-LAST:event_sendBtnMouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Chat_Room.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Chat_Room.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Chat_Room.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Chat_Room.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Chat_Room().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel chatPanel;
    private javax.swing.JLabel date;
    private javax.swing.JTextField inputChat;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane messagePane;
    private javax.swing.JLabel sendBtn;
    private javax.swing.JPanel sendBtnPanel;
    private javax.swing.JLabel time;
    private javax.swing.JLabel time1;
    private javax.swing.JLabel uname;
    // End of variables declaration//GEN-END:variables

    private void initDisplay() {
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        messagePane.getViewport().setBackground(Color.WHITE);
        inputChat.setBackground(new Color(0, 0, 0, 0));
        inputChat.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.WHITE));
        inputChat.setForeground(Color.WHITE);
        uname.setText(name);

        

        new Thread(new TimeCal()).start();
    }

    void terminal() {
        this.dispose();
    }

    private class TimeCal implements Runnable {

        public TimeCal() {
        }

        @Override
        public void run() {
            try {
                while (true) {
                    //LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss dd:MM-yyyy"))
                    date.setText(LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd:MM-yyyy")));
                    time.setText(LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss")));
                    Thread.sleep(1000);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void addMessReceive(String name, String chat) {
        Container conn = null;
        //Them vao khnug chat
        try{
            conn = (Container) messagePane.getViewport().getComponents()[0];
        }catch(Exception e){
            conn = new Container();
        }
        if (conn == null) {
            conn = new Container();
        }

        JPanel newMess = new LeftMess(name, chat);

        conn.add(newMess);
        conn.setLayout(new BoxLayout(conn, BoxLayout.Y_AXIS));

        messagePane.getViewport().setView(conn);
        JScrollBar vertical = messagePane.getVerticalScrollBar();
        vertical.setValue(vertical.getMaximum());
        
    }
}
