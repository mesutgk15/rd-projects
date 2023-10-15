package com.robotdreams.schoolservice.mapper;

public interface BaseMapper<T, S> {
    T map(S model, Object... params);
}
