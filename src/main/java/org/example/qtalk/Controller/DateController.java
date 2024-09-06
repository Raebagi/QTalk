// 자격증 정보


package org.example.qtalk.Controller;

import lombok.RequiredArgsConstructor;
import org.example.qtalk.Service.DateService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/date")
@RequiredArgsConstructor

public class DateController {

    private final DateService dateService;

    @GetMapping("/info")
    public String getDateInfo() {
        return dateService.getDateInfo();
    }
}
