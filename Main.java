import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        // Lancer l'interface graphique
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                LogAnalyzerGUI gui = new LogAnalyzerGUI();
                gui.setVisible(true);
            }
        });
    }
}