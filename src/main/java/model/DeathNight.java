package model;
import lombok.Getter;
import lombok.Setter;
import org.json.*;

@Setter @Getter
public class DeathNight extends Hero implements Warrior{
    public DeathNight(){  this.setBase(40,10,150,0,0,0,0, "Max", 1, 1, 1);    }
    public DeathNight(String name){
        super(name);
        this.setBase(40,10,150,0,0,0,0, "Max", 1, 1, 1);
    }

    @Override
    public void attack(){
        System.out.println("SPAAAARRRRTTTTTAAAAA!!!!!");
    };
    @Override
    public void escape() {
        System.out.println("this bitch ran");
    }
    @Override
    public JSONObject getStats() {
        JSONObject obj = new JSONObject();

        obj.put("attack", this.attack);

        this.attack();
    }
}
