package rest.entity;

import lombok.*;

@ToString
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private Long id;
    private String name;
    private String lastName;
    private Byte age;
}

