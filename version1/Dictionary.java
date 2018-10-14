import java.io.File;

public class Dictionary {
    private static AVLTree wordList;

    static {
        wordList = new AVLTree();
    }

    public static void add(Word w) {
        if (!w.getWord_target().isEmpty()) {
            wordList.insertWord(w);
        }
    }

    public static void delete(Word w) {
        wordList.deleteWord(w);
    }

    public static Word searchWord(Word w) {
        return wordList.search(w);
    }

    public static void print() {
        wordList.printInOrder();
    }

    public static void approximateSearch(Word w) {
        wordList.approximateSearch(w);
    }

    public static void exportToFile(String fileName) {
        wordList.exportToFile(fileName);
    }
}
