package com.mps.pocs;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static com.mps.pocs.Validator.Token.*;

public class Validator {

    /*
    Valid samples:
    - []{}
    - {[()]}
    - [{}()]
    - ({[]{}})

    Invalid ones:
    - [)
    - ({[(]})
    - ((((([]))))
    - {()}}
     */
    public static boolean isValid(final String value) {
        if (value == null || value.isBlank()) return false;

        char[] tokens = value.toCharArray();
        if (isEvenLength(tokens)) {
            if (areAllTokensValid(value)) {
                return isValid(0, tokens);
            }
        }
        return false;
    }

    private static boolean isValid(int counter, char[] tokens) {
        Character closingToken = getClosingToken(tokens[counter]);
        if (closingToken != null) {
            counter++;
            isValid(counter, tokens);

            //TODO Build logic here
            return true;
        }
        return false;
    }

    private static boolean isEvenLength(char[] tokens) {
        return tokens.length % 2 == 0;
    }

    private static boolean areAllTokensValid(String value) {
        return value.chars().allMatch(token -> isValidToken((char) token));
    }

    private static boolean isValidToken(final char token) {
        return Arrays.stream(values()).anyMatch(v -> v.getToken() == token);
    }

    private static Character getClosingToken(char openingToken) {
        return tokenMap.get(openingToken);
    }

    enum Token {
        OPENING_BRACES('{'), CLOSING_BRACES('}'),
        OPENING_BRACKETS('['), CLOSING_BRACKETS(']'),
        OPENING_PARENTHESES('('), CLOSING_PARENTHESES(')');

        static Map<Character, Character> tokenMap = new HashMap<>();

        static {
            tokenMap.put(OPENING_BRACES.getToken(), CLOSING_BRACES.getToken());
            tokenMap.put(OPENING_BRACKETS.getToken(), CLOSING_BRACKETS.getToken());
            tokenMap.put(OPENING_PARENTHESES.getToken(), CLOSING_PARENTHESES.getToken());
        }

        private char token;

        Token(char token) {
            this.token = token;
        }

        public char getToken() {
            return token;
        }
    }
}
