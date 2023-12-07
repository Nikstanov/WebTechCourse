package org.education.beans;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.context.annotation.Lazy;

import java.io.Serializable;

@Getter
@Setter
@ToString
@EqualsAndHashCode
public class ReviewID implements Serializable {
    private Movie movie;
    private User user;
}
