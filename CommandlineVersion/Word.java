import java.io.*;

public class Word {
    private String word_target;
    private String word_explain;

    public Word() {}

    public Word(String word_target, String word_explain) {
        this.word_target = word_target.trim();
        this.word_explain = word_explain.trim();
    }

    public String getWord_target() {
        return word_target;
    }

    public void setWord_target(String word_target) {
        this.word_target = word_target.trim();
    }

    public String getWord_explain() {
        return word_explain;
    }

    public void setWord_explain(String word_explain) {
        this.word_explain = word_explain.trim();
    }

    public int compareTo(Word otherWord) {
        return this.word_target.toLowerCase().compareTo(otherWord.word_target.toLowerCase());
    }

    public int approximateCompareTo(Word otherWord) {
        String temp = (this.word_target.length() > otherWord.word_target.length()?this.word_target.substring(0, otherWord.word_target.length()):this.word_target);
        return temp.toLowerCase().compareTo(otherWord.word_target.toLowerCase());
    }

    public void print() {
        System.out.println(/*word_target + "\t\t\t" + */word_explain);
    }

    public void printToFile(PrintWriter writer) {
        writer.println(word_explain);
    }
}
