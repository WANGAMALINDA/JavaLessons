import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MovieSelection extends JFrame implements ActionListener {
    private JTextField nameField;
    private JTextField authorField;
    private JComboBox<String> genreOptions;
    private JButton submissionButton;
    private JTextArea displayArea;

    public MovieSelection() {

        setTitle("Movie Selection");
        setLayout(new BorderLayout(20, 25));

        JPanel formPanel = new JPanel();
        formPanel.setLayout(new GridLayout(5, 3, 10, 15));

        JLabel nameLabel = new JLabel("Enter Movie Name: ");
        nameField = new JTextField(15);

        JLabel authorLabel = new JLabel("Enter Movie Author Name: ");
        authorField = new JTextField(15);

        JLabel genreLabel = new JLabel("Select Movie Genre: ");

        String[] genres = {
                "Horror",
                "Romantic Comedy",
                "Science Aiction",
                "Comedy",
                "Action",
                "Animation",
        };
        genreOptions = new JComboBox<>(genres);
        submissionButton = new JButton("Submit");

        formPanel.add(nameLabel);
        formPanel.add(nameField);
        formPanel.add(authorLabel);
        formPanel.add(authorField);
        formPanel.add(genreLabel);
        formPanel.add(genreOptions);
        formPanel.add(submissionButton);

        submissionButton.addActionListener(this);

        displayArea = new JTextArea(6, 25);
        displayArea.setEditable(false);
        displayArea.setLineWrap(true);
        displayArea.setWrapStyleWord(true);
        JScrollPane scrollPanel = new JScrollPane(displayArea);

        add(formPanel, BorderLayout.CENTER);
        add(scrollPanel, BorderLayout.SOUTH);

        setSize(600, 500);
        setResizable(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    @Override
    // System Logic
    public void actionPerformed(ActionEvent e) {

        String Name = nameField.getText().trim();
        String Author = authorField.getText().trim();
        String genre = (String) genreOptions.getSelectedItem();

        if (Name.isEmpty() || Author.isEmpty()) {
            displayArea.setText("Fill in all fields!");
            return;
        } else {
            displayArea.setText(" Movie Selection: \n Movie Name: "
                    + Name + "\n Author: " + Author + "\n Genre: " + genre);
            return;
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MovieSelection form = new MovieSelection();
            form.setVisible(true);
        });
    }
}
