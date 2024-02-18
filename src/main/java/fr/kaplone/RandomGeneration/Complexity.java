package fr.kaplone.RandomGeneration;

import java.util.Arrays;

public class Complexity {

    private String value;
    private int length;
    private long position;
    private double entropy;
    private int score;

    public Complexity(String s){
        this.value = s;
        this.length = s.length();
        this.position = RandomPassWord.getPosition(s);
        this.entropy = entropy();
        this.score = score();
    }

    private double entropy(){
        return this.length *  Math.log(RandomPassWord.getRangeValues()) / Math.log(2) ;
    }

    private int score(){
        int bonus_letters = this.length * 4;
        int upper = (int) this.value.chars()
                .filter(Character::isUpperCase)
                .count();
        int bonus_upper = upper > 0 ? (this.length - upper) * 2 : 0;
        int lower = (int) this.value.chars()
                .filter(Character::isLowerCase)
                .count();
        int bonus_lower = lower > 0 ? (this.length - lower) * 2 : 0;
        int number = (int) this.value.chars()
                .filter(Character::isDigit)
                .count();
        int bonus_number = number > 0 ? (this.length - number) * 4 : 0;
        int symbol = (int) (this.length - this.value.chars()
                .filter(Character::isLetterOrDigit)
                .count());
        int bonus_symbol = symbol * 6;
        int bonus_symbolOrDigit = (int)(this.length - this.value.chars()
                .filter(Character::isLetter)
                .count()) * 2;

        int requirement = (bonus_upper > 0 ? 1 : 0) +
                (bonus_lower > 0 ? 1 : 0) +
                (bonus_number > 0 ? 1 : 0) +
                (bonus_symbol > 0 ? 1 : 0) +
                (bonus_symbolOrDigit > 0 ? 1 : 0);
        int bonus_requirement = requirement >= 4 ? requirement * 2 : 0;

//        System.out.println(bonus_letters + " " +
//                bonus_upper + " " +
//                bonus_lower + " " +
//                bonus_number + " " +
//                bonus_symbol + " " +
//                bonus_symbolOrDigit + " " +
//                bonus_requirement);

        int minus_letter_only = bonus_symbolOrDigit == 0 ? - this.length : 0;
        int minus_numbers_only = number == this.length ? - this.length : 0;
        int minus_repeated = (this.length - (int) Arrays.stream(this.value.split("")).distinct().count()) * -5;
        int minus_consecutive = 0;
        Type type = Type.NONE;
        for (char c : this.value.toCharArray()){
            if (Character.isUpperCase(c)){
                if (type == Type.UPPER){
                    minus_consecutive -= 2;
                }
                type = Type.UPPER;
            }
            if (Character.isLowerCase(c)){
                if (type == Type.LOWER){
                    minus_consecutive -= 2;
                }
                type = Type.LOWER;
            }
            if (Character.isDigit(c)){
                if (type == Type.NUMBER){
                    minus_consecutive -= 2;
                }
                type = Type.NUMBER;
            }
            else {
                if (type == Type.SYMBOL){
                    minus_consecutive -= 2;
                }
                type = Type.SYMBOL;
            }
        }

//        System.out.println(minus_letter_only + " " +
//                minus_numbers_only + " " +
//                minus_repeated + " " +
//                minus_consecutive);

        return (int) entropy +
                bonus_letters +
                bonus_upper +
                bonus_lower +
                bonus_number +
                bonus_symbol +
                bonus_symbolOrDigit +
                bonus_requirement +
                minus_letter_only +
                minus_numbers_only +
                minus_repeated +
                minus_consecutive;
    }

    @Override
    public String toString() {
        return "Complexity{" +
                "value='" + value + '\'' +
                ", length=" + length +
                ", position=" + position +
                ", entropy=" + entropy +
                ", score=" + score +
                '}';
    }

    public String getValue() {
        return value;
    }

    public int getLength() {
        return length;
    }

    public long getPosition() {
        return position;
    }

    public double getEntropy() {
        return entropy;
    }

    public int getScore() {
        return score;
    }

    enum Type {UPPER, LOWER, NUMBER, SYMBOL, NONE;}
}
