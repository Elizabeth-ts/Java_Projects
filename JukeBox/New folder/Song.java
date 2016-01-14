/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

/**
 *
 * @author Arvin
 */
public class Song {

    private String name, artist, album, url, format;
    private int duration;

    public Song(String url){
        this.url = url;
    }
    
    public Song(String name, String artist, String album, String url, String format, int duration) {
        this.name = name;
        this.artist = artist;
        this.album = album;
        this.url = url;
        this.format = format;
        this.duration = duration;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    @Override
    public String toString() {
        return "Song{" + "name=" + name + ", artist=" + artist + ", album=" + album + ", url=" + url + ", format=" + format + ", duration=" + duration + '}';
    }
    
    public void trace(String s){
        System.out.println(s);
    }
    
    public void play(){
         
            FileInputStream fis = null;
        try {
            fis = new FileInputStream(getUrl());
            Player playMP3 = new javazoom.jl.player.Player(fis);
            playMP3.play();
        } catch (FileNotFoundException | JavaLayerException ex) {
            Logger.getLogger(Song.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                fis.close();
            } catch (IOException ex) {
                Logger.getLogger(Song.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    }
    
}
