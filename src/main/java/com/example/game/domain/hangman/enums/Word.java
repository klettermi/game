package com.example.game.domain.hangman.enums;

import java.util.Random;

public enum Word {
    DOG, CAT, HORSE, COW, PIG, SHEEP, CHICKEN, FISH, BIRD,
    BREAD, RICE, PASTA, MEAT, VEGETABLES, FRUIT, SWEETS, HAMBURGER,
    HEAD, FACE, NECK, SHOULDER, ARM, HAND, CHEST, STOMACH, LEG,
    KOREA, CHINA, FRANCE, GERMANY, ITALY, SPAIN, CANADA, MEXICO, INDIA, ARGENTINA;

    // 랜덤하게 단어 3개 고르기
    private static final Random PRNG = new Random();

    public static Word randomWord(){
        Word[] words = values();
        return words[PRNG.nextInt(words.length)];
    }
}
