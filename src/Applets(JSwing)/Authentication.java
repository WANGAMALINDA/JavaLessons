import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Authentication extends JFrame implements ActionListener {
    private JTextField username;
    private JTextField password;
    private JComboBox<String> role;
    private JButton validate;
    private JTextArea display;

    public Authentication() {

        setTitle("Authentication");
        setLayout(new BorderLayout(20, 20));

        JPanel form = new JPanel();
        form.setLayout(new GridLayout(5, 2, 10, 20));

        JLabel usernameLabel = new JLabel("Enter Username: ");
        username = new JTextField(15);

        JLabel passwordLabel = new JLabel("Enter Password: ");
        password = new JTextField(15);

        JLabel roleLabel = new JLabel("Select Role: ");
        String[] roles = {
                "User",
                "Admin",
                "Guest"
        };
        role = new JComboBox<>(roles);
        validate = new JButton("Validate");

        form.add(usernameLabel);
        form.add(username);
        form.add(passwordLabel);
        form.add(password);
        form.add(roleLabel);
        form.add(role);
        form.add(validate);
        validate.addActionListener(this);

        display = new JTextArea(5, 20);
        display.setEditable(false);
        display.setLineWrap(true);
        display.setWrapStyleWord(true);

        add(form, BorderLayout.CENTER);
        add(new JScrollPane(display), BorderLayout.SOUTH);

        setSize(300, 250);
        setResizable(true);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    @Override
    // Program Logic
    public void actionPerformed(ActionEvent e) {
        String Username = username.getText().trim();
        String Password = password.getText().trim();
        String Role = (String) role.getSelectedItem();

        String[][] users = {
                { "Wanga", "Peter", "John" }, // usernames
                { "wangamalinda", "peterpan", "johndoe" }, // passwords
                { "User", "Admin", "Guest" } ///roles
        };

        boolean authenticated = false;

        for (int i = 0; i < users[0].length; i++) {
            String storedUsername = users[0][i];
            String storedPassword = users[1][i];
            String storedRole = users[2][i];

            if (Username.equals(storedUsername) && Password.equals(storedPassword) && Role.equals(storedRole)) {
                authenticated = true;
                break;
            }
        }
        if (authenticated) {
            display.setText(
                    "Validation Successful! \nUsername: " + Username + "\nPasssword: " + Password + "\nRole: " + Role);
            new MovieSelection();
            dispose();
        } else {
            display.setText("Validation Unsuccessful, recheck credintials!");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Authentication form = new Authentication();
            form.setVisible(true);
        });
    }

}