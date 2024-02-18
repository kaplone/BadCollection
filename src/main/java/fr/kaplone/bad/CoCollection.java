package fr.kaplone.bad;

import fr.kaplone.bad.BadCollection;
import fr.kaplone.RandomGeneration.RandomPassWord;
import fr.kaplone.config.Default;

import java.util.*;

public class CoCollection {

    BadCollection<String> badCollection = new BadCollection<>();
    List<String> list = new ArrayList<>();

    public CoCollection (int size){

        for (int i = 0; i < size; i++){
            String string = RandomPassWord.newString(Default.STRING_LENGTH);
            badCollection.add(string);
            list.add(string);
        }
    }

    public BadCollection<String> getBadCollection() {
        return badCollection;
    }

    public List<String> getList() {
        return list;
    }

    public String affList(){
        return String.join("\n", list);
    }

    public String affBadCollection(){
        return String.join("\n", badCollection);
    }
}
