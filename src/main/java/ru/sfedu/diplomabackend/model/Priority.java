package ru.sfedu.diplomabackend.model;

public enum Priority {

    PRIORITY_1("HIGH"),
    PRIORITY_2("MEDIUM"),
    PRIORITY_3("LOW");

    private String pr;

    private EnItemType(String code) {
        this.code=code;
    }

    @JsonCreator
    public static EnItemType decode(final String code) {
        return Stream.of(EnItemType.values()).filter(targetEnum -> targetEnum.code.equals(code)).findFirst().orElse(null);
    }

    @JsonValue
    public String getCode() {
        return code;
    }


}
