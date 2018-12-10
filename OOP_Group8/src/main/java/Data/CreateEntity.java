package Data;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.util.HashSet;
import java.util.Set;

public class CreateEntity {
    private Set<String> setFirstName = new HashSet<String>();
    private Set<String> setMiddleName = new HashSet<String>();
    private Set<String> setLasttName = new HashSet<String>();

    public Set<String> createSetFirstName(File file) {
        String line;
        BufferedReader in = new BufferedReader(new FileReader(file));

        while ((in.readLine() != null)) {
            setFirstName.add(in.readLine());
        }
    }
}
