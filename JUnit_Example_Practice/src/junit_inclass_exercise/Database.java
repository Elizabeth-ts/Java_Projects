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
public class Database {

    private List<String> songs;

    public Database(List<String> songs) {
        this.songs = songs;
    }

    public List<String> getSongs() {
        return songs;
    }

    public void setSongs(List<String> songs) {
        this.songs = songs;
    }

    public boolean isEmpty() {
        return songs.isEmpty();
    }

    public void addSong(String song) {
        songs.add(song);
    }

    public void removeSong(String song) {
        songs.remove(song);
    }

    private void trace(String s) {
        System.out.println(s);
    }
}
