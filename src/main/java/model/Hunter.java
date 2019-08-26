package model;
import lombok.Getter;
import lombok.Setter;

@Setter @Getter
public class Hunter extends Hero implements Warrior{
    public Hunter(){
        this.setBase(25,15,80,0,0,0,0, "Larry", 1, 1, 1);
    }
    public Hunter(String name){
        super(name);
        this.setBase(25,15,80,0,0,0,0, "Larry", 1, 1, 1);
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
