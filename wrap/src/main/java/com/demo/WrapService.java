package com.demo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SomeService {

    private String before;
    private String after;

    public String wrap(String word) {
        return before + word + after;
    }
}
