package com.igiantpanda.androidtetris.shape;

import android.graphics.Point;

import java.util.ArrayList;
import java.util.List;

public class ShapeL extends BaseShape {

    public ShapeL() {
        super();
        List<Point> boxes = new ArrayList<>();
        boxes.add(new Point(1, 0));
        boxes.add(new Point(1, 1));
        boxes.add(new Point(1, 2));
        boxes.add(new Point(2, 2));
        init(boxes);
    }
}
