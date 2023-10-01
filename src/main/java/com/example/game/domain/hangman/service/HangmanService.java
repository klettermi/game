package com.example.game.domain.hangman.service;

import com.example.game.domain.hangman.dto.HangmanResponseDto;
import com.example.game.domain.hangman.entity.HangmanHistory;
import com.example.game.domain.hangman.enums.Word;
import com.example.game.domain.hangman.exception.AlreadySelectAlphabet;
import com.example.game.domain.hangman.exception.GameOverException;
import com.example.game.domain.hangman.exception.WordNotFoundException;
import com.example.game.domain.hangman.repository.HangmanRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static com.example.game.global.enums.ErrorCode.*;

@Service
@Transactional
@RequiredArgsConstructor
public class HangmanService {
    private final HangmanRepository hangmanRepository;
    static final int LIMIT_INCORRECT_COUNT = 8;

    /**
     * 랜덤 단어 3개 추출
     * @return List<String> 단어 배열
     */
    public List<String> setInitWords() {
        ArrayList<String> initWords = new ArrayList<>();
        for(int i = 0; i < 3; i++){
            String missionWord = String.valueOf(Word.randomWord());
            if(!initWords.contains(initWords)) initWords.add(missionWord);
        }
        return initWords;
    }

    /**
     * 단어 DB에 저장 후 게임 id 반환
     * @param word
     * @return id
     */
    public Long setMission(String word) {
        HangmanHistory hangmanHistory = HangmanHistory.builder()
                .word(word)
                .currentAnswer("*".repeat(word.length()))
                .build();

        hangmanRepository.save(hangmanHistory);

        return hangmanHistory.getId();
    }

    // 알파벳 입력 후 확인

    /**
     * 행맨 게임 시작
     * @param id : 게임 id
     * @param alphabet : 입력 알파벳
     * @return HangmanResponseDto : win(이겼는지 여부), correct(알파벳 존재 여부), currentWord(현재 답 상태)
     */
    public HangmanResponseDto startGame(Long id, String alphabet) {
        /* id로 가져오기 */
        HangmanHistory hangmanHistory = hangmanRepository.findById(id).orElseThrow(
                () -> new WordNotFoundException(WORD_NOT_FOUND)
        );

        String word = hangmanHistory.getWord();         /* 출제 단어 */
        String currentAnswer = hangmanHistory.getCurrentAnswer();       /* 현재까지 대답한 답 */
        int count = hangmanHistory.getCount();      /* 오답 횟수 */
        String newAnswer = "";      /* 새로 입력할 답 */
        boolean win = false;        /* 단어를 맞췄는지 flag */

        String[] currentAnswerAlphabet = hangmanHistory.getCurrentAnswer().toUpperCase().split("");

        /* 선택한 버튼 또 선택 시, front에서 선택 못하게 막아놓기는 했음 */
        if(currentAnswer.contains(alphabet)){
            throw new AlreadySelectAlphabet(ALREADY_SELECT_ALPHABET);
        }

        /* 단어에 포함되어 있는 알파벳 선택한 경우 */
        if(word.contains(alphabet)){
            List<Integer> idxList = findIndexes(alphabet,word);
            for(int i = 0; i < idxList.size(); i++){
                currentAnswerAlphabet[idxList.get(i)] = alphabet;
            }

            newAnswer = String.join("",currentAnswerAlphabet);

            hangmanHistory.update(newAnswer, count);

            /* 만약 단어를 맞춘 경우 */
            if(!newAnswer.contains("*")){
                win = true;
                hangmanRepository.delete(hangmanHistory);
            }

            return HangmanResponseDto.builder()
                    .win(win)
                    .correct(true)
                    .currentWord(newAnswer)
                    .build();
        }
        /* 단어 안에 알파벳이 없을 경우 */
        else{
            hangmanHistory.update(currentAnswer, count+1);      /* 오답 횟수 더하기 */
            /* 만약 오답 Limit 수를 넘었을 경우 */
            if(count+1 >= LIMIT_INCORRECT_COUNT){
                hangmanRepository.delete(hangmanHistory);
                throw new GameOverException(GAME_OVER);
            }
            return HangmanResponseDto.builder()
                    .win(win)
                    .correct(false)
                    .currentWord(currentAnswer)
                    .build();
         }
    }

    /**
     * 단어에 있는 알파벳 인덱스 리스트 얻기
     * @param alphabet
     * @param word
     * @return List<Integer> 인덱스 리스트
     */
    public static List<Integer> findIndexes(String alphabet, String word){
        List<Integer> idxList = new ArrayList<>();
        int idx = word.indexOf(alphabet);

        while(idx != -1){
            idxList.add(idx);
            idx = word.indexOf(alphabet, idx+1);
        }

        return idxList;
    }

    /**
     * 출제할 단어 조회
     * @param id
     * @return String : 출제할 단어
     */
    public String getMission(Long id) {
        HangmanHistory hangmanHistory = hangmanRepository.findById(id).orElseThrow(
                () -> new WordNotFoundException(WORD_NOT_FOUND)
        );
        return hangmanHistory.getWord();
    }

    public String restartGame(Long id) {
        HangmanHistory hangmanHistory = hangmanRepository.findById(id).orElseThrow(
                () -> new WordNotFoundException(WORD_NOT_FOUND)
        );
        hangmanHistory.update("*".repeat(hangmanHistory.getWord().length()),0);
        return hangmanHistory.getWord();
    }
}
