package org.education.Lab1.task9;

public class Ball {
    public static final String COLOR_BLUE = "COLOR_BLUE";
    public static final String COLOR_GRAY = "COLOR_GRAY";
    public static final String COLOR_YELLOW = "COLOR_YELLOW";

    private final String color;
    private final int weight;

    public Ball(String color, int weight) {
        this.color = color;
        this.weight = weight;
    }

    public String getColor() {
        return color;
    }

    public int getWeight() {
        return weight;
    }
}
