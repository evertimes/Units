package unit5.task1;

public class App {

    public static void main(String[] args) {
        ByteStreamKeywordExtractor byteStreamKeywordExtractor = new ByteStreamKeywordExtractor();
        System.out.println(byteStreamKeywordExtractor.getContainedKeywords("unit5/src/main/resources/App.txt"));
    }
}
