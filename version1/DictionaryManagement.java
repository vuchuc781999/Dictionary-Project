import java.util.Scanner;

public class DictionaryManagement {
    private static Scanner sc;

    static {
        sc = new Scanner(System.in);
    }

    public void insertFromCommandline()
    {
        Dictionary.wordsList.add(new Word(sc.nextLine(), sc.nextLine()));
    }

    public void dictionaryBasic()
    {
        this.insertFromCommandline();
        DictonaryCommandline.showAllWords();
    }
}

