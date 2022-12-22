package jpa.shop.rest.dto;

import lombok.Data;

@Data
public class SelectResult<T> {
    private int count;

    private T data;
}
