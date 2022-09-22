package com.hznu.designMode.factory;

/**
 * @author LIN
 * @date 2022/3/9 23:33
 */
public class ShapeFactory {
    public Shape getShape(String type) {
        if (type == null) {
            return null;
        }
        if ("CIRCLE".equalsIgnoreCase(type)) {
            return new Circle();
        } else if ("RECTANGLE".equalsIgnoreCase(type)) {
            return new Rectangle();
        } else if ("SQUARE".equalsIgnoreCase(type)) {
            return new Square();
        }
        return null;
    }
}
