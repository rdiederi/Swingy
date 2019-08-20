package model;
import lombok.Getter;
import lombok.Setter;

@Setter @Getter
public class DeathNight extends Hero {
    public DeathNight(){
        this.setBase(40,10,150,0,0,0,0);
    }
    public DeathNight(String name){
        super(name);
        this.setBase(40,10,150,0,0,0,0);
    }

    @Override
    public void attack(){
        System.out.println("SPAAAARRRRTTTTTAAAAA!!!!!");
    };
}
