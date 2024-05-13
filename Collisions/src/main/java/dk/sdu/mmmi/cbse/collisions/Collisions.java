package dk.sdu.mmmi.cbse.collisions;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.services.IPostEntityProcessingService;

public class Collisions implements IPostEntityProcessingService{
    @Override
    public void process(GameData gameData, World world) {
        for(Entity entity1 : world.getEntities()){
            for(Entity entity2 : world.getEntities()){
                if(entity1==entity2 || entity1.getColor()==entity2.getColor()){
                    //System.out.println("Not Collided");
                    continue;
                }
                if(this.collides(entity1,entity2)){
                    entity1.setDeath(true);
                    entity2.setDeath(true);
                    System.out.println("Collided");
                }
            }
        }
    }

    public Boolean collides(Entity entity1, Entity entity2){
        Boolean collides = false;
        double distance = Math.sqrt(((entity2.getX()-entity1.getX())*(entity2.getX()-entity1.getX()))+
                (entity2.getY()-entity1.getY())*(entity2.getY()-entity1.getY()));
        if(entity1.getRadius()>distance || entity2.getRadius()>distance){
            collides = true;
        }
        return collides;
//        double direction_x = (double) entity1.getX() - (double) entity2.getX();
//        double direction_y = (double) entity1.getY() - (double) entity2.getY();
//        double distance_of_xy = (double) Math.sqrt(direction_x*direction_x+direction_y*direction_y);
//        return distance_of_xy < (entity1.getRadius() + entity2.getRadius());
    }

}
