package hr.fipu.knjiznica.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
public class HomeController {

    @ModelAttribute("activePage")
    public String activePage() {
        return "home";
    }

    @GetMapping("/")
    public String home() {
        return "index";
    }
}
