package jpa.shop.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j
@RequestMapping
public class HomeController {

    @RequestMapping("/")
    public String home() {
        log.info("homeController");
        return "home";
    }
}
