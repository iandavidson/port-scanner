package com.ian.davidson.port.scanner.validation;

public abstract class AbstractValidator<T> {

    public abstract T isValid(T value);
}
