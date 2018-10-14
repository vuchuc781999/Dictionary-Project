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
        File file = new File("dictionaries.txt");

        try {
            FileReader fileReader = new FileReader(file);
            BufferedReader reader = new BufferedReader(fileReader);

            String temp;
            int tempIndex;

            temp = reader.readLine();

            while ((temp = reader.readLine()) != null) {
                tempIndex = temp.indexOf('\t');
                Dictionary.add(new Word(temp.substring(0, tempIndex), temp.substring(tempIndex + 1)));
            }

            fileReader.close();
            reader.close();
        } catch (Exception e) {
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

    public static void dictionarySearcher() {
        String tar = sc.nextLine();

        Dictionary.approximateSearch(new Word(tar, ""));
    }

    public static void dictionaryExportToFile() {
        String fileName = sc.nextLine();

        if (fileName.endsWith(".txt")) {
            Dictionary.exportToFile(fileName);
        } else {
            System.out.println("This dictionary only support to export to \".txt\" file.\nLet try again with a \".txt\" file name.");
        }
    }
}
