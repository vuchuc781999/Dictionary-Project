import java.io.File;

public class Dictionary {
    private AVLTree wordList;

    public Dictionary(){
        wordList = new AVLTree();
    }

    public void add(Word w) {
        if (!w.getWord_target().isEmpty()) {
            wordList.insertWord(w);
        }
    }

    public  void delete(Word w) {
        wordList.deleteWord(w);
    }

    public Word searchWord(Word w) {
        return wordList.search(w);
    }

    public void print() {
        wordList.printInOrder();
    }

    public  void approximateSearch(Word w) {
        wordList.approximateSearch(w);
    }

    public  void exportToFile(String fileName) {
        wordList.exportToFile(fileName);
    }
}
