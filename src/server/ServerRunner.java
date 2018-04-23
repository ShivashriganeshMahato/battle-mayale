package server;

import javax.swing.*;

public class ServerRunner {
    public static void main(String[] args) {
        boolean isValid = false;
        int port = -1;

        do {
            try {
                port = Integer.parseInt(JOptionPane.showInputDialog(null, "Which port to listen to?"));
                if (port < 0 || port > 65535)
                    throw new NumberFormatException();
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Please enter a number from 0 to 65535");
                continue;
            }
            isValid = true;
        } while (!isValid);

        new ServerManager(port);
    }
}
