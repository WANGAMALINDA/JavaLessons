import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StudentRegisteration{
    public static void main(String[] args) {
        JFrame frame = new JFrame("Student Reg");

        JLabel StudentName = new JLabel("Student Name");
        JTextField nameTextField = new JTextField(14);

        JLabel gender = new JLabel("Gender");
        JRadioButton male = new JRadioButton("Male");
        JRadioButton female = new JRadioButton("Female");
        ButtonGroup bg = new ButtonGroup();
        bg.add(female);
        bg.add(male);

        JLabel interests = new JLabel("Interests");
        JCheckBox programming = new JCheckBox("Programming");
        JCheckBox networking = new JCheckBox("Networking");
        JCheckBox cyber = new JCheckBox("Cyber Sucurity");
        
        JLabel course = new JLabel("Course");
        JComboBox<String> courses = new JComboBox<>();
        courses.addItem("Java");
        courses.addItem("Python");
        courses.addItem("C++");

        JButton register = new JButton("Register");


        frame.add(StudentName);
        frame.add(nameTextField);
        frame.add(gender);
        frame.add(male);
        frame.add(female);
        frame.add(interests);
        frame.add(programming);
        frame.add(networking);
        frame.add(cyber);
        frame.add(course);
        frame.add(courses);
        frame.add(register);

        register.addActionListener(
            new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    String name = nameTextField.getText().trim();
                    String gender = "";
                    if (male.isSelected()) {
                        gender = "Male";
                    }
                    if (female.isSelected()) {
                        gender = "Female";
                    }
                    String selectedCourse = (String) courses.getSelectedItem();
                    JOptionPane.showMessageDialog(frame, "Student Register Sucessfully\n\n" + "Name: " + name + " \nGender: " + gender + "\nCourse: " + selectedCourse);
                }
            }
        );

        frame.setSize(350, 300);
        frame.setLayout(new FlowLayout());
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }
}