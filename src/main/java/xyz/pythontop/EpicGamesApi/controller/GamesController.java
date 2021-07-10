package xyz.pythontop.EpicGamesApi.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xyz.pythontop.EpicGamesApi.dto.GameDto;
import xyz.pythontop.EpicGamesApi.service.EpicService;

import java.util.List;

@RestController
@RequestMapping("/api/epic")
public class GamesController {

    private final EpicService epicService;

    public GamesController(EpicService epicService) {
        this.epicService = epicService;
    }

    @GetMapping("games")
    public List<GameDto> getGames() {
        return epicService.findAllGames();
    }

    @GetMapping("games/active")
    public List<GameDto> getActiveGames() {
        return epicService.findActiveGames();
    }

    @GetMapping("games/coming-soon")
    public List<GameDto> getAllComingSoonGames() {
        return epicService.findComingSoonGames();
    }
}
