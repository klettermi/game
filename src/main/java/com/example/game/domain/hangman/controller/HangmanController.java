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

@RestController
@CrossOrigin(exposedHeaders = "*")
@RequestMapping("/api/hangman")
@RequiredArgsConstructor
public class HangmanController {
    private final HangmanService hangmanService;

    // 영어 단어 랜덤으로 세 개 띄우기
    @GetMapping("/setting_words")
    public ApiResponse<?> setInitWords(){
        List<String> data = hangmanService.setInitWords();
        return ResponseUtils.ok(INIT_WORDS_SETTING_SUCCESS, data);
    }

    // 선택한 단어 셋팅
    @PostMapping("/setting_word")
    public ApiResponse<?> setMission(@RequestBody WordRequestDto wordRequestDto){
        Long data = hangmanService.setMission(wordRequestDto.getWord());
        return ResponseUtils.ok(CLEAR_GAME, data);
    }

    @GetMapping("/setting_word/{id}")
    public ApiResponse<?> getMission(@PathVariable("id") Long id){
        String data = hangmanService.getMission(id);
        return ResponseUtils.ok(GET_WORD_SUCCESS, data);
    }
    // 게임 하기
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
