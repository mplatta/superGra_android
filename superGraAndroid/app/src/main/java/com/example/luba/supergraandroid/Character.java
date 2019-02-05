package com.example.luba.supergraandroid;

import java.io.Serializable;
import java.util.ArrayList;

public class Character implements Serializable {
    private String id;
    private Integer characterId;
    private String name;
    private String description;
    private String type;
    private ArrayList<Stat> stats;
    private ArrayList<String> equipment;

    public String getAndId() {
        return id;
    }

    public void setAndId(String id) {
        this.id = id;
    }

    public Integer getCharacterId() {
        return characterId;
    }

    public void setCharacterId(Integer characterId) {
        this.characterId = characterId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public ArrayList<Stat> getStats() {
        return stats;
    }

    public void setStats(ArrayList<Stat> stats) {
        this.stats = stats;
    }

    public ArrayList<String> getEquipment() {
        return equipment;
    }

    public void setEquipment(ArrayList<String> equipment) {
        this.equipment = equipment;
    }

    public Character() {
        this.stats = new ArrayList<Stat>();
        this.equipment = new ArrayList<String>();
    }
}
