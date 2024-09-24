/*
// 자격증 정보


package org.example.qtalk.Controller;

import lombok.RequiredArgsConstructor;
import org.example.qtalk.Service.QualificationService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/qualification")
@RequiredArgsConstructor

public class QualificationController {

    private final QualificationService qualificationService;
    @GetMapping("/save")
    public String saveQualificationList() {
        return qualificationService.saveQualificationInfo();
    }
}
*/
