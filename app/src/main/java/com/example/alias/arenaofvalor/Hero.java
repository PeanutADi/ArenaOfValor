package com.example.alias.arenaofvalor;

import java.io.Serializable;

public class Hero  implements Serializable {
    String image_url;
    String name;
    String alias;
    String position;
    int live;
    int attack;
    int skill;
    int difficulty;
    public Hero(String imageurl, String name, String alias, String position,  int live,int attack,int skill,int difficulty){
        this.image_url=imageurl;
        this.name=name;
        this.alias=alias;
        this.position=position;
        this.live=live;
        this.attack=attack;
        this.skill=skill;
        this.difficulty=difficulty;
    }



}
