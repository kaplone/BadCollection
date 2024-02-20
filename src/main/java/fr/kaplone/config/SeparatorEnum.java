package fr.kaplone.config;

public enum SeparatorEnum {
    OVER(-1), // 65535
    NULL(0),
    START_OF_HEADING(1),
    START_OF_TEXT(2),
    END_OF_TEXT(3),
    END_OF_TRANSMISSION(4),
    INQUIRY(5),
    ACKNOWLEDGE(6),
    BELL(77);

    private int value;

    SeparatorEnum(int value) {
        this.value = value;
    }

    public char getValue(){
        return (char) value;
    }
}
