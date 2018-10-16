import java.io.*;
        import java.util.Scanner;

public class DictionaryManagement {
    private static Scanner sc;
    Dictionary dictionary;
    public DictionaryManagement() {
        sc = new Scanner(System.in);
        dictionary = new Dictionary();
    }

    public void insertFromCommandline () {
        String tar = sc.nextLine();
        String exp = sc.nextLine();

        dictionary.add(new Word(tar, exp));
    }

    public void insertFromFile() {
        File file = new File("E:\\Java\\Dictionary-Project\\version1\\dictionaries.txt");

        try {
            FileReader fileReader = new FileReader(file);
            BufferedReader reader = new BufferedReader(fileReader);

            String temp;
            int tempIndex;

            temp = reader.readLine();

            while ((temp = reader.readLine()) != null) {
                tempIndex = temp.indexOf('\t');
                dictionary.add(new Word(temp.substring(0, tempIndex), temp.substring(tempIndex + 1)));
            }

            fileReader.close();
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void dictionaryLookup() {
        String tar = sc.nextLine();

        Word result = dictionary.searchWord(new Word(tar, ""));

        String exp = result == null?"This dictionary doesn't have this word":result.getWord_explain();

        System.out.println(exp);
    }

    public void deleteWord() {
        String tar = sc.nextLine();

        dictionary.delete(new Word(tar, ""));
    }

    public void dictionarySearcher() {
        String tar = sc.nextLine();

        dictionary.approximateSearch(new Word(tar, ""));
    }

    public void dictionaryExportToFile() {
        String fileName = sc.nextLine();

        if (fileName.endsWith(".txt")) {
            dictionary.exportToFile(fileName);
        } else {
            System.out.println("This dictionary only support to export to \".txt\" file.\nLet try again with a \".txt\" file name.");
        }
    }
}
