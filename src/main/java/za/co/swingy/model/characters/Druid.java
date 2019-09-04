package za.co.swingy.model.characters;

import za.co.swingy.model.Hero;

public class Druid  extends Hero {
    public Druid() {
        this.setBase(20,20,100,0,1,0,0);
    }

    public Druid(String name) {
        super(name);
        this.setBase(20,20,100,0,1,0,0);
    }
}
