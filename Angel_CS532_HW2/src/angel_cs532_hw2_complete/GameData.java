/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package angel_cs532_hw2_complete;

import java.io.Serializable;

/**
 *
 * @author Arvin
 */
public class GameData implements Serializable {

    private int player;
    private int opponent;
    private int result;

    public GameData() {
        player = -1;
        opponent = -1;
        result = -1;
    }

    public GameData(int player) {
        this.player = player;
        opponent = -1;
        result = -1;
    }

    public int getPlayer() {
        return player;
    }

    public int getOpponent() {
        return opponent;
    }

    public int getResult() {
        return result;
    }

    public void setPlayer(int player) {
        this.player = player;
    }

    public void setOpponent(int opponent) {
        this.opponent = opponent;
    }

    public void setResult(int result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "DataObject{" + "player=" + player + ", opponent=" + opponent + ", result=" + result + '}';
    }

}
