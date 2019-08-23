package model;
import lombok.Getter;
import lombok.Setter;

@Setter @Getter
public class DeathNight extends Hero implements Warrior{
    public DeathNight(){
        this.setBase(40,10,150,0,0,0,0, "Max", "Sword", "", "");
    }
    public DeathNight(String name){
        super(name);
        this.setBase(40,10,150,0,0,0,0, "Max", "Sword", "", "");
    }

    @Override
    public void attack(){
        System.out.println("SPAAAARRRRTTTTTAAAAA!!!!!");
    };
    @Override
    public void escape() {
        System.out.println("this bitch ran");
    }
}
