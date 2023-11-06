package org.education.beans;

import lombok.*;

/**
 * @author nstah
 * @version 0.5
 */
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Review {

    private int movie_id;
    private int user_id;

    private String review;
    private int mark;
    private String ownerName;
    private int socialCredit;

}
