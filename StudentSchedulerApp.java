import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;

public class StudentSchedulerApp {

    public static void main(String[] args) {
        new LoginPage();
    }
}

// ---------------- LOGIN PAGE ----------------
class LoginPage extends JFrame {

    JTextField emailField;
    JPasswordField passField;

    LoginPage() {
        setTitle("Login");
        setSize(350, 350);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        getContentPane().setBackground(Color.WHITE);

        JPanel panel = new JPanel();
        panel.setBounds(50, 60, 250, 200);
        panel.setBackground(new Color(45, 45, 45));
        panel.setLayout(null);
        add(panel);

        JLabel loginLabel = new JLabel("Login");
        loginLabel.setForeground(Color.WHITE);
        loginLabel.setFont(new Font("Arial", Font.BOLD, 16));
        loginLabel.setBounds(95, 10, 80, 30);
        panel.add(loginLabel);

        JLabel emailLabel = new JLabel("Email");
        emailLabel.setForeground(Color.WHITE);
        emailLabel.setBounds(10, 50, 100, 20);
        panel.add(emailLabel);

        emailField = new JTextField();
        emailField.setBounds(10, 70, 230, 25);
        panel.add(emailField);

        JLabel passLabel = new JLabel("Password");
        passLabel.setForeground(Color.WHITE);
        passLabel.setBounds(10, 100, 100, 20);
        panel.add(passLabel);

        passField = new JPasswordField();
        passField.setBounds(10, 120, 230, 25);
        panel.add(passField);

        JButton loginBtn = new JButton("Login");
        loginBtn.setBounds(10, 155, 230, 28);
        loginBtn.setBackground(new Color(150, 85, 200));
        loginBtn.setForeground(Color.WHITE);
        panel.add(loginBtn);

        JLabel signupLink = new JLabel("Don't have an account? Sign up");
        signupLink.setForeground(Color.WHITE);
        signupLink.setBounds(30, 185, 200, 20);
        panel.add(signupLink);

        signupLink.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                dispose();
                new SignupPage();
            }
        });

        loginBtn.addActionListener(e -> {
            dispose();
            new SchedulePage();
        });

        setLocationRelativeTo(null);
        setVisible(true);
    }
}

// ---------------- SIGNUP PAGE ----------------
class SignupPage extends JFrame {

    JTextField nameField;
    JTextField emailField;
    JPasswordField passField;

    SignupPage() {
        setTitle("Sign Up");
        setSize(350, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        getContentPane().setBackground(Color.WHITE);

        JPanel panel = new JPanel();
        panel.setBounds(50, 60, 250, 270);
        panel.setBackground(new Color(45, 45, 45));
        panel.setLayout(null);
        add(panel);

        JLabel signUpLabel = new JLabel("Sign Up");
        signUpLabel.setForeground(Color.WHITE);
        signUpLabel.setFont(new Font("Arial", Font.BOLD, 16));
        signUpLabel.setBounds(90, 10, 100, 30);
        panel.add(signUpLabel);

        JLabel nameLabel = new JLabel("Full Name");
        nameLabel.setForeground(Color.WHITE);
        nameLabel.setBounds(10, 50, 100, 20);
        panel.add(nameLabel);

        nameField = new JTextField();
        nameField.setBounds(10, 70, 230, 25);
        panel.add(nameField);

        JLabel emailLabel = new JLabel("Email");
        emailLabel.setForeground(Color.WHITE);
        emailLabel.setBounds(10, 100, 100, 20);
        panel.add(emailLabel);

        emailField = new JTextField();
        emailField.setBounds(10, 120, 230, 25);
        panel.add(emailField);

        JLabel passLabel = new JLabel("Password");
        passLabel.setForeground(Color.WHITE);
        passLabel.setBounds(10, 150, 100, 20);
        panel.add(passLabel);

        passField = new JPasswordField();
        passField.setBounds(10, 170, 230, 25);
        panel.add(passField);

        JButton signUpBtn = new JButton("Sign Up");
        signUpBtn.setBounds(10, 210, 230, 28);
        signUpBtn.setBackground(new Color(150, 85, 200));
        signUpBtn.setForeground(Color.WHITE);
        panel.add(signUpBtn);

        JLabel loginLink = new JLabel("Already have an account? Login");
        loginLink.setForeground(Color.WHITE);
        loginLink.setBounds(30, 240, 200, 20);
        panel.add(loginLink);

        loginLink.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                dispose();
                new LoginPage();
            }
        });

        signUpBtn.addActionListener(e -> {
            dispose();
            new LoginPage();
        });

        setLocationRelativeTo(null);
        setVisible(true);
    }
}

// ---------------- SCHEDULE PAGE ----------------
class SchedulePage extends JFrame {

    SchedulePage() {
        setTitle("My Schedule");
        setSize(550, 350);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JLabel title = new JLabel("My Schedule", JLabel.LEFT);
        title.setFont(new Font("Arial", Font.BOLD, 18));
        title.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));

        String[] columns = {"Course", "Section", "Day", "Time", "Room"};
        String[][] data = {
                {"CCS", "BSIT2C", "Mon/Thu", "5:30PM–7:30PM", "402"},
                {"HCI", "BSIT2C", "Mon/Thu", "11:30AM–1:00PM", "203"},
                {"PLMP", "BSIT2C", "Mon/Thu", "7:30–9:00AM", "302"},
                {"MATH", "BSIT2C", "Mon", "7:30–9:00AM", "102"}
        };

        JTable table = new JTable(new DefaultTableModel(data, columns));
        JScrollPane scroll = new JScrollPane(table);

        JButton addBtn = new JButton("Add Schedule");
        addBtn.setBackground(new Color(150, 85, 200));
        addBtn.setForeground(Color.WHITE);

        JPanel bottomPanel = new JPanel();
        bottomPanel.add(addBtn);

        add(title, BorderLayout.NORTH);
        add(scroll, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);

        setLocationRelativeTo(null);
        setVisible(true);
    }
}