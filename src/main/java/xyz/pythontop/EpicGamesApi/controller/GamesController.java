package xyz.pythontop.EpicGamesApi.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xyz.pythontop.EpicGamesApi.dto.GameDto;
import xyz.pythontop.EpicGamesApi.service.EpicService;

import java.util.List;

@RestController
@RequestMapping("/games")
public class GamesController {

    private final EpicService epicService;

    public GamesController(EpicService epicService) {
        this.epicService = epicService;
    }

    @GetMapping
    public List<GameDto> getGames() {
        return epicService.findGames();
    }

    @GetMapping("all")
    public List<GameDto> getAllGames() {
        return epicService.findAllGames();
    }
}
