package Model;

import java.util.Random;

public class Ship {
    private int length; // длина корабля (количество палуб)
    private int healthyDeck; // количество целых (не раненых) палуб
    public boolean isHorizontal; //направление (горизонтальное, вертикальное)
    Point leftUp = new Point(); // верхняя левая точка
    Point rightDown = new Point(); //нижняя праваЯ точка

    public Ship(int length) {
        this.length = length;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getHealthyDeck() {
        return healthyDeck;
    }

    public void setHealthyDeck(int healthyDeck) {
        this.healthyDeck = healthyDeck;
    }

    public void init(int length) {
        this.length = length;
        healthyDeck = length;
        Random random = new Random();
        isHorizontal = random.nextBoolean();
        if (isHorizontal) {
            leftUp.setX(random.nextInt(Field.SIZE - length + 1));
            leftUp.setY(random.nextInt(Field.SIZE));
            rightDown.setX(leftUp.getX() + length - 1);
            rightDown.setY(leftUp.getY());
        } else {
            leftUp.setX(random.nextInt(Field.SIZE));
            leftUp.setY(random.nextInt(Field.SIZE - length + 1));
            rightDown.setX(leftUp.getX());
            rightDown.setY(leftUp.getY() + length - 1);
        }
    }

    boolean isIntersectTwoShip(Ship anotherShip) {
        if (anotherShip.rightDown.getY() + 1 < leftUp.getY()) {
            return false;
        }
        if (anotherShip.leftUp.getY() - 1 > rightDown.getY()) {
            return false;
        }
        if (anotherShip.rightDown.getX() + 1 < leftUp.getX()) {
            return false;
        }
        if (anotherShip.leftUp.getX() - 1 > rightDown.getX()) {
            return false;
        }
        return true;
    }

 }