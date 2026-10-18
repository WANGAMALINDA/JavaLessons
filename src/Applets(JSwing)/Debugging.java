import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Debugging {

    public static void main(String args[]) {

        JFrame frame =
        new JFrame("Library Registration System");

        frame.setLayout(new FlowLayout());

        JLabel lblName =
        new JLabel("Full Name");

        JTextField txtName =
        new JTextField(20);

        JLabel lblStudentNo =
        new JLabel("Student Number");

        JTextField txtStudentNo =
        new JTextField(15);

        JLabel lblGender =
        new JLabel("Gender");

        JRadioButton male =
        new JRadioButton("Male");

        JRadioButton female =
        new JRadioButton("Female");

        ButtonGroup bg =
        new ButtonGroup();

        bg.add(male);
        bg.add(female);

        JLabel lblMembership =
        new JLabel("Membership Type");

        JComboBox membership =
        new JComboBox();

        membership.addItem("Regular");
        membership.addItem("Premium");
        membership.addItem("VIP");

        JCheckBox fiction =
        new JCheckBox("Fiction");

        JCheckBox science =
        new JCheckBox("Science");

        JCheckBox technology =
        new JCheckBox("Technology");

        JLabel lblAddress =
        new JLabel("Address");

        JTextArea txtAddress =
        new JTextArea(4,20);

        JButton submit =
        new JButton("Submit");

        frame.add(lblName);
        frame.add(txtName);

        frame.add(lblStudentNo);
        frame.add(txtStudentNo);

        frame.add(lblGender);
        frame.add(male);
        frame.add(female);

        frame.add(lblMembership);
        frame.add(membership);

        frame.add(fiction);
        frame.add(science);
        frame.add(technology);

        frame.add(lblAddress);
        frame.add(txtAddress);

        frame.add(submit);

        submit.addActionListener( new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                String name =
                txtName.getText();

                String studentNo =
                txtStudentNo.getText();

                String gender =
                "";

                if(male.isSelected())
                    gender = "Male";

                if(female.isSelected())
                    gender = "Female";

                String member = (String) membership.getSelectedItem();

                String books = "";

                if(fiction.isSelected())
                    books += "Fiction ";

                if(science.isSelected())
                    books += "Science ";

                if(technology.isSelected())
                    books += "Technology ";

                JOptionPane.showMessageDialog(
                    frame,

                    "Library Registration\n\n" +
                    "Name: " + name +
                    "\nStudent Number: " + studentNo +
                    "\nGender: " + gender +
                    "\nMembership: " + member +
                    "\nInterests: " + books +
                    "\nAddress: " +
                    txtAddress.getText()
                );
            }
        });

        frame.setSize(500,500);
        frame.setLayout(new FlowLayout());
        frame.setDefaultCloseOperation(
        JFrame.EXIT_ON_CLOSE);

        frame.setVisible(true);
    }
}