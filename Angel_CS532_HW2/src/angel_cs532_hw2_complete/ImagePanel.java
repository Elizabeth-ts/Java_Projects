/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package angel_cs532_hw2_complete;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

/**
 *
 * @author Arvin
 */
// Image Panel to handle Images
class ImagePanel extends JPanel implements GameDataConstants {

    private Image img;

    public ImagePanel(String img) {
        this(new ImageIcon(img).getImage());
    }

    private ImagePanel(Image img) {
        this.img = img;
        //Dimension size = new Dimension(getWidth(), getHeight());
        Dimension size = new Dimension(img.getWidth(null), img.getHeight(null));
        setPreferredSize(size);
        setMinimumSize(size);
        setSize(size);
        setLayout(new GridLayout());
    }

    public void setImage(int img) {
        switch (img) {
            case ROCK:
                setImage(STRING_IMAGE_ROCK);
                break;
            case PAPER:
                setImage(STRING_IMAGE_PAPER);
                break;
            case SCISSOR:
                setImage(STRING_IMAGE_SCISSOR);
                break;
            default:
                setImage(STRING_IMAGE_PAPER);
                break;
        }
    }

    public void setImage(String img) {
        setImage(new ImageIcon(img).getImage());
    }

    private void setImage(Image img) {
        this.img = img;
        Dimension size = new Dimension(this.getWidth(), this.getHeight());
        setPreferredSize(size);
        setMinimumSize(size);
        setSize(size);
        setLayout(null);
        repaint();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), null);
    }
}
