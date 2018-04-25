import javax.swing.*;

public class ClientRunner {
    public static void main(String[] args) {
        boolean isIPValid = false;
        String IP = null;

        do {
            try {
                IP = JOptionPane.showInputDialog(null, "Which port to listen to?");
                if (!IP.matches("[0-9]{1,4}.[0-9]{1,4}.[0-9]{1,4}.[0-9]{1,4}"))
                    throw new NumberFormatException();
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Please enter a valid IP");
                continue;
            }
            isIPValid = true;
        } while (!isIPValid);

        System.out.println(IP);

        new ClientInterface();
    }
}
