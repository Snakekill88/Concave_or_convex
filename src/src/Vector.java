public class Vector extends Point{

    public Vector(double x, double y) {
        super(x, y);
    }
    public double dotProduct(Vector vector){
        return this.x*vector.x+this.y*vector.y;
    }
    public double length(){
        return Math.sqrt(this.x*this.x+this.y*this.y);
    }
}
