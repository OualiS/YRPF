package fr.yuki.yrpf.model;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

public class GameMapMarker {
    @Setter
    @Getter
    private int id = -1;
    private String type;
    private String icon;
    private ArrayList<Integer> position;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public ArrayList<Integer> getPosition() {
        return position;
    }

    public void setPosition(ArrayList<Integer> position) {
        this.position = position;
    }
}
