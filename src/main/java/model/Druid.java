package model;
import lombok.Getter;
import lombok.Setter;

@Setter @Getter
public class Druid  extends Hero implements Warrior{

    public Druid(){
        this.setBase(20,20,100,0,0,0,0, "Evan", "Sword", "", "");
    }

    public Druid(String name){
        super(name);
        this.setBase(20,20,100,0,0,0,0, name, "","","");
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
