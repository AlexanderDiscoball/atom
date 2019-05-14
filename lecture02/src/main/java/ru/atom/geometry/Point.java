package ru.atom.geometry;

/**
 * Template class for
 */
public class Point extends Geometry implements Collider {
    // fields
    // and methods

    /**
     * @param o - other object to check equality with
     * @return true if two points are equal and not null.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        // cast from Object to Point
        Point point = (Point) o;
         return (this.x == point.x && this.y == point.y);
    }
    @Override
    public boolean isColliding(Collider other) {
        if (this == other) return true;
        if (other == null) throw new NullPointerException("Коллайдер не должен быть пустой");
        if (getClass() != other.getClass()) return false;
        Geometry geometry = (Geometry) other;
        if (this.x > -1 && this.y > -1 && geometry.x > -1 && geometry.y > -1) {
            Point point1 = (Point) geometry;
            return this.equals(point1);
        }
        else if (this.x > -1 && this.y > -1 && geometry.x == -1 && geometry.y == -1) {
            return !(this.x > geometry.secondCornerX || this.y > geometry.secondCornerY);
        }
        return false;
    }
}
