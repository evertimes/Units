package unit5.task2;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CharacterStreamKeywordExtractor {

    private static final Set<Character> SEPARATORS = Set
        .of('\r', '\n', '\t', ' ', '(', ')', '[', ']', '{', '}', ';', ',', '.');

    public Set<String> getContainedKeywords(String filePath) {
        var file = new File(filePath);
        char[] buffer = new char[255];
        List<Character> chars = new ArrayList<>();
        int characterCount;
        try (var fileInputStream = new FileReader(file)) {
            while ((characterCount = fileInputStream.read(buffer)) != -1) {
                for (int i = 0; i < characterCount; i++) {
                    char character = buffer[i];
                    chars.add(character);
                }
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return extractKeywords(chars);
    }

    private Set<String> extractKeywords(List<Character> chars) {
        Set<String> words = new HashSet<>();

        StringBuilder stringBuilder = new StringBuilder();
        for (Character character : chars) {
            if (SEPARATORS.contains(character)) {
                String word = stringBuilder.toString();
                if (KeywordsHolder.isKeyword(word)) {
                    words.add(word);
                }
                stringBuilder = new StringBuilder();
            } else {
                stringBuilder.append(character);
            }

        }
        return words;
    }
}
