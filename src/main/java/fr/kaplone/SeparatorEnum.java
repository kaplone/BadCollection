package fr.kaplone;

public enum SeparatorEnum {
    NULL(0),
    START_OF_HEADING(1),
    START_OF_TEXT(2),
    END_OF_TEXT(3),
    END_OF_TRANSMISSION(4),
    INQUIRY(5),
    ACKNOWLEDGE(6),
    BELL(7);

    private int value;

    SeparatorEnum(int value) {
        this.value = value;
    }

    public char getValue(){
        return (char) value;
    }
}
