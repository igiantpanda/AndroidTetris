package com.igiantpanda.androidtetris.shape;

import android.graphics.Point;

import java.util.ArrayList;
import java.util.List;

public class ShapeI extends BaseShape {

    public ShapeI() {
        super();
        List<Point> boxes = new ArrayList<>();
        boxes.add(new Point(1, 0));
        boxes.add(new Point(1, 1));
        boxes.add(new Point(1, 2));
        boxes.add(new Point(1, 3));
        init(boxes);
    }
}
