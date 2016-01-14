/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package junit_inclass_exercise;

import java.util.List;

/**
 *
 * @author Arvin
 */
public class JukeBox {

    private List<String> songList;
    private int creditCardNumber;

    public JukeBox(List<String> songList, int creditCardNumber) {
        this.songList = songList;
        this.creditCardNumber = creditCardNumber;
    }

    public List<String> getSongList() {
        return songList;
    }

    public void setSongList(List<String> songList) {
        this.songList = songList;
    }

    public int getCreditCardNumber() {
        return creditCardNumber;
    }

    public void setCreditCardNumber(int creditCardNumber) {
        this.creditCardNumber = creditCardNumber;
    }

    public boolean isValidCreditCard() {
        return creditCardNumber > 0;
    }

    public void play(int i) {
        trace(songList.get(i));
    }

    private void trace(String s) {
        System.out.println(s);
    }
}
