package com.handev.playmongo.config;

import com.handev.playmongo.constants.SortOrder;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class SortOrderConverter implements Converter<String, SortOrder> {
    @Override
    public SortOrder convert(String source) {
        return switch (source) {
            case "asc" -> SortOrder.ASC;
            case "desc" -> SortOrder.DESC;
            default -> null;
        };
    }
}
