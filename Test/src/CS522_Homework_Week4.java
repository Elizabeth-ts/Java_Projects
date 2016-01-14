/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author mayfa
 */
public class CS522_Homework_Week4 {

    public static void main(String[] args) {
        Coin coinObj = new Coin(2, 2.0);
        System.out.format("Area : %.2f\n", coinObj.area());
        System.out.println("The coin is normal?: " + coinObj.isNormal());
    }
}

class Square {

    private int s;

    public Square(int s) {
        this.s = s;
    }

    public int getS() {
        return s;
    }

    public void setS(int s) {
        this.s = s;
    }

    public boolean isLarge() {
        return s > 10;
    }

    @Override
    public String toString() {
        return "Square{" + "s=" + s + '}';
    }

    private int square(int i) {
        return i * i;
    }

    public void enLarge(int ds) {
        s += ds;
    }

    public int area() {
        return square(s);
    }

    public int circumference() {
        return s * 4;
    }
}

class Circle {

    private double r;

    public Circle(double r) {
        this.r = r;
    }

    public double getR() {
        return r;
    }

    public void setR(double r) {
        this.r = r;
    }

    public boolean isLarge() {
        return r > 10;
    }

    public boolean isPoint() {
        return r == 0;
    }

    @Override
    public String toString() {
        return "Circle{" + "r=" + r + '}';
    }

    private double pi() {
        return 3.14f;
    }

    public void enLarge(double dr) {
        r += dr;
    }

    public double area() {
        return pi() * r * r;
    }

    public double circumference() {
        return pi() * r * 2;
    }
}

class Coin {

    private Circle circleObj;
    private Square squareObj;

    public Coin(int s1, double r1) {
        circleObj = new Circle(r1);
        squareObj = new Square(s1);
    }

    public Coin(Circle circleObj1, Square squareObj1) {
        circleObj = circleObj1;
        squareObj = squareObj1;
    }

    public Circle getCircleObj() {
        return circleObj;
    }

    public void setCircleObj(Circle circleObj) {
        this.circleObj = circleObj;
    }

    public Square getSquareObj() {
        return squareObj;
    }

    public void setSquareObj(Square squareObj) {
        this.squareObj = squareObj;
    }

    public boolean isNormal() {
        return circleObj.getR() * 2 > Math.sqrt(2.0 * squareObj.getS() * squareObj.getS());
    }

    @Override
    public String toString() {
        return "Coin{" + "circleObj=" + circleObj + ", squareObj=" + squareObj + '}';
    }

    private double getCircleArea() {
        return circleObj.area();
    }

    private int getSquareArea() {
        return squareObj.area();
    }

    public double area() {
        return getCircleArea() - getSquareArea();
    }
}
