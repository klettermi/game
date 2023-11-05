package com.example.game.domain.baseball.service;

import com.example.game.domain.baseball.dto.BaseballRequestDto;
import com.example.game.domain.baseball.dto.BaseballResponseDto;
import com.example.game.domain.baseball.entity.BaseballGame;
import com.example.game.domain.baseball.entity.BaseballUser;
import com.example.game.global.enums.GameEnum;
import com.example.game.domain.baseball.exception.BaseballGameNotFoundException;
import com.example.game.domain.baseball.repository.BaseballRepository;
import com.example.game.domain.baseball.repository.BaseballUserRepository;
import com.example.game.domain.hangman.exception.GameOverException;
import com.example.game.global.util.ParserUtils;
import com.example.game.global.util.RandomUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.example.game.global.enums.ErrorCode.*;

@Service
@Transactional
@RequiredArgsConstructor
public class BaseballService {
    private final BaseballRepository baseballRepository;
    private final BaseballUserRepository baseballUserRepository;

    public Long settingNums(int size) {
        int[] randomNums = RandomUtils.setRandomNumber(size);
        String nums = "";
        for(int x : randomNums) nums += (x);

        BaseballGame baseballGame = BaseballGame.builder()
                .gameNums(nums)
                .build();

        baseballRepository.save(baseballGame);

        return baseballGame.getId();
    }

    public BaseballResponseDto startGame(Long id, int size, BaseballRequestDto baseballRequestDto) {
        /* 기존 게임 히스토리 가져오기 */
        BaseballGame baseballGame = baseballRepository.findById(id).orElseThrow(
                () -> new BaseballGameNotFoundException(BASEBALL_GAME_NOT_FOUND)
        );

        /* 사용자 가져오기 */
        BaseballUser user = baseballUserRepository.findAllByBaseballGame(baseballGame).orElse(
                 BaseballUser.builder()
                        .baseballGame(baseballGame)
                        .build()
        );

        user.plusCount();

        int[] computerNums = ParserUtils.parserUserInput(baseballGame.getGameNums(), size); /* 컴퓨터 랜덤 숫자 배열 */
        int[] userNums = ParserUtils.parserUserInput(baseballRequestDto.getUserNums(), size); /* 사용자 랜덤 숫자 배열 */


        /* 승부 판단 */
        int ball = checkBallAndStrike(userNums, computerNums, size)[0];
        int strike = checkBallAndStrike(userNums, computerNums, size)[1];

        baseballGame.update(strike, ball, baseballGame.getGameNums());
        baseballUserRepository.save(user);
        GameEnum winFlag = checkWin(strike, user.getCount());

        return BaseballResponseDto.builder()
                .ball(ball)
                .strike(strike)
                .count(user.getCount())
                .win(winFlag)
                .build();
    }

    private int[] checkBallAndStrike(int[] user, int[] computer, int size){
        int ball = 0, strike = 0;
        int[] cnt = new int[size];
        for(int i = 0; i < size; i++){
            if(user[i] == computer[i]) cnt[i]++;
            for(int j = 0; j < size; j++){
                if(user[i] == computer[j]) cnt[i]++;
            }
        }
        for(int i : cnt){
            if(i == 2) strike++;
            else if(i == 1) ball++;
        }
        return new int[]{ball, strike};
    }

    private GameEnum checkWin(int strike, int count){
        if(count > 9) throw new GameOverException(GAME_OVER);
        else{
            if(strike == 3) return GameEnum.WIN;
            else return GameEnum.CONTINUE;
        }
    }
}
