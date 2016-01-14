/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Arvin
 */
public class Database {

    private List<Song> songList;

    Database() {
        this.songList = new ArrayList();
    }

    public Database(List<Song> songList) {
        this.songList = songList;
    }

    public List<Song> getSongList() {
        return songList;
    }

    public void setSongList(List<Song> songList) {
        this.songList = songList;
    }

    public boolean isEmpty() {
        return songList.isEmpty();
    }

    public boolean addSong(Song song) {
        return songList.add(song);
    }

    public boolean removeSong(Song song) {
        return songList.remove(song);
    }

    public Song removeSongByIndex(int index){
        if(index >= 0 && index < songList.size())
            return songList.remove(index);
        return null;
    }

    @Override
    public String toString() {
        return "Database{" + "songList=" + songList.toString() + '}';
    }
    private void trace(String s){
        System.out.println(s);
    }
        
}
