package flashcards;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

public class LogFile {
    ArrayList<String> logFile;

    public LogFile() {
        logFile = new ArrayList<>();
    }

    public void addLine(String line) {
        logFile.add(line);
    }

    public ArrayList getLogFile() {
        return this.logFile;
    }

    public Set getLines() {
        Set<String> lines = new LinkedHashSet<String>();
        for (String line : this.logFile) {
            lines.add(line);
        }
        return lines.size() != 0 ? lines : null;
    }

    public void printLog() {
        for (int i = 0; i < logFile.size(); i++) {
            System.out.print(logFile.get(i));
        }
        System.out.println("");
    }
}
