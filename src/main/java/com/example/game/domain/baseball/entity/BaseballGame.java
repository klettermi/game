package com.example.game.domain.baseball.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class BaseballGame {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "baseball_id")
    private Long id;

    @Column
    @Builder.Default
    private int strikeCnt = 0;

    @Column
    @Builder.Default
    private int ballCnt = 0;

    @Column
    private String gameNums;

}
