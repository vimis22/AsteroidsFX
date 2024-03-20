package dk.sdu.mmmi.cbse.common.data;

import javafx.scene.paint.Paint;

import java.io.Serializable;
import java.util.UUID;

public class Entity implements Serializable {

    private final UUID ID = UUID.randomUUID();

    private double size;
    
    private double[] polygonCoordinates;
    private double x;
    private double y;
    private double rotation;
    private double objectiveX;
    private double objectiveY;

    private Paint color;

    public String getID() {
        return ID.toString();
    }


    public void setPolygonCoordinates(double... coordinates ) {
        this.polygonCoordinates = coordinates;
    }

    public double[] getPolygonCoordinates() {
        return polygonCoordinates;
    }
       

    public void setX(double x) {
        this.x =x;
    }

    public double getX() {
        return x;
    }

    
    public void setY(double y) {
        this.y = y;
    }

    public double getY() {
        return y;
    }

    public void setRotation(double rotation) {
        this.rotation = rotation;
    }

    public double getRotation() {
        return rotation;
    }

    public double getobjectiveX(){
        return objectiveX;
    }

    public double getobjectiveY(){
        return objectiveY;
    }

    public void setObjectiveX(double objectiveX){
        this.objectiveX = objectiveX;
    }

    public void setObjectiveY(double objectiveY){
        this.objectiveY = objectiveY;
    }

    public void setColor(Paint color){
        this.color = color;
    }

    public Paint getColor(){
        return color;
    }

    public void setSize(Double size){
        this.size = size;
    }

    public Double getSize(){
        return size;
    }

}
