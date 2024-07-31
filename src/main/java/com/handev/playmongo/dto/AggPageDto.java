package com.handev.playmongo.dto;

import lombok.Data;

import java.util.List;

@Data
public class AggPageDto<T> {
    private int total;
    private List<T> content;
}
