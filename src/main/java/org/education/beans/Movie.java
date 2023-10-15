package org.education.beans;

import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Movie {
    private String name;
    private String author;
    private String description;
    private String shortDescription;
    private double averageMark;
}
