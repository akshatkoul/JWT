package com.wings.may24.model;

import lombok.*;

@AllArgsConstructor
@Getter
@Setter
@Data
@Builder
public class Employee {
    private Long id;
    private String name;
}
