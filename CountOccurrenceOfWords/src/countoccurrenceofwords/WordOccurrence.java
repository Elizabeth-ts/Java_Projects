/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package countoccurrenceofwords;

import java.util.Comparator;
import java.util.Objects;
/**
 *
 * @author Arvin
 */
public class WordOccurrence implements Comparable<WordOccurrence> {

    private String word;
    private int count;

    public WordOccurrence(String word, int count) {
        this.word = word;
        this.count = count;
    }

    @Override
    public String toString() {
        return "WordOccurrence{" + "word=" + word + ", count=" + count + '}';
    }

    public void setWord(String word) {
        this.word = word;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getWord() {
        return word;
    }

    public int getCount() {
        return count;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final WordOccurrence other = (WordOccurrence) obj;
        if (!Objects.equals(this.word, other.word)) {
            return false;
        }
        return true;
    }

    @Override
    public int compareTo(WordOccurrence wo) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        return Comparators.Count.compare(this, wo);
    }

    static class Comparators {

        public static Comparator<WordOccurrence> Count = new Comparator<WordOccurrence>() {
            @Override
            public int compare(WordOccurrence wo1, WordOccurrence wo2) {
                return wo1.count - wo2.count;
            }
        };
        public static Comparator<WordOccurrence> Word = new Comparator<WordOccurrence>() {
            @Override
            public int compare(WordOccurrence wo1, WordOccurrence wo2) {
                return wo1.compareTo(wo2);
            }
        };
    }
}
