import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MarioCalculatorGUI extends JFrame {
    private JTextField inputField;
    private JLabel resultLabel;

    public MarioCalculatorGUI() {
        setTitle("🎮 Mario Calculator 🎮");
        setSize(400, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Main panel
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout(10, 10));
        panel.setBackground(new Color(255, 235, 200));

        // Input field
        inputField = new JTextField();
        inputField.setFont(new Font("Comic Sans MS", Font.BOLD, 20));
        panel.add(inputField, BorderLayout.NORTH);

        // Result label
        resultLabel = new JLabel("Result: ", SwingConstants.CENTER);
        resultLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 18));
        resultLabel.setForeground(Color.BLUE);
        panel.add(resultLabel, BorderLayout.SOUTH);

        // Button grid
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(5, 4, 10, 10));

        String[] buttons = {
                "7", "8", "9", "/",
                "4", "5", "6", "*",
                "1", "2", "3", "-",
                "0", ".", "(", "+",
                ")", "C", "=", "Exit"
        };

        for (String text : buttons) {
            JButton button = new JButton(text);
            button.setFont(new Font("Comic Sans MS", Font.BOLD, 18));
            button.addActionListener(new ButtonClickListener());
            buttonPanel.add(button);
        }

        panel.add(buttonPanel, BorderLayout.CENTER);
        add(panel);
    }

    private class ButtonClickListener implements ActionListener {
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
            } else if (cmd.equals("Exit")) {
                System.exit(0);
            } else {
                inputField.setText(inputField.getText() + cmd);
            }
        }
    }

    // Same evaluator as in your console version
    private static double eval(final String expr) {
        return new Object() {
            int pos = -1, ch;

            void nextChar() {
                ch = (++pos < expr.length()) ? expr.charAt(pos) : -1;
            }

            boolean eat(int charToEat) {
                while (ch == ' ') nextChar();
                if (ch == charToEat) {
                    nextChar();
                    return true;
                }
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

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MarioCalculatorGUI frame = new MarioCalculatorGUI();
            frame.setVisible(true);
        });
    }
}