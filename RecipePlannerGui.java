import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class RecipePlannerGUI extends JFrame {
    private CardLayout cardLayout;
    private JPanel mainPanel;
    // Components for login
    private JTextField usernameField;
    private JPasswordField passwordField;
    // Components for registration
    private JTextField newUsernameField;
    private JPasswordField newPasswordField;
    // Components for recipe search
    private JTextField ingredientField;
    private JTextField servingsField;
    private JTextArea resultsArea;
    public RecipePlannerGUI() {
        setTitle("Recipe Planner");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 400);
        setLocationRelativeTo(null);
        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);
        // Add all cards
        mainPanel.add(createLoginPanel(), "login");
        mainPanel.add(createRegisterPanel(), "register");
        mainPanel.add(createRecipePanel(), "recipe");
        add(mainPanel);
        cardLayout.show(mainPanel, "login");
    }
    private JPanel createLoginPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(Color.WHITE);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        JLabel title = new JLabel("🔒 Login - Recipe Planner", SwingConstants.CENTER);
        title.setFont(new Font("SansSerif", Font.BOLD, 16));
        usernameField = new JTextField(15);
        passwordField = new JPasswordField(15);
        JButton loginBtn = new JButton("Login");
        loginBtn.setBackground(new Color(255, 200, 100));
        loginBtn.setFont(new Font("SansSerif", Font.BOLD, 14));
        JButton createAccBtn = new JButton("Create Account");
        createAccBtn.setBackground(new Color(220, 220, 220));
        // Actions
        loginBtn.addActionListener(e -> cardLayout.show(mainPanel, "recipe"));
        createAccBtn.addActionListener(e -> cardLayout.show(mainPanel, "register"));
        gbc.gridx = 0; gbc.gridy = 0; gbc.gridwidth = 2;
        panel.add(title, gbc);
        gbc.gridy++;
        panel.add(new JLabel("Username:"), gbc);
        gbc.gridy++;
        panel.add(usernameField, gbc);
        gbc.gridy++;
        panel.add(new JLabel("Password:"), gbc);
        gbc.gridy++;
        panel.add(passwordField, gbc);
        gbc.gridy++;
        panel.add(loginBtn, gbc);
        gbc.gridy++;
        panel.add(createAccBtn, gbc);
        return panel;
    }
    private JPanel createRegisterPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(Color.WHITE);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        JLabel title = new JLabel("📝 Create Account", SwingConstants.CENTER);
        title.setFont(new Font("SansSerif", Font.BOLD, 16));
        newUsernameField = new JTextField(15);
        newPasswordField = new JPasswordField(15);
        JButton registerBtn = new JButton("Register");
        registerBtn.setBackground(new Color(255, 200, 100));
        registerBtn.setFont(new Font("SansSerif", Font.BOLD, 14));
        JButton backBtn = new JButton("Back to Login");
        backBtn.setBackground(new Color(220, 220, 220));
        // Actions
        registerBtn.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, "Account created successfully!");
            cardLayout.show(mainPanel, "login");
        });
        backBtn.addActionListener(e -> cardLayout.show(mainPanel, "login"));
        gbc.gridx = 0; gbc.gridy = 0; gbc.gridwidth = 2;
        panel.add(title, gbc);
        gbc.gridy++;
        panel.add(new JLabel("New Username:"), gbc);
        gbc.gridy++;
        panel.add(newUsernameField, gbc);
        gbc.gridy++;
        panel.add(new JLabel("New Password:"), gbc);
        gbc.gridy++;
        panel.add(newPasswordField, gbc);
        gbc.gridy++;
        panel.add(registerBtn, gbc);
        gbc.gridy++;
        panel.add(backBtn, gbc);
        return panel;
    }
    private JPanel createRecipePanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(Color.WHITE);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        JLabel title = new JLabel("🔍 Recipe Planner", SwingConstants.CENTER);
        title.setFont(new Font("SansSerif", Font.BOLD, 16));
        ingredientField = new JTextField(15);
        servingsField = new JTextField("1", 5);
        resultsArea = new JTextArea(5, 20);
        resultsArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(resultsArea);
        JButton findBtn = new JButton("Find Recipes");
        findBtn.setBackground(new Color(255, 200, 100));
        findBtn.setFont(new Font("SansSerif", Font.BOLD, 14));
        JButton clearBtn = new JButton("Clear");
        clearBtn.setBackground(new Color(220, 220, 220));
        findBtn.addActionListener(e -> {
            String ingredient = ingredientField.getText();
            if (ingredient.isEmpty()) {
                resultsArea.setText("Please enter an ingredient.");
            } else {
                resultsArea.setText("No recipes found for \"" + ingredient + "\".");
            }
        });
        clearBtn.addActionListener(e -> {
            ingredientField.setText("");
            servingsField.setText("1");
            resultsArea.setText("");
        });
        gbc.gridx = 0; gbc.gridy = 0; gbc.gridwidth = 2;
        panel.add(title, gbc);
        gbc.gridy++;
        panel.add(new JLabel("Ingredient:"), gbc);
        gbc.gridy++;
        panel.add(ingredientField, gbc);
        gbc.gridy++;
        panel.add(new JLabel("Servings:"), gbc);
        gbc.gridy++;
        panel.add(servingsField, gbc);
        gbc.gridy++;
        panel.add(findBtn, gbc);
        gbc.gridy++;
        panel.add(clearBtn, gbc);
        gbc.gridy++;
        panel.add(new JLabel("Results:"), gbc);
        gbc.gridy++;
        panel.add(scrollPane, gbc);
        return panel;
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new RecipePlannerGUI().setVisible(true));
    }
          }
