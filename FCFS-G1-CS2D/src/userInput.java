import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JTextField;
import java.awt.Component;

public class userInput {

    // Declare instance variables for the panels
    private JPanel panel1;
    private JPanel p1Panel, p2Panel, p3Panel, p4Panel, p5Panel, p6Panel;

    // Add these instance variables
    private int visiblePanels = 0;
    private JLabel[] notAvailableLabels;

    public userInput() {
        // Create a JFrame instance
        JFrame frame = new JFrame("User Input Window");

        // Set the size of the window to 1200x600
        frame.setSize(1200, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null); // Center the window

        // Use BorderLayout for the main frame layout
        frame.setLayout(new BorderLayout());

        // Upper part - 2 panels (half the height) - Contains the number of process prompts and group details
        JPanel upperPanel = new JPanel(new GridLayout(1, 2)); // 1 row, 2 columns
        upperPanel.setPreferredSize(new Dimension(1200, 300)); // Half the height (300px)

        // Lower part - 6 panels (half the height) - Contains the process prompts.
        JPanel lowerPanel = new JPanel(new GridLayout(1, 6)); // 1 row, 6 columns (each column)
        lowerPanel.setPreferredSize(new Dimension(1200, 300)); // Half the height (300px)

        // Initialize the upper panels
        panel1 = new JPanel();
        JPanel panel2 = new JPanel();
        panel1.setBackground(new Color(0xD3, 0xD2, 0xDB)); 
        panel2.setBackground(Color.BLACK);

        upperPanel.add(panel1);
        upperPanel.add(panel2);

        // Call the processNum() method to add GUI elements to panel1
        processNum();

        // Initialize the lower panels as instance variables
        p1Panel = new JPanel();
        p2Panel = new JPanel();
        p3Panel = new JPanel();
        p4Panel = new JPanel();
        p5Panel = new JPanel();
        p6Panel = new JPanel();

        // Set different colors for each JPanel to differentiate them
        p1Panel.setBackground(Color.decode("#ff99c8")); // First color
        p2Panel.setBackground(Color.decode("#fec8c3")); // Second color
        p3Panel.setBackground(Color.decode("#fcf6bd")); // Third color
        p4Panel.setBackground(Color.decode("#d0f4de")); // Fourth color
        p5Panel.setBackground(Color.decode("#a9def9")); // Fifth color
        p6Panel.setBackground(Color.decode("#e4c1f9")); // Sixth color

        // Add the 6 panels to the lower part
        lowerPanel.add(p1Panel);
        lowerPanel.add(p2Panel);
        lowerPanel.add(p3Panel);
        lowerPanel.add(p4Panel);
        lowerPanel.add(p5Panel);
        lowerPanel.add(p6Panel);

        // Call the processPanel() method to add GUI elements to the 6 panels
        processPanel(p1Panel, p2Panel, p3Panel, p4Panel, p5Panel, p6Panel);

        // Add upper and lower panels to the frame
        frame.add(upperPanel, BorderLayout.NORTH); // Upper half
        frame.add(lowerPanel, BorderLayout.SOUTH); // Lower half

        // Make the frame visible
        frame.setVisible(true);

        // Initialize notAvailableLabels array
        notAvailableLabels = new JLabel[6];
        for (int i = 0; i < 6; i++) {
            notAvailableLabels[i] = new JLabel("Not Available");
            notAvailableLabels[i].setFont(new Font("Arial", Font.BOLD, 20));
            notAvailableLabels[i].setBounds(20, 50, 200, 30);
            notAvailableLabels[i].setVisible(false);
        }
    }

    // This method will process and add elements to the panels (panel1 and panel2 in the upper part)
    private void processNum() {
        // Set layout to null for absolute positioning in panel1
        panel1.setLayout(null);

        // Add JLabel for "Number of processes" and set its position
        JLabel processLabel = new JLabel("Number of processes");
        processLabel.setBounds(30, 40, 370, 30); // x, y, width, height
        processLabel.setFont(new Font("Arial", Font.BOLD, 30));
        panel1.add(processLabel);

        // Create a JPanel for the button grid, set layout to null for manual positioning
        JPanel buttonGrid = new JPanel();
        buttonGrid.setLayout(null);
        buttonGrid.setBounds(30, 80, 375, 250); // x, y, width, height
        buttonGrid.setBackground(new Color(0, 0, 0, 0)); // Transparent background

        // Set button positions and sizes manually (100x100 for each)
        for (int i = 1; i <= 6; i++) {
            JButton button = new JButton(String.valueOf(i)); // Create button with label
            button.setFont(new Font("Arial", Font.BOLD, 40)); // Set font size to 40
            button.setFocusPainted(false); // Disable focus painting
            button.setBackground(new Color(23, 22, 34)); // Default background color
            button.setForeground(Color.WHITE); // Default font color

            // Manually set position and size for each button
            int row = (i - 1) / 3;  // Determine the row (0 or 1)
            int col = (i - 1) % 3;  // Determine the column (0, 1, or 2)

            int x = col * 100;      // 100 is width + spacing (90 + 10px spacing)
            int y = row * 100;      // 100 is height + spacing (90 + 10px spacing)

            button.setBounds(x, y, 90, 90); // Set size to 90x90 for each button

            final int panelNumber = i;
            button.addActionListener(e -> {
                // Set all buttons back to default color and font color
                for (int j = 1; j <= 6; j++) {
                    JButton b = (JButton) buttonGrid.getComponent(j - 1);
                    b.setBackground(new Color(23, 22, 34)); // Reset to default color
                    b.setForeground(Color.WHITE); // Reset font color to white
                }
                // Set pressed button to highlight color and change font color to black
                button.setBackground(new Color(179, 180, 242)); // Set highlight color
                button.setForeground(Color.BLACK); // Change font color to black

                // Update visible panels
                updateVisiblePanels(panelNumber);
            });

            // Add button to the buttonGrid
            buttonGrid.add(button);
        }

        // Add the button grid to panel1
        panel1.add(buttonGrid);
    }

