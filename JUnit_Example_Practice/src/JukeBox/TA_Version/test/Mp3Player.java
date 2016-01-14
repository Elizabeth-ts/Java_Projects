/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JukeBox.TA_Version.test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Arvin
 */
public class Mp3Player {

    public static void main(String[] args) {

//        try {
//            
//            FileInputStream fis = new FileInputStream("abc.mp3");
//            System.out.println("Working Directory = " +
//              System.getProperty("user.dir"));
//            Player playMP3 = new javazoom.jl.player.Player(fis);
//            playMP3.play();
//        } catch (FileNotFoundException | JavaLayerException ex) {
//            Logger.getLogger(Mp3Player.class.getName()).log(Level.SEVERE, null, ex);
//        }
        File file = new File("songList.txt");
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Mp3Player.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Mp3Player.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
