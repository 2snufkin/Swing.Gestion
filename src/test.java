import java.awt.GridLayout;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class test {

    private JFrame frame;
    private JPanel pane;
    private JTextField daysField;
    private JTextField assignmentField;
    private int days = 0;
    private int assignments = 0;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                //Comment / uncomment one of them to see the output related to each sample method.
//              new UsingDialogsExample().annoyingGui();
              //  new UsingDialogsExample().singleDialogInformation();
            }
        });
    }

    public void annoyingGui() {
        frame = new JFrame("My Frame's Title");
        frame.setVisible(true);

        String daysInput = JOptionPane.showInputDialog(frame, "How many days are left?"); //Get user input on the textfield as a String
        String assignmentsInput = JOptionPane.showInputDialog(frame, "How many assignments are due?");

        try {
            days = Integer.parseInt(daysInput); //Convert the string gotten above to an int
            assignments = Integer.parseInt(assignmentsInput);
        } catch (NumberFormatException nfe) {
            nfe.printStackTrace();
        }

        JOptionPane.showMessageDialog(frame, "The number of days left is: " + days);
        JOptionPane.showMessageDialog(frame, "The number of assignments due is: " + assignments);
    }

    public void singleDialogInformation() {
        pane = new JPanel();
        pane.setLayout(new GridLayout(0, 2, 2, 2));

        daysField = new JTextField(5);
        assignmentField = new JTextField(5);

        pane.add(new JLabel("How many days are left?"));
        pane.add(daysField);

        pane.add(new JLabel("How many assignments are due?"));
        pane.add(assignmentField);

        int option = JOptionPane.showConfirmDialog(frame, pane, "Please fill all the fields", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);

        if (option == JOptionPane.YES_OPTION) {

            String daysInput = daysField.getText();
            String assignmentsInput = assignmentField.getText();

            try {
                days = Integer.parseInt(daysInput);
                assignments = Integer.parseInt(assignmentsInput);
            } catch (NumberFormatException nfe) {
                nfe.printStackTrace();
            }

            pane = new JPanel();
            pane.setLayout(new BoxLayout(pane, BoxLayout.PAGE_AXIS));

            pane.add(new JLabel("Days left: " + days));
            pane.add(new JLabel("Assignments due: " + assignments));

            JOptionPane.showMessageDialog(frame, pane);
        }
    }
}

