package org.education.beans;

import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Review {

    private int movieId;
    private int userId;

    private String review;
    private int mark;
}
