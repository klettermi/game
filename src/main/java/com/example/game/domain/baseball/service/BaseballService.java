package com.example.game.domain.baseball.service;

import com.example.game.domain.baseball.entity.BaseballGame;
import com.example.game.domain.baseball.repository.BaseballRepository;
import com.example.game.global.util.RandomUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class BaseballService {
    private final BaseballRepository baseballRepository;

    public Long settingNums(int size) {
        int[] randomNums = RandomUtils.setRandomNumber(size);
        String nums = "";
        for(int x : randomNums) nums += (x + " ");

        BaseballGame baseballGame = BaseballGame.builder()
                .gameNums(nums)
                .build();

        baseballRepository.save(baseballGame);

        return baseballGame.getId();
    }
}
