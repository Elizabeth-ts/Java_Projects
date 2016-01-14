/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JukeBox.TA_Version;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Arvin
 */
public class JukeBox {

    private Database songList;
    private int creditCard = 0;

    public JukeBox() {
        songList = new Database();
    }

    public JukeBox(int creditCard) {
        super();
        this.creditCard = creditCard;
    }

    public JukeBox(Database songList, int creditCard) {
        super();
        this.songList = songList;
        this.creditCard = creditCard;
    }

    public Database getSongList() {
        return songList;
    }

    public void setSongList(Database songList) {
        this.songList = songList;
    }

    public int getCreditCard() {
        return creditCard;
    }

    public void setCreditCard(int creditCard) {
        this.creditCard = creditCard;
    }

    public boolean isValidCreditCard(int creditCard) {
        return creditCard > 0;
    }

    boolean initialsongListFromScript(String url) {

        BufferedReader br = null;
        try {
            File file = new File(url);
            br = new BufferedReader(new FileReader(file));
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
                songList.getSongList().add(new Song(line));
            }
            return true;

        } catch (IOException ex) {
            Logger.getLogger(JukeBox.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                br.close();
            } catch (IOException ex) {
                Logger.getLogger(JukeBox.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return false;
    }

    void playByIndex(int index) {
        songList.getSongList().get(index).play();
    }

    void playAll() {
        for (Song s : songList.getSongList()) {
            trace("Song: " + s);
            s.play();
        }
    }

    private void trace(String s) {
        System.out.println(s);
    }

    public static void main(String[] args) {

        JukeBox jb = new JukeBox();
        jb.initialsongListFromScript("C:\\Users\\mayfa\\Documents\\NetBeansProjects\\JukeBox\\New folders\\ongList.txt");
        System.out.println(jb.getSongList().toString());
        jb.playAll();
    }
}
