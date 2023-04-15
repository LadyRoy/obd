package com.example.kurs.mapper;

import org.modelmapper.ModelMapper;

public final class CommonMapper {
    private static final ModelMapper MAPPER = new ModelMapper();

    private CommonMapper() {
    }

    public static <S, D> D map(S source, Class<D> destinationClass) {
        return MAPPER.map(source, destinationClass);
    }
}
