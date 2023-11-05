package com.example.game.domain.baseball.controller;

import com.example.game.domain.baseball.dto.BaseballRequestDto;
import com.example.game.domain.baseball.dto.BaseballResponseDto;
import com.example.game.domain.baseball.enums.WinEnum;
import com.example.game.domain.baseball.service.BaseballService;
import com.example.game.global.responseDto.ApiResponse;
import com.example.game.global.util.ResponseUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.example.game.global.enums.SuccessCode.*;

@Controller
@CrossOrigin(exposedHeaders = "*")
@ResponseBody
@RequestMapping("/api/baseball")
@RequiredArgsConstructor
public class BaseballController {
    private final int SIZE = 3;
    private final BaseballService baseballService;

    @GetMapping("/setting_nums")
    public ApiResponse<?> settingNums(){
        Long data = baseballService.settingNums(SIZE);
        return ResponseUtils.ok(INIT_NUMS_SETTING_SUCCESS, data);
    }

    @PostMapping("/start_baseball/{id}")
    public ApiResponse<?> startBaseBall(@PathVariable("id") Long id, @RequestBody BaseballRequestDto baseballRequestDto){
        BaseballResponseDto data = baseballService.startGame(id, SIZE, baseballRequestDto);
        if(data.getWin() == WinEnum.WIN) return ResponseUtils.ok(WIN, data);
        else return ResponseUtils.ok(CONTINUE_GAME, data);
    }
}
