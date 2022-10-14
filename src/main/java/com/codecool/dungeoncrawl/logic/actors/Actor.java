package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.CellType;
import com.codecool.dungeoncrawl.logic.Drawable;

public abstract class Actor implements Drawable {
    private Cell cell;
    private int health = 100;
    private int key;
    protected int id;
    protected int damage;

    private int countSkeleton = 0;

    public Actor(Cell cell) {
        this.cell = cell;
        this.cell.setActor(this);
    }

    public void move(int dx, int dy) {
        Cell nextCell = cell.getNeighbor(dx, dy);
        collision(nextCell, CellType.TREE);
        collision(nextCell, CellType.WALL);
        if (nextCell.getType() == CellType.KEY) {
            nextCell.setType(CellType.FLOOR);
            collision(nextCell, CellType.FLOOR);
            key++;
        }
    }
    private void collision(Cell nextCell, CellType cellType) {
        if (nextCell.getType() != cellType && nextCell.getActor() == null) {
            cell.setActor(null);
            nextCell.setActor(this);
            cell = nextCell;
        }
    }


    public int getHealth() {
        return health;
    }

    public int getDamage() {
        return damage;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public int getKey() {
        return key;
    }

    public void addKey() {
        key++;
    }

    public Cell getCell() {
        return cell;
    }

    public void setCell(Cell cell) {
        this.cell = cell;
    }

    public int getX() {
        return cell.getX();
    }

    public int getY() {
        return cell.getY();
    }
}
