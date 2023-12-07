package org.education.beans.dto;

import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ReviewDTO {

    @Size(min = 10, max = 3000)
    private String review;
    private int mark;
}
