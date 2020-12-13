package com.igiantpanda.androidtetris.shape;


import android.graphics.Point;

import com.igiantpanda.androidtetris.GameView;
import com.igiantpanda.androidtetris.IShape;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseShape implements IShape {
    private Point mOldPosition;
    private Point mPosition;
    private Point mCenter;
//    private List<Point> mOldBoxes;
    private List<Point> mBoxes;
    private GameView mParent;

//    abstract protected boolean canRotate();
//    abstract protected boolean canMove(Direction direction);
//    public BaseShape(List<Point> boxes) {
//        mBoxes = boxes;
//    }

    private boolean canDown(boolean maps[][]) {
        for (Point box : mBoxes) {
            int x = box.x + mPosition.x;
            int y = box.y + mPosition.y + 1;

            if (y >= maps[x].length) {
                return false;
            }

            if (maps[x][y]) {
                return false;
            }
        }

        return true;
    }

    private boolean canLeft(boolean maps[][]) {
        for (Point box : mBoxes) {
            int x = box.x + mPosition.x - 1;
            int y = box.y + mPosition.y;

            if (x < 0) {
                return false;
            }

            if (maps[x][y]) {
                return false;
            }
        }

        return true;
    }

    private boolean canRight(boolean maps[][]) {
        for (Point box : mBoxes) {
            int x = box.x + mPosition.x + 1;
            int y = box.y + mPosition.y;

            if (x >= maps.length) {
                return false;
            }

            if (maps[x][y]) {
                return false;
            }
        }

        return true;
    }
    protected void init(List<Point> boxes) {
        mBoxes = boxes;
//        mOldBoxes = new ArrayList<>(mBoxes);
        mOldPosition = new Point(0, 0);
        mPosition = new Point(0, 0);
    }

    @Override
    public boolean rotate(boolean maps[][]) {
        return false;
    }

    @Override
    public boolean move(boolean maps[][], Direction direction) {
        switch (direction) {
            case DOWN:
                if (canDown(maps)) {
                    mPosition.y++;
                    return true;
                }
                break;
            case LEFT:
                if (canLeft(maps)) {
                    mPosition.x--;
                    return true;
                }
                break;
            case RIGHT:
                if (canRight(maps)) {
                    mPosition.x++;
                    return true;
                }
                break;
            default:
                break;
        }

        return false;
    }

    @Override
    public List<Point> getBoxes() {
        List<Point> boxes = new ArrayList<>(mBoxes);
        for (Point box : boxes) {
            box.x += mPosition.x;
            box.y += mPosition.y;
        }
        return boxes;
    }

    @Override
    public List<Point> getUncheckBoxes() {
        List<Point> boxes = new ArrayList<>();
        List<Point> oldboxes = new ArrayList<>();
        List<Point> newboxes = new ArrayList<>();

//        if (mPosition.equals(mOldPosition)) {
//            return boxes;
//        }

        mBoxes.forEach(box -> {
            oldboxes.add(new Point(box.x + mOldPosition.x, box.y + mOldPosition.y));
            newboxes.add(new Point(box.x + mPosition.x, box.y + mPosition.y));
        });

//        for (Point box : oldboxes) {
//            if (!newboxes.contains(box)) {
//                boxes.add(new Point(box.x + mPosition.x, box.y + mPosition.y));
//            }
//        }
//        return boxes;
        return oldboxes;
    }

    @Override
    public List<Point> getCheckBoxes() {
        List<Point> boxes = new ArrayList<>();
        List<Point> oldboxes = new ArrayList<>();
        List<Point> newboxes = new ArrayList<>();

//        if (mPosition.equals(mOldPosition)) {
//            return boxes;
//        }

        mBoxes.forEach(box -> {
            oldboxes.add(new Point(box.x + mOldPosition.x, box.y + mOldPosition.y));
            newboxes.add(new Point(box.x + mPosition.x, box.y + mPosition.y));
        });

//        for (Point box : newboxes) {
//            if (!oldboxes.contains(box)) {
//                boxes.add(new Point(box.x + mPosition.x, box.y + mPosition.y));
//            }
//        }
//        return boxes;
        return newboxes;
    }
}
