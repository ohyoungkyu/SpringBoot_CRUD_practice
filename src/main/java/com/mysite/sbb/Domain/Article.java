package com.mysite.sbb.Domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Setter
@Getter
public class Article {
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    public Long id;

    public LocalDateTime regDate;
    public LocalDateTime updateDate;

    public String title;
    public String body;

    public Long userId;

    @ManyToOne
    private User user;
}
