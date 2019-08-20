package model;
import lombok.Getter;
import lombok.Setter;

@Setter @Getter
public class Hunter extends Hero{
    public Hunter(){
        this.setBase(25,15,80,0,0,0,0);
    }
    public Hunter(String name){
        super(name);
        this.setBase(25,15,80,0,0,0,0);
    }

    @Override
    public void attack(){
        System.out.println("SPAAAARRRRTTTTTAAAAA!!!!!");
    };
}
