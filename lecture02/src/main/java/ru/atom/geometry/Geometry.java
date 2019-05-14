package ru.atom.geometry;

/**
 *  ^ Y
 *  |
 *  |
 *  |
 *  |          X
 *  .---------->
 */

public class Geometry implements Collider {

    public int firstCornerX = -1;
    public int firstCornerY = -1;
    public int secondCornerX = -1;;
    public int secondCornerY = -1;;
    public int x = -1;;
    public int y = -1;;


    /**
     * Bar is a rectangle, which borders are parallel to coordinate axis
     * Like selection bar in desktop, this bar is defined by two opposite corners
     * Bar is not oriented
     * (It is not relevant, which opposite corners you choose to define bar)
     * @return new Bar
     */
    public static Collider createBar(int firstCornerX, int firstCornerY, int secondCornerX, int secondCornerY) {
        if (firstCornerX < 0 || firstCornerY < 0||secondCornerX<0||secondCornerY<0)throw new IllegalArgumentException("Не может быть меньше нуля");
        Geometry geometry = new Geometry();
        geometry.firstCornerX = firstCornerX;
        geometry.firstCornerY = firstCornerY;
        geometry.secondCornerX = secondCornerX;
        geometry.secondCornerY = secondCornerY;
        return geometry;
    }

    /**
     * 2D point
     * @return new Point
     */
    public static Collider createPoint(int x, int y) {
        if (x<0 || y<0)throw new IllegalArgumentException("Не может быть меньше нуля");
        Point point = new Point();
        point.x = x;
        point.y = y;
        return point;

    }

    @Override
    public boolean isColliding(Collider other) {
        if(other == null)throw new NullPointerException("Коллайдер не должен быть пустой");
        if (!(other instanceof Geometry)) return false;
        Geometry geometry = (Geometry) other;

        if(this.x == -1 && this.y == -1 && geometry.x > -1 && geometry.y > -1){
            return !(this.secondCornerX < geometry.x||this.secondCornerY < geometry.y);
        }
        else if(this.x == -1 && this.y == -1 && geometry.x == -1 && geometry.y == -1){
            return !(this.firstCornerY > geometry.secondCornerY || this.secondCornerY < geometry.firstCornerY
                    || this.firstCornerX > geometry.secondCornerX || this.secondCornerX < geometry.firstCornerX);
        }
        return false;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Geometry geometry = (Geometry) o;
        return ((this.firstCornerX + this.secondCornerX) == (geometry.firstCornerX + geometry.secondCornerX)
                && (this.secondCornerY + this.firstCornerY) == (geometry.secondCornerY + geometry.firstCornerY));
    }
}
