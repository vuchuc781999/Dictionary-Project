import java.io.*;

public class fixInput {
    public static void main(String[] args) throws IOException {
        String a = "the";
        String b = "zzzzzz";
        System.out.println(a.compareTo(b));

        FileReader fileReader = new FileReader(fixInput.class.getProtectionDomain().getCodeSource().getLocation().getPath() + "\\dictionaries.txt");
        BufferedReader reader = new BufferedReader(fileReader);
        FileWriter fileWriter = new FileWriter("dictionaries(1).txt");
        PrintWriter writer = new PrintWriter(fileWriter);
        String sTemp;

        while ((sTemp = reader.readLine()) != null) {
            int i = 0;
            for (; i < sTemp.length(); i++) {
                if (sTemp.charAt(i) == ':') {
                    break;
                }
            }

            if (i > 1 && i < sTemp.length() - 3) {
                writer.println(sTemp.substring(0, i));
                writer.println(sTemp.substring(i + 2));
            }
        }

        fileReader.close();
        reader.close();
        fileWriter.close();
        writer.close();
    }
}
