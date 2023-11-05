package com.example.game.domain.baseball.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class BaseballUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "baseball_user_id")
    private Long id;

    @Column
    private String nums;

    @Column
    @Builder.Default
    private int count  = 0;

    @ManyToOne
    @JoinColumn(name = "baseball_id")
    BaseballGame baseballGame;

    public void plusCount(){
        this.count++;
    }

}
