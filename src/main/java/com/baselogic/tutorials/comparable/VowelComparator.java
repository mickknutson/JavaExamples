package com.baselogic.tutorials.comparable;

import java.util.Comparator;

public final class VowelComparator implements Comparator<String> {

    @Override
    public int compare(final String x, final String y) {

        if (getVowelCount(x) < getVowelCount(y)) {
            return -1;

        } else if (getVowelCount(x) > getVowelCount(y)) {
            return 1;

        }
        return 0;
    }

    private int getVowelCount(final String word) {
        int vowel = 0;
        for (int i = 0; i < word.length(); i++) {
            char chr = word.charAt(i);
            if (chr == 'a' || chr == 'A' || chr == 'e' || chr == 'E'
                    || chr == 'i' || chr == 'I' || chr == 'o' || chr == 'O'
                    || chr == 'u' || chr == 'U')
                vowel++;
        }
        return vowel;
    }

} // The End...
