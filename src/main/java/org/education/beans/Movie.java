package org.education.beans;

import lombok.*;

import java.util.List;

/**
 * @author nstah
 * @version 0.5
 */
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Movie {
    private int id;
    private String name;
    private String author;
    private String description;
    private String shortDescription;
    private double averageMark;
    private List<Review> reviewList;
}
