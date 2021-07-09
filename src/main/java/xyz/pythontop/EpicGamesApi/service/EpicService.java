package xyz.pythontop.EpicGamesApi.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import xyz.pythontop.EpicGamesApi.dto.GameDto;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EpicService {

    private final ObjectMapper mapper;
    private static final String FREE_GAMES_URL = "https://store-site-backend-static.ak.epicgames.com/freeGamesPromotions/";
    private static final String GAME_URL = "https://www.epicgames.com/store/product/";
    private static final Logger LOG = LoggerFactory.getLogger(EpicService.class.getName());
    private static final String DATE_PATTERN = "yyyy-MM-dd'T'HH:mm:ss.SSSVV";

    public EpicService() {
        mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
    }

    public List<GameDto> findGames() {
        List<GameDto> games = findAllGames();
        LOG.info("Games: {}", games);
        return games
                .stream()
                .filter(game -> game.getStartDate().isAfter(LocalDateTime.now().minusDays(7))
                        && game.getStartDate().isBefore(LocalDateTime.now())
                )
                .collect(Collectors.toList());
    }

    public List<GameDto> findAllGames() {
        List<GameDto> games = new ArrayList<>();
        try {
            JsonNode elements = mapper
                    .readTree(new URL(FREE_GAMES_URL))
                    .findValue("elements");
            LOG.info("Json games: {}", elements);
            for (JsonNode gameNode : elements) {
                GameDto game = parseGame(gameNode);
                if (game != null) {
                    games.add(game);
                }
            }
            return games;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return games;
    }

    private LocalDateTime parseDate(String date) {
        return LocalDateTime.parse(date, DateTimeFormatter.ofPattern(DATE_PATTERN));
    }

    private String createUrl(String productSlug) {
        return GAME_URL + productSlug;
    }

    private GameDto parseGame(JsonNode gameNode) throws JsonProcessingException {
        if (gameNode.hasNonNull("promotions")) {
            GameDto game = mapper.readValue(gameNode.toString(), GameDto.class);
            game.setUrl(createUrl(
                    gameNode.findValue("productSlug").asText()
            ));
            game.setEndDate(parseDate(
                    gameNode.findValue("endDate").asText()
            ));
            game.setStartDate(parseDate(
                    gameNode.findValue("startDate").asText()
            ));
            return game;
        }
        return null;
    }
}
