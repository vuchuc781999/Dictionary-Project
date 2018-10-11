import java.io.*;
import java.util.Scanner;

public class DictionaryManagement {
    private static Scanner sc;

    static {
        sc = new Scanner(System.in);
    }

    public static void insertFromCommandline () {
        String tar = sc.nextLine();
        String exp = sc.nextLine();

        Dictionary.add(new Word(tar, exp));
    }

    public static void insertFromFile() {
        File file = new File(Main.class.getProtectionDomain().getCodeSource().getLocation().getPath() + "\\dictionaries.txt");

        try {
            FileReader fileReader = new FileReader(file);
            BufferedReader reader = new BufferedReader(fileReader);

            String tarTemp;

            while ((tarTemp = reader.readLine()) != null) {
                Dictionary.add(new Word(tarTemp, reader.readLine()));
            }

            fileReader.close();
            reader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void dictionaryLookup() {
        String tar = sc.nextLine();

        Word result = Dictionary.searchWord(new Word(tar, ""));

        String exp = result == null?"This dictionary doesn't have this word":result.getWord_explain();

        System.out.println(exp);
    }

    public static void deleteWord() {
        String tar = sc.nextLine();

        Dictionary.delete(new Word(tar, ""));
    }
}
