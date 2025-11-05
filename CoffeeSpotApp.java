import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CoffeeSpotApp extends JFrame {
    CardLayout cardLayout;
    JPanel mainPanel;

    public CoffeeSpotApp() {
        setTitle("Coffee Spot");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 700);
        setLocationRelativeTo(null);

        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        // Panels
        JPanel loginPanel = createLoginPanel();
        JPanel signupPanel = createSignupPanel();
        JPanel homePanel = createHomePanel();

        // Add to main container
        mainPanel.add(loginPanel, "Login");
        mainPanel.add(signupPanel, "Signup");
        mainPanel.add(homePanel, "Home");

        add(mainPanel);
        setVisible(true);
    }

    private JPanel createLoginPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        panel.setBackground(Color.WHITE);

        JLabel title = new JLabel("Welcome to Coffee Spot!");
        title.setFont(new Font("Arial", Font.BOLD, 18));

        JLabel userLabel = new JLabel("Username:");
        JTextField username = new JTextField(15);

        JLabel passLabel = new JLabel("Password:");
        JPasswordField password = new JPasswordField(15);

        JButton loginBtn = new JButton("Log In");
        JLabel signupLink = new JLabel("Don't have an account? Sign up.");
        signupLink.setForeground(Color.BLUE);
        signupLink.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        // Layout setup
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.gridx = 0; gbc.gridy = 0; gbc.gridwidth = 2;
        panel.add(title, gbc);

        gbc.gridwidth = 1; gbc.gridy++;
        panel.add(userLabel, gbc);
        gbc.gridx = 1;
        panel.add(username, gbc);

        gbc.gridx = 0; gbc.gridy++;
        panel.add(passLabel, gbc);
        gbc.gridx = 1;
        panel.add(password, gbc);

        gbc.gridx = 0; gbc.gridy++; gbc.gridwidth = 2;
        panel.add(loginBtn, gbc);

        gbc.gridy++;
        panel.add(signupLink, gbc);

        // Actions
        loginBtn.addActionListener(e -> cardLayout.show(mainPanel, "Home"));
        signupLink.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent e) {
                cardLayout.show(mainPanel, "Signup");
            }
        });

        return panel;
    }

    private JPanel createSignupPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        panel.setBackground(Color.WHITE);

        JLabel userLabel = new JLabel("Username:");
        JTextField username = new JTextField(15);

        JLabel passLabel = new JLabel("Password:");
        JPasswordField password = new JPasswordField(15);

        JLabel emailLabel = new JLabel("E-mail:");
        JTextField email = new JTextField(15);

        JButton signupBtn = new JButton("Sign Up");
        JLabel loginLink = new JLabel("Already have an account? Login.");
        loginLink.setForeground(Color.BLUE);
        loginLink.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.gridx = 0; gbc.gridy = 0;
        panel.add(userLabel, gbc);
        gbc.gridx = 1;
        panel.add(username, gbc);

        gbc.gridx = 0; gbc.gridy++;
        panel.add(passLabel, gbc);
        gbc.gridx = 1;
        panel.add(password, gbc);

        gbc.gridx = 0; gbc.gridy++;
        panel.add(emailLabel, gbc);
        gbc.gridx = 1;
        panel.add(email, gbc);

        gbc.gridx = 0; gbc.gridy++; gbc.gridwidth = 2;
        panel.add(signupBtn, gbc);

        gbc.gridy++;
        panel.add(loginLink, gbc);

        signupBtn.addActionListener(e -> cardLayout.show(mainPanel, "Home"));
        loginLink.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent e) {
                cardLayout.show(mainPanel, "Login");
            }
        });

        return panel;
    }

    private JPanel createHomePanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setBackground(Color.WHITE);

        // Search bar
        JPanel topPanel = new JPanel(new BorderLayout());
        JTextField searchField = new JTextField();
        JButton searchBtn = new JButton("🔍");
        topPanel.add(searchField, BorderLayout.CENTER);
        topPanel.add(searchBtn, BorderLayout.EAST);

        // Coffee list
        JPanel listPanel = new JPanel();
        listPanel.setLayout(new BoxLayout(listPanel, BoxLayout.Y_AXIS));

        listPanel.add(createCoffeeItem("Caramel Macchiato"));
        listPanel.add(createCoffeeItem("Salted Caramel"));
        listPanel.add(createCoffeeItem("Spanish Latte"));

        JButton moreBtn = new JButton("More");

        panel.add(topPanel, BorderLayout.NORTH);
        panel.add(new JScrollPane(listPanel), BorderLayout.CENTER);
        panel.add(moreBtn, BorderLayout.SOUTH);

        return panel;
    }

    private JPanel createCoffeeItem(String name) {
        JPanel item = new JPanel(new BorderLayout());
        item.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        item.setMaximumSize(new Dimension(300, 80));

        JLabel image = new JLabel("[Image]");
        image.setPreferredSize(new Dimension(80, 80));
        JLabel label = new JLabel(name);
        label.setFont(new Font("Arial", Font.PLAIN, 14));

        item.add(image, BorderLayout.WEST);
        item.add(label, BorderLayout.CENTER);

        return item;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(CoffeeSpotApp::new);
    }
}