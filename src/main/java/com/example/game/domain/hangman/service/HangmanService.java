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

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.example.game.global.enums.ErrorCode.*;

@Service
@Transactional
@RequiredArgsConstructor
public class HangmanService {
    private final HangmanRepository hangmanRepository;
    static final int LIMIT_INCORRECT_COUNT = 8;

    // 영어 단어 랜덤으로 세 개 띄우기
    public List<String> setInitWords() {
        ArrayList<String> initWords = new ArrayList<>();
        for(int i = 0; i < 3; i++){
            String missionWord = String.valueOf(Word.randomWord());
            if(!initWords.contains(initWords)) initWords.add(missionWord);
        }
        return initWords;
    }

    // 단어 DB에 저장
    public Long setMission(String word) {
        HangmanHistory hangmanHistory = HangmanHistory.builder()
                .word(word)
                .currentAnswer("*".repeat(word.length()))
                .build();
        System.out.println(hangmanHistory.getCurrentAnswer());
        hangmanRepository.save(hangmanHistory);

        return hangmanHistory.getId();
    }

    // 알파벳 입력 후 확인
    public HangmanResponseDto startGame(Long id, String alphabet) {
        HangmanHistory hangmanHistory = hangmanRepository.findById(id).orElseThrow(
                () -> new WordNotFoundException(WORD_NOT_FOUND)
        );

        String word = hangmanHistory.getWord();
        String currentAnswer = hangmanHistory.getCurrentAnswer();
        int count = hangmanHistory.getCount();
        String newAnswer = "";
        boolean win = false;

        String[] currentAnswerAlphabet = hangmanHistory.getCurrentAnswer().toUpperCase().split("");

        if(currentAnswer.contains(alphabet)){
            throw new AlreadySelectAlphabet(ALREADY_SELECT_ALPHABET);
        }

        // 알맞은 알파벳을 선택했을 때
        if(word.contains(alphabet)){
            List<Integer> idxList = findIndexes(alphabet,word);
            for(int i = 0; i < idxList.size(); i++){
                currentAnswerAlphabet[idxList.get(i)] = alphabet;
            }

            newAnswer = String.join("",currentAnswerAlphabet);

            hangmanHistory.update(newAnswer, count+1);

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
        // 알맞지 않은 알파벳을 선택했을 때
        else{
            hangmanHistory.update(currentAnswer, count+1);
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

    // 단어에 있는 알파벳 인덱스 리스트 얻기
    public static List<Integer> findIndexes(String alphabet, String word){
        List<Integer> idxList = new ArrayList<>();
        int idx = word.indexOf(alphabet);

        while(idx != -1){
            idxList.add(idx);
            idx = word.indexOf(alphabet, idx+1);
        }

        return idxList;
    }

    public String getMission(Long id) {
        HangmanHistory hangmanHistory = hangmanRepository.findById(id).orElseThrow(
                () -> new WordNotFoundException(WORD_NOT_FOUND)
        );
        return hangmanHistory.getWord();
    }
}
