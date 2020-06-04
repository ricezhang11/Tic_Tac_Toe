import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

public class Main {
    // git
    public static void main(String[] args) {
        /* this is to resolve thread issue, make sure 
        all these run in the Event Dispatch Thread (EDT) which
        runs the Swing GUI 
        */
        int size = 3;
        String m = JOptionPane.showInputDialog("You are about to start a Tic Tac Toe Game. Please enter the size of the board", 3);
        try{
            size = Integer.parseInt(m);
        } catch (NumberFormatException e){
            size = 3;
        }
        /* solve the problem of accessing local variable inside anonymous class
        https://stackoverflow.com/questions/33799800/java-local-variable-mi-defined-in-an-enclosing-scope-must-be-final-or-effective/33799995
        */
        final int copy_of_input = size;
        SwingUtilities.invokeLater(new Runnable(){
            /* takes updates for the GUI using a queue
            the queue takes note of changes and update the
            GUI
            */
            @Override
            public void run() {
                new TicGUI(copy_of_input);
            }
        });
    }
}