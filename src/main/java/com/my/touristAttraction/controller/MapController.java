package com.my.touristAttraction.controller;

import com.my.touristAttraction.KakaoProperties;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MapController {
    private final KakaoProperties kakaoProperties;

    public MapController(KakaoProperties kakaoProperties) {
        this.kakaoProperties = kakaoProperties;
    }

    @GetMapping("/map")
    public String mapPage(Model model) {
        model.addAttribute("kakaoKey", kakaoProperties.getKey()); // 속성 이름 수정
        return "map"; // map.html 위치: src/main/resources/templates/map.html
    }
}
