import  javax.swing.*;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

public class Test {

    public static void main(String[] args) {
        JLabel nameLabel = new JLabel("Enter Name: ");
        JTextField name = new JTextField(15);
        JButton submit = new JButton("Submit");
        JTextArea display = new JTextArea();
        JFrame frame = new JFrame();
     
        frame.setLayout(new FlowLayout());
        frame.add(nameLabel);
        frame.add(name);
        frame.add(submit);
        frame.add(display, BorderLayout.CENTER);
        submit.addActionListener(event -> {
            String username = name.getText().trim();
            display.setText("Wanga".equals(username) ? "Correct username" : "Invalid username!");
        });
        frame.setSize(200, 300);
        frame.setVisible(true);

    }
}
