package com.hpedrorodrigues.imagesearch.api.network.services.cse;

public enum CSESearchType {

    IMAGE("image");

    private final String value;

    CSESearchType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}