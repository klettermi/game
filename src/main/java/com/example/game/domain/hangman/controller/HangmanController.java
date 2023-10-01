package com.example.game.domain.hangman.controller;

import com.example.game.domain.hangman.dto.HangmanRequestDto;
import com.example.game.domain.hangman.dto.HangmanResponseDto;
import com.example.game.domain.hangman.dto.WordRequestDto;
import com.example.game.global.responseDto.ApiResponse;
import com.example.game.global.util.ResponseUtils;
import com.example.game.domain.hangman.service.HangmanService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.example.game.global.enums.SuccessCode.*;

/**
 * 행맨 게임의 Cotroller
 *
 * @author mi
 * @version 0.0, init project
 * @see None
 */
@RestController
@CrossOrigin(exposedHeaders = "*")
@RequestMapping("/api/hangman")
@RequiredArgsConstructor
public class HangmanController {
    private final HangmanService hangmanService;

    /**
     * 단어 3개 랜덤 추출
     * @return data: 랜덤 단어 3개 리스트
     */
    @GetMapping("/setting_words")
    public ApiResponse<?> setInitWords(){
        List<String> data = hangmanService.setInitWords();
        return ResponseUtils.ok(INIT_WORDS_SETTING_SUCCESS, data);
    }

    /**
     * 출제할 단어 선택
     * @return data: 게임 id
     */
    @PostMapping("/setting_word")
    public ApiResponse<?> setMission(@RequestBody WordRequestDto wordRequestDto){
        Long data = hangmanService.setMission(wordRequestDto.getWord());
        return ResponseUtils.ok(CLEAR_GAME, data);
    }

    /**
     * 출제할 단어 가져오기
     * @param id
     * @return data: 선택한 단어
     */
    @GetMapping("/setting_word/{id}")
    public ApiResponse<?> getMission(@PathVariable("id") Long id){
        String data = hangmanService.getMission(id);
        return ResponseUtils.ok(GET_WORD_SUCCESS, data);
    }

    /**
     * 행맨 게임 시작
     * @param id: 게임 id
     * @param hangmanRequestDto: win(이겼는지 여부), correct(알파벳 존재 여부), currentWord(현재 답 상태)
     * @return
     */
   @PostMapping("/game_start/{id}")
    public ApiResponse<?> startHangman(@PathVariable("id") Long id, @RequestBody HangmanRequestDto hangmanRequestDto){
        HangmanResponseDto data = hangmanService.startGame(id, hangmanRequestDto.getAlphabet().toUpperCase());
        if(data.isWin()){
            return ResponseUtils.ok(WIN, data);
        }else{
            if(data.isCorrect()){
                return ResponseUtils.ok(CORRECT_ALPHABET, data);
            }else return ResponseUtils.ok(INCORRECT_ALPHABET, data);
        }
   }
}
