import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

// Welcome Screen
class WelcomeScreen extends JFrame {
    public WelcomeScreen() {
        setTitle("Student Counseling System");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JPanel panel = new JPanel();
        panel.setBackground(new Color(235, 235, 235));
        panel.setLayout(new BorderLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel welcomeLabel = new JLabel("Welcome to Student Counseling System", JLabel.CENTER);
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 16));
        welcomeLabel.setForeground(new Color(51, 153, 255));

        JButton startButton = new JButton("Start Now");
        startButton.setBackground(new Color(51, 153, 255));
        startButton.setForeground(Color.WHITE);
        startButton.setFont(new Font("Arial", Font.BOLD, 14));
        startButton.addActionListener(e -> {
            new RegistrationForm();
            dispose();
        });

        panel.add(welcomeLabel, BorderLayout.CENTER);
        panel.add(startButton, BorderLayout.SOUTH);
        add(panel, BorderLayout.CENTER);
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(WelcomeScreen::new);
    }
}

// Registration Form
class RegistrationForm extends JFrame {
    public RegistrationForm() {
        setTitle("Registration Form");
        setSize(400, 350);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JPanel panel = new JPanel(new GridLayout(5, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JTextField nameField = new JTextField();
        JTextField usernameField = new JTextField();
        JTextField passwordField = new JPasswordField();
        JTextField emailField = new JTextField();
        JButton submitButton = new JButton("Register");

        panel.add(new JLabel("Full Name:")); panel.add(nameField);
        panel.add(new JLabel("Username:")); panel.add(usernameField);
        panel.add(new JLabel("Password:")); panel.add(passwordField);
        panel.add(new JLabel("Email:")); panel.add(emailField);
        panel.add(new JLabel("")); panel.add(submitButton);

        submitButton.addActionListener(e -> {
            String fullName = nameField.getText();
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());
            String email = emailField.getText();
            if (fullName.isEmpty() || username.isEmpty() || password.isEmpty() || email.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Please fill in all fields!");
                return;
            }
            new AdditionalInfoForm(fullName, username, password, email);
            dispose();
        });

        add(panel, BorderLayout.CENTER);
        setVisible(true);
    }
}

// Additional Information Form
class AdditionalInfoForm extends JFrame {
    public AdditionalInfoForm(String fullName, String username, String password, String email) {
        setTitle("Additional Information");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JPanel panel = new JPanel(new GridLayout(5, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JComboBox<String> categoryComboBox = new JComboBox<>(new String[]{"Public", "Private"});
        JComboBox<String> distanceComboBox = new JComboBox<>(new String[]{"Yes", "No"});
        JComboBox<String> hecComboBox = new JComboBox<>(new String[]{"Yes", "No"});
        JComboBox<String> cityComboBox = new JComboBox<>(new String[]{"Islamabad", "Karachi", "Lahore", "Faisalabad"});
        JButton saveButton = new JButton("Save");
        JButton accessButton = new JButton("Access");

        panel.add(new JLabel("Category:")); panel.add(categoryComboBox);
        panel.add(new JLabel("Distance Learning:")); panel.add(distanceComboBox);
        panel.add(new JLabel("HEC Recognized:")); panel.add(hecComboBox);
        panel.add(new JLabel("City:")); panel.add(cityComboBox);
        panel.add(saveButton); panel.add(accessButton);

        saveButton.addActionListener(e -> {
            try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/oopproject", "root", "")) {
                String sql = "INSERT INTO selections (full_name, username, password, email, category, distance_learning, hec_recognition, city) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
                PreparedStatement pstmt = conn.prepareStatement(sql);
                pstmt.setString(1, fullName);
                pstmt.setString(2, username);
                pstmt.setString(3, password);
                pstmt.setString(4, email);
                pstmt.setString(5, (String) categoryComboBox.getSelectedItem());
                pstmt.setString(6, (String) distanceComboBox.getSelectedItem());
                pstmt.setString(7, (String) hecComboBox.getSelectedItem());
                pstmt.setString(8, (String) cityComboBox.getSelectedItem());
                pstmt.executeUpdate();
                JOptionPane.showMessageDialog(null, "Information saved successfully!");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Error saving information: " + ex.getMessage());
            }
        });

        accessButton.addActionListener(e -> {
            try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/oopproject", "root", "")) {
                String sql = "SELECT * FROM universities WHERE category = ? AND distance_learning = ? AND hec_recognition = ? AND city = ?";
                PreparedStatement pstmt = conn.prepareStatement(sql);
                pstmt.setString(1, (String) categoryComboBox.getSelectedItem());
                pstmt.setString(2, (String) distanceComboBox.getSelectedItem());
                pstmt.setString(3, (String) hecComboBox.getSelectedItem());
                pstmt.setString(4, (String) cityComboBox.getSelectedItem());
                ResultSet rs = pstmt.executeQuery();
                StringBuilder result = new StringBuilder();
                while (rs.next()) {
                    result.append("University: ").append(rs.getString("name")).append("\n");
                }
                JOptionPane.showMessageDialog(null, result.length() > 0 ? result.toString() : "No matching universities found.");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Error accessing data: " + ex.getMessage());
            }
        });

        add(panel, BorderLayout.CENTER);
        setVisible(true);
    }
}


