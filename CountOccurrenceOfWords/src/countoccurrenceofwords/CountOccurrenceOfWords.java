
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package countoccurrenceofwords;

import java.util.*;

public class CountOccurrenceOfWords {

    public static void main(String[] args) {
        // Set text in a string
        String text = "Good morning. Have a good class. "
                + "Have a good visit. Have fun!";
        final List<WordOccurrence> list = new ArrayList<>();

        String[] words = text.split("[ \n\t\r.,;:!?(){}]");
        for (String s : words) {
            if (s.length() > 0) {
                s = s.toLowerCase();
                if (!list.contains(new WordOccurrence(s, 1))) {
                    list.add(new WordOccurrence(s, 1));
                } else {
                    int index = list.indexOf(new WordOccurrence(s, 1));
                    WordOccurrence word = list.get(index);
                    word.setCount(word.getCount() + 1);
                }
            }
        }
        Collections.sort(list, WordOccurrence.Comparators.Count);
        //System.out.println(list);
        for (WordOccurrence wo : list) {
            System.out.println(wo);
        }
    }
}
