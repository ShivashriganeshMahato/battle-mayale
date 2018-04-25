package server;

import javax.swing.*;

public class ServerRunner {
    public static void main(String[] args) {
        boolean isValid = false;
        int port = -1;

        do {
            try {
                String output = JOptionPane.showInputDialog(null, "Which port to listen to?");
                if (output.length() == 0)
                    break;
                port = Integer.parseInt(output);
                if (port > 65535)
                    throw new NumberFormatException();
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Please enter a number from 0 to 65535");
                continue;
            }
            isValid = true;
        } while (!isValid);


        if (port < 0)
            System.exit(0);
        else
            new ServerManager(port);
    }
}
