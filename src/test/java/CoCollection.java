import fr.kaplone.BadCollection;

import java.time.Instant;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class CoCollection {

    List<Character> letters = new ArrayList<>(IntStream.rangeClosed(32, 126).mapToObj(i -> (char) i).collect(Collectors.toList()));
    BadCollection<String> badCollection = new BadCollection<>();
    List<String> list = new ArrayList<>();

    public CoCollection (int size){

        for (int i = 0; i < size; i++){
            String string = newString();
            badCollection.add(string);
            list.add(string);
        }
    }


    public String newString(){
        Collections.shuffle(letters);
        Random random = new Random(Instant.now().toEpochMilli() + Instant.now().getNano());
        int in = random.nextInt(94 - TestDefault.STRING_LENGTH);
        return letters.subList(in, in + TestDefault.STRING_LENGTH).stream().map(Object::toString).collect(Collectors.joining());
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
