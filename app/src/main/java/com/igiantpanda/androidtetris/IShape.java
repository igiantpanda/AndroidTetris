package com.igiantpanda.androidtetris;

import android.graphics.Point;

import java.util.List;

public interface IShape {
    enum Direction {
        DOWN,
        LEFT,
        RIGHT
    }

    boolean rotate(boolean maps[][]);
    boolean move(boolean maps[][], Direction direction);
    List<Point> getBoxes();
    List<Point> getUncheckBoxes();
    List<Point> getCheckBoxes();
}
