package com.robotdreams.hw05.mapper;

public interface BaseMapper <T, S>{

    public T map(S model, Object... params);
}
