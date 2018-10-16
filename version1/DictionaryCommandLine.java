import java.util.Scanner;

public class DictionaryCommandLine {
    DictionaryManagement dictionaryManagement;
    public  DictionaryCommandLine()
    {
        dictionaryManagement = new DictionaryManagement();
    }
    public  void showAllWord() {
        dictionaryManagement.dictionary.print();
    }

    public void dictionaryBasic() {
        Scanner sc = new Scanner(System.in);
        String temp;

        dictionaryManagement.insertFromFile();

        while (true) {
            System.out.println("\n0 to finish\n1 to insert a new word\n2 to show dictionary\n");

            temp = sc.nextLine();

            if (temp.compareTo("0") == 0) {
                break;
            } else if (temp.compareTo("1") == 0) {
                dictionaryManagement.insertFromCommandline();
            } else if (temp.compareTo("2") == 0) {
                showAllWord();
            } else {
                continue;
            }
        }
    }

    public void dictionaryAdvanced() {
        Scanner sc = new Scanner(System.in);
        String temp;

        dictionaryManagement.insertFromFile();

        while (true) {
            System.out.println("\nPress 0 to finish\nPress 1 to insert a new word\nPress 2 to show dictionary\nPress 3 to look up a word\nPress 4 to search a word\nPress 5 to delete a word\nPress 6 to export this dictionary to a \".txt\" file\n");

            temp = sc.nextLine();

            if (temp.compareTo("0") == 0) {
                System.out.println("Finished !!!");
                break;
            } else if (temp.compareTo("1") == 0) {
                System.out.println("Word target in the first line and second line is word explain.");
                dictionaryManagement.insertFromCommandline();
            } else if (temp.compareTo("2") == 0) {
                showAllWord();
            } else if (temp.compareTo("3") == 0) {
                System.out.println("Which word do you want to look up?");
                dictionaryManagement.dictionaryLookup();
            } else if (temp.compareTo("4") == 0) {
                System.out.println("Input some first labels:");
                dictionaryManagement.dictionarySearcher();
            } else if (temp.compareTo("5") == 0) {
                System.out.println("Which word do you want to delete?");
                dictionaryManagement.deleteWord();
            } else if (temp.compareTo("6") == 0) {
                System.out.println("Input a file name you want to export to:");
                dictionaryManagement.dictionaryExportToFile();
            } else {
                System.out.println("Wrong pressing !!");
            }
        }
    }
}