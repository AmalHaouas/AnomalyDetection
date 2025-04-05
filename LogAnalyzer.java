import java.io.*;

public class LogAnalyzer {
    public static String analyzeLog(String line) {
        String[] parts = line.split(" ");
        int size = 0;
        String protocol = "";

        for (String part : parts) {
            if (part.startsWith("SIZE:")) {
                size = Integer.parseInt(part.split(":")[1]);
            }
            if (part.startsWith("PROTOCOL:")) {
                protocol = part.split(":")[1];
            }
        }

        if (size > 10000) {
            return "🚨 Anomalie détectée (paquet trop grand) : " + line;
        } else if (protocol.equals("ICMP") && size > 1000) {
            return "🚨 Anomalie détectée (ICMP taille anormale) : " + line;
        } else {
            return "✅ Normal : " + line;
        }
    }
}
