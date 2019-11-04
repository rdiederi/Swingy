package za.co.swingy.model.characters;

import za.co.swingy.model.Hero;

public class DeathNight extends Hero {
    public DeathNight() {
        this.setBase(40,10,150,0,1,0,0);
    }

    public DeathNight(String name) {
        super(name);
        this.setBase(40,10,150,0,1,0,0);
    }
}
