package com.crio.jukebox.entities;

public enum Genre{
    POP("Pop"),
    ROCK("Rock"), 
    JAZZ("Jazz"), 
    ELECTRONIC_DANCE_MUSIC("Electronic Dance Music"), 
    HIPHOP("Hip-Hop");

    private String displayName; 

    Genre(String displayName){
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    @Override 
    public String toString() { return displayName; }
} 