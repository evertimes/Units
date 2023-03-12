package unit5.task2;

public class App {

    public static void main(String[] args) {
        CharacterStreamKeywordExtractor characterStreamKeywordExtractor = new CharacterStreamKeywordExtractor();
        System.out.println(characterStreamKeywordExtractor.getContainedKeywords("unit5/src/main/resources/App.txt"));
    }
}
