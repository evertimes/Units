package unit5.task2;

import java.util.Set;

public class KeywordsHolder {

    private static final Set<String> keywords;

    static {
        keywords = Set.of("abstract", "assert", "boolean",
                          "break", "byte", "case", "catch", "char", "class", "const",
                          "continue", "default", "do", "double", "else", "extends", "false",
                          "final", "finally", "float", "for", "goto", "if", "implements",
                          "import", "instanceof", "int", "interface", "long", "native",
                          "new", "null", "package", "private", "protected", "public",
                          "return", "short", "static", "strictfp", "super", "switch",
                          "synchronized", "this", "throw", "throws", "transient", "true",
                          "try", "void", "volatile", "while");
    }

    private KeywordsHolder() {
    }

    public static boolean isKeyword(String word) {
        return keywords.contains(word);
    }
}
