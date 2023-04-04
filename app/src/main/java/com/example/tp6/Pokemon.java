package com.example.tp6;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 *  get Pokemons from json data: https://raw.githubusercontent.com/fanzeyi/pokemon.json/17d33dc111ffcc12b016d6485152aa3b1939c214/pokedex.json
 *
 *  --> let's use jackson libbrairy!
 *          - no constructor in this Pokemon class because 'jackson' librairy is used to parse json -> Pokemon
 *          - tuto to use jackson lib: <a href="https://stackoverflow.com/questions/60750796/how-to-convert-this-json-to-object-in-java-android">...</a>
 *          - add in Gradle
 *              implementation 'com.squareup.retrofit2:converter-jackson:2.7.2'
 *              implementation 'com.fasterxml.jackson.core:jackson-databind:2.10.3'
 *              implementation 'com.fasterxml.jackson.core:jackson-core:2.10.3'
 *              implementation 'com.fasterxml.jackson.core:jackson-annotations:2.10.3'
 *
 *  there is no picture in data json but there are some at
 *          - sprites : <a href="https://github.com/fanzeyi/pokemon.json/tree/master/sprites">...</a>
 *          - images : <a href="https://github.com/fanzeyi/pokemon.json/tree/master/images">...</a>
 *   --> let's use Picaso libbrairy to inflate pictures
 *          add in Gradle implementation 'com.squareup.picasso:picasso:2.71828'
 *
 * @author Frédéric RALLO - March 2023
 * @author
 */
public class Pokemon implements Comparable {
    public static String language;
    private static ArrayList<Pokemon> completeList;
    private int id;
    private Map<String, String> name; //depends of settings language
    private final List<Types> type = new ArrayList<>();
    private Map<String, Integer> base;  //json name is base
    private String pictureURL;

    public Pokemon () {
    }

    //TODO some methods


    /**
     * the best pokemon are those with the highest rank value
     **/
    public Integer getRank(){
        return 4*base.get("Speed") + 3*base.get("Attack") + 2*base.get("Defense")  + base.get("HP");
    }

    public String getName() {
        return null; //TODO: return name.get(MainActivity.language);
    }


    @Override
    public String toString() {
        return "Pokemon{ id=" + id + ", name=" + getName() + ", type=" + type + ", features=" + base + '}';
    }

    /**
     * change speed of all NORMAL Type Pokemon
     * @param boost
     */
    public static void boost(int boost) {
        completeList.forEach(pokemon -> {
            if(pokemon.type.contains(Types.NORMAL)) {
                pokemon.base.put(Stats.SPEED.toString(), pokemon.base.get(Stats.SPEED.toString()) + boost);
            }
        });
    }

    @Override
    public int compareTo(Object object) {
        if(!(object instanceof Pokemon)){
            return -2;
        }
        Pokemon pokemon = (Pokemon) object;
        int scoreP1 = this.getRank();
        int scoreP2 = pokemon.getRank();
        if(scoreP1-scoreP2 >0){
            return 1;
        }
        if(scoreP1-scoreP2 ==0){
            return 0;
        }
        else{
            return -1;
        }
    }

    public static ArrayList<Pokemon> getCompleteList() {
        return completeList;
    }
}



enum Stats {
    HP(0),
    ATTACK(0),
    DEFENSE(0),
    SP_ATTACK(0),
    SP_DEFENSE(0),
    SPEED(0);
    private int value;

    Stats(int value) {
        this.value = value;
    }
    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}

enum Types{
    NORMAL,
    FIGHTING,
    FLYING,
    POISON,
    GROUND,
    ROCK,
    BUG,
    GHOST,
    STEEL,
    FIRE,
    WATER,
    GRASS,
    ELECTRIC,
    PSYCHIC,
    ICE,
    DRAGON,
    DARK,
    FAIRY
}