    private void updateVisiblePanels(int number) {
        visiblePanels = number;
        JPanel[] panels = {p1Panel, p2Panel, p3Panel, p4Panel, p5Panel, p6Panel};

        for (int i = 0; i < panels.length; i++) {
            if (i < visiblePanels) {
                showPanelContents(panels[i]);
                notAvailableLabels[i].setVisible(false);
            } else {
                hidePanelContents(panels[i]);
                notAvailableLabels[i].setVisible(true);
                panels[i].add(notAvailableLabels[i]);
            }
        }
    }

    private void showPanelContents(JPanel panel) {
        for (Component comp : panel.getComponents()) {
            if (!(comp instanceof JLabel && ((JLabel) comp).getText().equals("Not Available"))) {
                comp.setVisible(true);
            }
        }
    }

    private void hidePanelContents(JPanel panel) {
        for (Component comp : panel.getComponents()) {
            if (!(comp instanceof JLabel && ((JLabel) comp).getText().equals("Not Available"))) {
                comp.setVisible(false);
            }
        }
    }

    // This method will handle the GUI elements for p1Panel to p6Panel
    private void processPanel(JPanel p1Panel, JPanel p2Panel, JPanel p3Panel, JPanel p4Panel, JPanel p5Panel, JPanel p6Panel) {
        
        // Set null layout for absolute positioning
        p1Panel.setLayout(null);
        p2Panel.setLayout(null);
        p3Panel.setLayout(null);
        p4Panel.setLayout(null);
        p5Panel.setLayout(null);
        p6Panel.setLayout(null);

        // Adding elements to p1Panel
        JLabel p1Label = new JLabel("Process 1");
        p1Label.setFont(new Font("Arial", Font.BOLD, 20));
        p1Label.setBounds(20, 50, 200, 30); // x, y, width, height
        p1Panel.add(p1Label);
        
        // Arrival Time Label and Text Field
        JLabel p1ArriveLabel = new JLabel("Arrival Time");
        p1ArriveLabel.setFont(new Font("Arial", Font.PLAIN, 15));
        p1ArriveLabel.setBounds(20, 90, 200, 30); // x, y, width, height
        p1Panel.add(p1ArriveLabel);

        JTextField p1ArriveTextField = new JTextField();
        p1ArriveTextField.setBounds(20, 120, 100, 30); // same x, below label
        p1Panel.add(p1ArriveTextField);

        // Burst Time Label and Text Field
        JLabel p1BurstLabel = new JLabel("Burst Time");
        p1BurstLabel.setFont(new Font("Arial", Font.PLAIN, 15));
        p1BurstLabel.setBounds(20, 160, 200, 30);
        p1Panel.add(p1BurstLabel);

        JTextField p1BurstTextField = new JTextField();
        p1BurstTextField.setBounds(20, 190, 100, 30); // same x, below label
        p1Panel.add(p1BurstTextField);

        // Repeat similar structure for other panels
        // Adding elements to p2Panel
        JLabel p2Label = new JLabel("Process 2");
        p2Label.setFont(new Font("Arial", Font.BOLD, 20));
        p2Label.setBounds(20, 50, 200, 30);
        p2Panel.add(p2Label);
        
        JLabel p2ArriveLabel = new JLabel("Arrival Time");
        p2ArriveLabel.setFont(new Font("Arial", Font.PLAIN, 15));
        p2ArriveLabel.setBounds(20, 90, 200, 30);
        p2Panel.add(p2ArriveLabel);

        JTextField p2ArriveTextField = new JTextField();
        p2ArriveTextField.setBounds(20, 120, 100, 30); // same x, below label
        p2Panel.add(p2ArriveTextField);

        JLabel p2BurstLabel = new JLabel("Burst Time");
        p2BurstLabel.setFont(new Font("Arial", Font.PLAIN, 15));
        p2BurstLabel.setBounds(20, 160, 200, 30);
        p2Panel.add(p2BurstLabel);

        JTextField p2BurstTextField = new JTextField();
        p2BurstTextField.setBounds(20, 190, 100, 30); // same x, below label
        p2Panel.add(p2BurstTextField);

        // Adding elements to p3Panel
        JLabel p3Label = new JLabel("Process 3");
        p3Label.setFont(new Font("Arial", Font.BOLD, 20));
        p3Label.setBounds(20, 50, 200, 30);
        p3Panel.add(p3Label);
        
        JLabel p3ArriveLabel = new JLabel("Arrival Time");
        p3ArriveLabel.setFont(new Font("Arial", Font.PLAIN, 15));
        p3ArriveLabel.setBounds(20, 90, 200, 30);
        p3Panel.add(p3ArriveLabel);

        JTextField p3ArriveTextField = new JTextField();
        p3ArriveTextField.setBounds(20, 120, 100, 30); // same x, below label
        p3Panel.add(p3ArriveTextField);

        JLabel p3BurstLabel = new JLabel("Burst Time");
        p3BurstLabel.setFont(new Font("Arial", Font.PLAIN, 15));
        p3BurstLabel.setBounds(20, 160, 200, 30);
        p3Panel.add(p3BurstLabel);

        JTextField p3BurstTextField = new JTextField();
        p3BurstTextField.setBounds(20, 190, 100, 30); // same x, below label
        p3Panel.add(p3BurstTextField);

        // Adding elements to p4Panel
        JLabel p4Label = new JLabel("Process 4");
        p4Label.setFont(new Font("Arial", Font.BOLD, 20));
        p4Label.setBounds(20, 50, 200, 30);
        p4Panel.add(p4Label);
        
        JLabel p4ArriveLabel = new JLabel("Arrival Time");
        p4ArriveLabel.setFont(new Font("Arial", Font.PLAIN, 15));
        p4ArriveLabel.setBounds(20, 90, 200, 30);
        p4Panel.add(p4ArriveLabel);

        JTextField p4ArriveTextField = new JTextField();
        p4ArriveTextField.setBounds(20, 120, 100, 30); // same x, below label
        p4Panel.add(p4ArriveTextField);

        JLabel p4BurstLabel = new JLabel("Burst Time");
        p4BurstLabel.setFont(new Font("Arial", Font.PLAIN, 15));
        p4BurstLabel.setBounds(20, 160, 200, 30);
        p4Panel.add(p4BurstLabel);

        JTextField p4BurstTextField = new JTextField();
        p4BurstTextField.setBounds(20, 190, 100, 30); // same x, below label
        p4Panel.add(p4BurstTextField);

        // Adding elements to p5Panel
        JLabel p5Label = new JLabel("Process 5");
        p5Label.setFont(new Font("Arial", Font.BOLD, 20));
        p5Label.setBounds(20, 50, 200, 30);
        p5Panel.add(p5Label);
        
        JLabel p5ArriveLabel = new JLabel("Arrival Time");
        p5ArriveLabel.setFont(new Font("Arial", Font.PLAIN, 15));
        p5ArriveLabel.setBounds(20, 90, 200, 30);
        p5Panel.add(p5ArriveLabel);

        JTextField p5ArriveTextField = new JTextField();
        p5ArriveTextField.setBounds(20, 120, 100, 30); // same x, below label
        p5Panel.add(p5ArriveTextField);

        JLabel p5BurstLabel = new JLabel("Burst Time");
        p5BurstLabel.setFont(new Font("Arial", Font.PLAIN, 15));
        p5BurstLabel.setBounds(20, 160, 200, 30);
        p5Panel.add(p5BurstLabel);

        JTextField p5BurstTextField = new JTextField();
        p5BurstTextField.setBounds(20, 190, 100, 30); // same x, below label
        p5Panel.add(p5BurstTextField);

        // Adding elements to p6Panel
        JLabel p6Label = new JLabel("Process 6");
        p6Label.setFont(new Font("Arial", Font.BOLD, 20));
        p6Label.setBounds(20, 50, 200, 30);
        p6Panel.add(p6Label);
        
        JLabel p6ArriveLabel = new JLabel("Arrival Time");
        p6ArriveLabel.setFont(new Font("Arial", Font.PLAIN, 15));
        p6ArriveLabel.setBounds(20, 90, 200, 30);
        p6Panel.add(p6ArriveLabel);

        JTextField p6ArriveTextField = new JTextField();
        p6ArriveTextField.setBounds(20, 120, 100, 30); // same x, below label
        p6Panel.add(p6ArriveTextField);

        JLabel p6BurstLabel = new JLabel("Burst Time");
        p6BurstLabel.setFont(new Font("Arial", Font.PLAIN, 15));
        p6BurstLabel.setBounds(20, 160, 200, 30);
        p6Panel.add(p6BurstLabel);

        JTextField p6BurstTextField = new JTextField();
        p6BurstTextField.setBounds(20, 190, 100, 30); // same x, below label
        p6Panel.add(p6BurstTextField); 
    }

}
