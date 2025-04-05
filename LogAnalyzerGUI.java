import javax.swing.*;
import javax.swing.text.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class LogAnalyzerGUI extends JFrame {
    private JTextPane textPane; // Changé de JTextArea à JTextPane
    private JButton openButton, analyzeButton;
    private File selectedFile;
    private StyleContext styleContext;
    private StyledDocument doc;
    private int normalLogsCount = 0;
    private int anomalyLogsCount = 0;

    public LogAnalyzerGUI() {
        // Configuration de la fenêtre
        setTitle("Analyseur de Logs Réseau");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Zone de texte avec ascenseur (utilisant JTextPane)
        textPane = new JTextPane();
        textPane.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textPane);

        // Initialisation des styles
        styleContext = StyleContext.getDefaultStyleContext();
        doc = textPane.getStyledDocument();

        // Boutons
        openButton = new JButton("Ouvrir un fichier");
        analyzeButton = new JButton("Analyser");
        analyzeButton.setEnabled(false);

        // Panel pour les boutons
        JPanel panel = new JPanel();
        panel.add(openButton);
        panel.add(analyzeButton);

        // Ajout des composants à la fenêtre
        add(scrollPane, BorderLayout.CENTER);
        add(panel, BorderLayout.SOUTH);

        // Action sur le bouton "Ouvrir"
        openButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                    selectedFile = fileChooser.getSelectedFile();
                    addTextToPane("Fichier sélectionné : " + selectedFile.getName() + "\n", Color.BLACK);
                    analyzeButton.setEnabled(true);
                }
            }
        });

        // Action sur le bouton "Analyser"
        analyzeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (selectedFile != null) {
                    analyzeLogs(selectedFile);
                }
            }
        });
    }

    private void addTextToPane(String text, Color color) {
        try {
            // Créer un style avec la couleur spécifiée
            AttributeSet attributeSet = styleContext.addAttribute(SimpleAttributeSet.EMPTY, StyleConstants.Foreground, color);
            
            // Ajouter le texte au document avec le style
            doc.insertString(doc.getLength(), text, attributeSet);
        } catch (BadLocationException e) {
            e.printStackTrace();
        }
    }

    private void analyzeLogs(File file) {
        addTextToPane("Analyse en cours...\n\n", Color.BLACK);
        
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                String result = LogAnalyzer.analyzeLog(line);
                
                // Déterminer la couleur en fonction du résultat
                if (result.contains("Anomalie") ) {
                    addTextToPane(result + "\n", Color.red);
                    anomalyLogsCount++;
                } else {
                    addTextToPane(result + "\n",new Color(0,100,0));
                    normalLogsCount++;
                }
            }
            addTextToPane("\n✅ Analyse terminée !", Color.BLACK);
            addTextToPane("\nLogs normaux : " + normalLogsCount, new Color(0,100,0));
            addTextToPane("\nAnomalies détectées : " + anomalyLogsCount, Color.red);
        } catch (IOException e) {
            addTextToPane("\n❌ Erreur : " + e.getMessage(), Color.RED);
        }
    }

    
}