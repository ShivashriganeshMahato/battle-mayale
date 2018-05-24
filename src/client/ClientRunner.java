package client;

import javax.swing.*;

public class ClientRunner {
    public static void main(String[] args) {
        // Ask for IP to connect to
        boolean isIPValid = false;
        String IP = null;
        boolean isPortValid = false;
        int port = -1;

        do {
            try {
                IP = JOptionPane.showInputDialog(null, "Which IP to connect to?");
                if (!IP.matches("[0-9]{1,4}.[0-9]{1,4}.[0-9]{1,4}.[0-9]{1,4}"))
                    throw new NumberFormatException();
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Please enter a valid IP");
                continue;
            }
            isIPValid = true;
        } while (!isIPValid);

        do {
            try {
                String output = JOptionPane.showInputDialog(null, "Which port to connect to?");
                if (output.length() == 0)
                    break;
                port = Integer.parseInt(output);
                if (port > 65535)
                    throw new NumberFormatException();
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Please enter a number from 0 to 65535");
                continue;
            }
            isPortValid = true;
        } while (!isPortValid);

        JOptionPane.showMessageDialog(null, "Connecting to " + IP + ":" + port);

        System.out.println(IP);

        new ClientInterface(IP, port);
    }
}
