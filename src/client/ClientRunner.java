package client;

import javax.swing.*;

public class ClientRunner {
    public static void main(String[] args) {
        boolean isIPValid = false;
        // TODO For testing
//        String IP = null;
//
//        do {
//            try {
//                IP = JOptionPane.showInputDialog(null, "Which IP to connect to?");
//                if (!IP.matches("[0-9]{1,4}.[0-9]{1,4}.[0-9]{1,4}.[0-9]{1,4}"))
//                    throw new NumberFormatException();
//            } catch (NumberFormatException e) {
//                JOptionPane.showMessageDialog(null, "Please enter a valid IP");
//                continue;
//            }
//            isIPValid = true;
//        } while (!isIPValid);
//
//        System.out.println(IP);

        new ClientInterface("10.11.1.57", 1234);
    }
}
