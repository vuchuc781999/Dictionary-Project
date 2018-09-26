public class DictonaryCommandline {
    public static void showAllWords()
    {
        System.out.println("No\t| English\t\t\t| Vietnamese");

        for(int i = 0; i < Dictionary.wordsList.size(); i++)
        {
            System.out.println(i + 1 + "\t| " + Dictionary.wordsList.get(i).word_target + "\t\t\t\t| " + Dictionary.wordsList.get(i).word_explain);
        }
    }
}
