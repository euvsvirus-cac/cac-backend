package org.euvsvirus.cac.model.request;

import org.euvsvirus.cac.model.Level;

public class AddSkillRequest {

    private String name;
    private Level level;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Level getLevel() {
        return level;
    }

    public void setLevel(Level level) {
        this.level = level;
    }
}
