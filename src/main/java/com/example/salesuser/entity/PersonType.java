package com.example.salesuser.entity;

public enum PersonType {
    EMPLOYEE("Employee"),
    CONTRACTOR("Contractor"),
    PARTNER("Partner");

    private final String label;

    PersonType(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }

    public static PersonType fromLabel(String label) {
        for (PersonType type : values()) {
            if (type.getLabel().equals(label)) {
                return type;
            }
        }
        return null;
    }
}