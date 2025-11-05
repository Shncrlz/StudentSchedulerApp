import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MarioCalculatorApp extends JFrame {
    private CardLayout cardLayout;
    private JPanel mainPanel;

    public MarioCalculatorApp() {
        setTitle("🎮 Mario Calculator Adventure 🎮");
        setSize(450, 550);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        // Add pages
        mainPanel.add(new WelcomePage(), "Welcome");
        mainPanel.add(new CalculatorPage(), "Calculator");
        mainPanel.add(new RatingPage(), "Rating");

        add(mainPanel);
        showPage("Welcome");
    }

    public void showPage(String name) {
        cardLayout.show(mainPanel, name);
    }

    // -------------------------------
    // 🏁 Welcome Page
    // -------------------------------
    private class WelcomePage extends JPanel {
        public WelcomePage() {
            setLayout(new BorderLayout());
            setBackground(new Color(255, 240, 200));

            JLabel title = new JLabel("🎮 Welcome to Mario Calculator! 🎮", SwingConstants.CENTER);
            title.setFont(new Font("Comic Sans MS", Font.BOLD, 22));
            title.setForeground(Color.RED);
            add(title, BorderLayout.CENTER);

            JButton startBtn = new JButton("Start Adventure →");
            startBtn.setFont(new Font("Comic Sans MS", Font.BOLD, 18));
            startBtn.setBackground(new Color(255, 100, 100));
            startBtn.setForeground(Color.WHITE);
            startBtn.addActionListener(e -> showPage("Calculator"));
            add(startBtn, BorderLayout.SOUTH);
        }
    }

    // -------------------------------
    // 🧮 Calculator Page
    // -------------------------------
    private class CalculatorPage extends JPanel {
        private JTextField inputField;
        private JLabel resultLabel;

        public CalculatorPage() {
            setLayout(new BorderLayout(10, 10));
            setBackground(new Color(255, 235, 200));

            // Input field
            inputField = new JTextField();
            inputField.setFont(new Font("Comic Sans MS", Font.BOLD, 20));
            add(inputField, BorderLayout.NORTH);

            // Result label
            resultLabel = new JLabel("Result: ", SwingConstants.CENTER);
            resultLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 18));
            resultLabel.setForeground(Color.BLUE);
            add(resultLabel, BorderLayout.SOUTH);

            // Button grid
            JPanel buttonPanel = new JPanel(new GridLayout(5, 4, 10, 10));

            String[] buttons = {
                    "7", "8", "9", "/",
                    "4", "5", "6", "*",
                    "1", "2", "3", "-",
                    "0", ".", "(", "+",
                    ")", "C", "=", "Next →"
            };

            for (String text : buttons) {
                JButton button = new JButton(text);
                button.setFont(new Font("Comic Sans MS", Font.BOLD, 18));
                button.addActionListener(new CalculatorListener());
                buttonPanel.add(button);
            }

            add(buttonPanel, BorderLayout.CENTER);
        }

        private class CalculatorListener implements ActionListener {
            public void actionPerformed(ActionEvent e) {
                String cmd = e.getActionCommand();
                if (cmd.equals("C")) {
                    inputField.setText("");
                    resultLabel.setText("Result: ");
                } else if (cmd.equals("=")) {
                    try {
                        double result = eval(inputField.getText());
                        resultLabel.setText("Result: " + result);
                    } catch (Exception ex) {
                        resultLabel.setText("ERROR in expression.");
                    }
                } else if (cmd.equals("Next →")) {
                    showPage("Rating");
                } else {
                    inputField.setText(inputField.getText() + cmd);
                }
            }
        }

        // Same evaluator logic as before
        private double eval(final String expr) {
            return new Object() {
                int pos = -1, ch;
                void nextChar() { ch = (++pos < expr.length()) ? expr.charAt(pos) : -1; }
                boolean eat(int charToEat) {
                    while (ch == ' ') nextChar();
                    if (ch == charToEat) { nextChar(); return true; }
                    return false;
                }
                double parse() {
                    nextChar();
                    double x = parseExpression();
                    if (pos < expr.length()) throw new RuntimeException("Unexpected: " + (char) ch);
                    return x;
                }
                double parseExpression() {
                    double x = parseTerm();
                    for (;;) {
                        if (eat('+')) x += parseTerm();
                        else if (eat('-')) x -= parseTerm();
                        else return x;
                    }
                }
                double parseTerm() {
                    double x = parseFactor();
                    for (;;) {
                        if (eat('*')) x *= parseFactor();
                        else if (eat('/')) x /= parseFactor();
                        else return x;
                    }
                }
                double parseFactor() {
                    if (eat('+')) return parseFactor();
                    if (eat('-')) return -parseFactor();
                    double x;
                    int startPos = this.pos;
                    if (eat('(')) {
                        x = parseExpression();
                        eat(')');
                    } else if ((ch >= '0' && ch <= '9') || ch == '.') {
                        while ((ch >= '0' && ch <= '9') || ch == '.') nextChar();
                        x = Double.parseDouble(expr.substring(startPos, this.pos));
                    } else {
                        throw new RuntimeException("Unexpected: " + (char) ch);
                    }
                    return x;
                }
            }.parse();
        }
    }

    // -------------------------------
    // ⭐ Rating Page
    // -------------------------------
    private class RatingPage extends JPanel {
        public RatingPage() {
            setLayout(new BorderLayout());
            setBackground(new Color(255, 250, 220));

            JLabel thankYou = new JLabel("⭐ Thanks for using Mario Calculator! ⭐", SwingConstants.CENTER);
            thankYou.setFont(new Font("Comic Sans MS", Font.BOLD, 20));
            thankYou.setForeground(new Color(255, 120, 0));
            add(thankYou, BorderLayout.NORTH);

            JPanel starsPanel = new JPanel();
            starsPanel.setLayout(new FlowLayout());
            JLabel ratingLabel = new JLabel("Rate us: ");
            ratingLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 18));
            starsPanel.add(ratingLabel);

            ButtonGroup ratingGroup = new ButtonGroup();
            JRadioButton[] stars = new JRadioButton[5];
            for (int i = 0; i < 5; i++) {
                stars[i] = new JRadioButton("⭐");
                stars[i].setFont(new Font("Dialog", Font.PLAIN, 18));
                stars[i].setActionCommand(String.valueOf(i + 1));
                ratingGroup.add(stars[i]);
                starsPanel.add(stars[i]);
            }

            add(starsPanel, BorderLayout.CENTER);

            JButton exitBtn = new JButton("Exit Game");
            exitBtn.setFont(new Font("Comic Sans MS", Font.BOLD, 18));
            exitBtn.setBackground(new Color(255, 100, 100));
            exitBtn.setForeground(Color.WHITE);
            exitBtn.addActionListener(e -> {
                String rating = (ratingGroup.getSelection() != null)
                        ? ratingGroup.getSelection().getActionCommand()
                        : "0";
                JOptionPane.showMessageDialog(this,
                        "You rated Mario Calculator: " + rating + " ⭐\nThanks for playing!",
                        "Goodbye!",
                        JOptionPane.INFORMATION_MESSAGE);
                System.exit(0);
            });

            add(exitBtn, BorderLayout.SOUTH);
        }
    }

    // -------------------------------
    // 🚀 Main
    // -------------------------------
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MarioCalculatorApp app = new MarioCalculatorApp();
            app.setVisible(true);
        });
    }
}
