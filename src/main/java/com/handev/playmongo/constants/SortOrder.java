package com.handev.playmongo.constants;

import lombok.Getter;

@Getter
public enum SortOrder {
    ASC("asc"),
    DESC("desc");

    private final String value;

    SortOrder(String value) {
        this.value = value;
    }

}
