import java.util.Scanner;

public class DictionaryCommandLine {

    public static void showAllWord() {
        Dictionary.print();
    }

    public static void dictionaryBasic() {
        Scanner sc = new Scanner(System.in);
        String temp;

        DictionaryManagement.insertFromFile();

        while (true) {
            System.out.println("\n0 to finish\n1 to insert a new word\n2 to show dictionary\n");

            temp = sc.nextLine();

            if (temp.compareTo("0") == 0) {
                break;
            } else if (temp.compareTo("1") == 0) {
                DictionaryManagement.insertFromCommandline();
            } else if (temp.compareTo("2") == 0) {
                DictionaryManagement.deleteWord();
            } else if (temp.compareTo("3") == 0) {
                DictionaryCommandLine.showAllWord();
            } else {
                continue;
            }
        }
    }
}