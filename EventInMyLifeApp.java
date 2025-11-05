import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EventInMyLifeApp extends JFrame {

    CardLayout cardLayout;
    JPanel mainPanel;

    public EventInMyLifeApp() {
        setTitle("Event in My Life");
        setSize(400, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        // Panels
        JPanel loginPanel = createLoginPanel();
        JPanel signupPanel = createSignupPanel();
        JPanel eventsPanel = createEventsPanel();

        // Add to main panel
        mainPanel.add(loginPanel, "Login");
        mainPanel.add(signupPanel, "Signup");
        mainPanel.add(eventsPanel, "Events");

        add(mainPanel);
        setVisible(true);
    }

    private JPanel createLoginPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(Color.WHITE);

        JLabel title = new JLabel("Event in My Life", SwingConstants.CENTER);
        title.setFont(new Font("Serif", Font.PLAIN, 28));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel subtitle = new JLabel("By Andrei Medina", SwingConstants.CENTER);
        subtitle.setFont(new Font("SansSerif", Font.PLAIN, 14));
        subtitle.setAlignmentX(Component.CENTER_ALIGNMENT);

        JTextField username = new JTextField();
        username.setMaximumSize(new Dimension(300, 40));
        username.setBorder(BorderFactory.createTitledBorder("Username"));

        JPasswordField password = new JPasswordField();
        password.setMaximumSize(new Dimension(300, 40));
        password.setBorder(BorderFactory.createTitledBorder("Password"));

        JButton loginBtn = new JButton("Log in");
        loginBtn.setBackground(Color.BLACK);
        loginBtn.setForeground(Color.WHITE);
        loginBtn.setFocusPainted(false);
        loginBtn.setMaximumSize(new Dimension(200, 40));
        loginBtn.setAlignmentX(Component.CENTER_ALIGNMENT);

        JButton forgot = new JButton("Forgot password?");
        forgot.setBorderPainted(false);
        forgot.setContentAreaFilled(false);
        forgot.setForeground(Color.GRAY);
        forgot.setAlignmentX(Component.CENTER_ALIGNMENT);

        JButton signup = new JButton("Don't have an account? Sign up");
        signup.setBorderPainted(false);
        signup.setContentAreaFilled(false);
        signup.setForeground(Color.GRAY);
        signup.setAlignmentX(Component.CENTER_ALIGNMENT);

        loginBtn.addActionListener(e -> cardLayout.show(mainPanel, "Events"));
        signup.addActionListener(e -> cardLayout.show(mainPanel, "Signup"));

        panel.add(Box.createVerticalStrut(80));
        panel.add(title);
        panel.add(subtitle);
        panel.add(Box.createVerticalStrut(50));
        panel.add(username);
        panel.add(Box.createVerticalStrut(10));
        panel.add(password);
        panel.add(Box.createVerticalStrut(20));
        panel.add(loginBtn);
        panel.add(Box.createVerticalStrut(10));
        panel.add(forgot);
        panel.add(Box.createVerticalStrut(10));
        panel.add(signup);
        return panel;
    }

    private JPanel createSignupPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(Color.WHITE);

        JLabel title = new JLabel("CREATE ACCOUNT", SwingConstants.CENTER);
        title.setFont(new Font("SansSerif", Font.BOLD, 22));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        JTextField username = new JTextField();
        username.setMaximumSize(new Dimension(300, 40));
        username.setBorder(BorderFactory.createTitledBorder("Username"));

        JPasswordField password = new JPasswordField();
        password.setMaximumSize(new Dimension(300, 40));
        password.setBorder(BorderFactory.createTitledBorder("Password"));

        JTextField email = new JTextField();
        email.setMaximumSize(new Dimension(300, 40));
        email.setBorder(BorderFactory.createTitledBorder("Email"));

        JButton signupBtn = new JButton("Sign up");
        signupBtn.setBackground(Color.BLACK);
        signupBtn.setForeground(Color.WHITE);
        signupBtn.setFocusPainted(false);
        signupBtn.setMaximumSize(new Dimension(200, 40));
        signupBtn.setAlignmentX(Component.CENTER_ALIGNMENT);

        JButton login = new JButton("Already have an account? Log in");
        login.setBorderPainted(false);
        login.setContentAreaFilled(false);
        login.setForeground(Color.GRAY);
        login.setAlignmentX(Component.CENTER_ALIGNMENT);

        signupBtn.addActionListener(e -> cardLayout.show(mainPanel, "Events"));
        login.addActionListener(e -> cardLayout.show(mainPanel, "Login"));

        panel.add(Box.createVerticalStrut(100));
        panel.add(title);
        panel.add(Box.createVerticalStrut(40));
        panel.add(username);
        panel.add(Box.createVerticalStrut(10));
        panel.add(password);
        panel.add(Box.createVerticalStrut(10));
        panel.add(email);
        panel.add(Box.createVerticalStrut(20));
        panel.add(signupBtn);
        panel.add(Box.createVerticalStrut(10));
        panel.add(login);
        return panel;
    }

    private JPanel createEventsPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(Color.WHITE);

        JLabel header = new JLabel("My Events", SwingConstants.CENTER);
        header.setFont(new Font("SansSerif", Font.BOLD, 24));
        header.setAlignmentX(Component.CENTER_ALIGNMENT);

        panel.add(Box.createVerticalStrut(50));
        panel.add(header);
        panel.add(Box.createVerticalStrut(30));

        panel.add(createEventCard("My Birthday", "January 12, 2005", "🎂"));
        panel.add(Box.createVerticalStrut(10));
        panel.add(createEventCard("My Pet's Birthday", "March 17, 2021", "🐾"));
        panel.add(Box.createVerticalStrut(10));
        panel.add(createEventCard("Baguio Trip", "January 12, 2026", "🧳"));

        return panel;
    }

    private JPanel createEventCard(String title, String date, String icon) {
        JPanel card = new JPanel(new BorderLayout());
        card.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1));
        card.setMaximumSize(new Dimension(330, 80));
        card.setBackground(Color.WHITE);

        JLabel titleLabel = new JLabel("<html><b>" + title + "</b><br>" + date + "</html>");
        titleLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel iconLabel = new JLabel(icon, SwingConstants.CENTER);
        iconLabel.setFont(new Font("SansSerif", Font.PLAIN, 26));
        iconLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        card.add(titleLabel, BorderLayout.WEST);
        card.add(iconLabel, BorderLayout.EAST);

        return card;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(EventInMyLifeApp::new);
    }
}