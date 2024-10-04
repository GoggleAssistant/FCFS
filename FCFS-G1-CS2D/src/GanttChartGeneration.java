import javax.swing.*;
import java.awt.*;

public class GanttChartGeneration {
    private JFrame frame;
    private JPanel ganttChartPanel;

    public GanttChartGeneration() {
        // Create the main frame
        frame = new JFrame("Gantt Chart");
        frame.setSize(1000, 600);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLocationRelativeTo(null); // Center the frame on the screen

        // Set the frame's content pane to white
        frame.getContentPane().setBackground(Color.WHITE);
        frame.setLayout(null); // Use null layout for absolute positioning

        // Create the Gantt chart panel
        ganttChartPanel = new JPanel();
        ganttChartPanel.setBackground(Color.GRAY);

        // Calculate the position to center the panel
        int x = (1000 - 900) / 2;
        int y = (600 - 100) / 2;
        ganttChartPanel.setBounds(x, y, 900, 100);

        // Add the Gantt chart panel to the frame
        frame.add(ganttChartPanel);

        // Make the frame visible
        frame.setVisible(true);
    }
}
