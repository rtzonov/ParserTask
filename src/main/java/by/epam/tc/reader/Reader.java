package by.epam.tc.reader;

import java.io.*;


public class Reader {
    public String reader() {

        StringBuilder list = new StringBuilder();
        try {
            File file = new File("src/main/resources/XML-file.XML");
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;

            while ((line = reader.readLine()) != null) {
                line = line.trim();
                if (!line.startsWith("<")) line = " " + line;
                list.append(line);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list.toString();
    }

    public static void main(String[] args) {
        Reader a = new Reader();
        System.out.println(a.reader());
    }
}
