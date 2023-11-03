package com.example.game.domain.baseball.controller;

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
    final int SIZE = 3;
    private final BaseballService baseballService;

    @GetMapping("/setting_nums")
    public ApiResponse<?> settingNums(){
        Long data = baseballService.settingNums(SIZE);
        return ResponseUtils.ok(INIT_NUMS_SETTING_SUCCESS, data);
    }


}
