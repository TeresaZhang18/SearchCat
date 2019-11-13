package com.example.searchcat.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class FakeDatabase {
    public static HashMap<String, Cat> cats = new HashMap<>();
    public static ArrayList<Cat> favouritecats = new ArrayList<>();
    public static HashMap<String, CatBreed> catimage = new HashMap<>();

    public static Cat getCatByID (String id) {
        return cats.get(id);
    }

    public static void saveCatsToFakedb(List<Cat> catToSave) {
        for (int i=0; i<catToSave.size(); i++) {
            Cat cat = catToSave.get(i);
            cats.put(cat.getId(), cat);
        }
    }

    public static void saveImage(String id, List<CatBreed> catBreeds) {
        for (int i=0; i<catBreeds.size(); i++) {
            CatBreed catBreed = catBreeds.get(i);
            catimage.put(id, catBreed);
        }
    }

    public static ArrayList<Cat> getallfavourite ( ) {
        return favouritecats;
    }

    public static CatBreed getimagebyid(String id){
        return catimage.get(id);
    }


}
