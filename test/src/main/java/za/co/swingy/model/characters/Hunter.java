package za.co.swingy.model.characters;

import za.co.swingy.model.Hero;

public class Hunter extends Hero {
    public Hunter() {
        this.setBase(25,15,80,0,1,0,0);
    }

    public Hunter(String name) {
        super(name);
        this.setBase(25,15,80,0,1,0,0);
    }
}
