package unit5.task1;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class ByteStreamKeywordExtractor {

    private static final Set<Character> SEPARATORS = Set
        .of('\r', '\n', '\t', ' ', '(', ')', '[', ']', '{', '}', ';', ',', '.');

    public Set<String> getContainedKeywords(String filePath) {
        var file = new File(filePath);
        byte[] bytes;
        try (FileInputStream fileInputStream = new FileInputStream(file)) {
            bytes = fileInputStream.readAllBytes();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return extractKeywords(bytes);
    }

    private Set<String> extractKeywords(byte[] bytes) {
        Set<String> words = new HashSet<>();

        StringBuilder stringBuilder = new StringBuilder();
        for (byte fileByte : bytes) {
            char character = Character.toChars(fileByte)[0];
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
