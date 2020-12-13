package com.igiantpanda.androidtetris.shape;

import android.graphics.Point;

import java.util.ArrayList;
import java.util.List;

public class ShapeT extends BaseShape {

    public ShapeT() {
        super();
        List<Point> boxes = new ArrayList<>();
        boxes.add(new Point(0, 1));
        boxes.add(new Point(1, 1));
        boxes.add(new Point(2, 1));
        boxes.add(new Point(1, 2));
        init(boxes);
    }
}
