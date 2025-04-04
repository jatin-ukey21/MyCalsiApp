import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.StringReader;

public class CalculatorGUI {
    private JFrame frame;
    private JTextField display;
    private Parser parser;

    public CalculatorGUI() {
        frame = new JFrame("Calculator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 500); 
        frame.setLayout(new BorderLayout());

        display = new JTextField();
        display.setFont(new Font("Arial", Font.BOLD, 24));
        display.setEditable(false);
        frame.add(display, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel(new GridLayout(5, 4, 5, 5)); // Changed to 5 rows
        String[] buttons = {
            "7", "8", "9", "/",
            "4", "5", "6", "*",
            "1", "2", "3", "-",
            "0", ".", "=", "+",
            "(", ")", "C", "⌫" 
        };

        for (String text : buttons) {
            JButton button = new JButton(text);
            button.addActionListener(new ButtonClickListener());
            buttonPanel.add(button);
        }

        frame.add(buttonPanel, BorderLayout.CENTER);
    }

    private class ButtonClickListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String cmd = e.getActionCommand();
            
            if (cmd.equals("=")) {
                try {
                    Lexer lexer = new Lexer(new StringReader(display.getText()));
                    parser = new Parser(lexer);
                    parser.parse();
                    display.setText(String.valueOf(parser.getResult()));
                } catch (Exception ex) {
                    display.setText("Error");
                }
            } 
            else if (cmd.equals("C")) {
                display.setText("");
            }
            else if (cmd.equals("⌫")) {  //cross sign
                String text = display.getText();
                if (!text.isEmpty()) {
                    display.setText(text.substring(0, text.length() - 1)); 
                }
            }
            else {
                display.setText(display.getText() + cmd); 
            }
        }
    }

    public void show() {
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new CalculatorGUI().show();
        });
    }
}