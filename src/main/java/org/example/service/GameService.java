package org.example.service;
import org.example.model.Game;
import org.example.enums.ServiceMsg;
import org.example.repository.GameRepositoryImpl;
import java.util.Collections;
import java.util.List;

public class GameService {
    private final GameRepositoryImpl gameRepository;
    public GameService(GameRepositoryImpl gameRepository) {
        this.gameRepository = gameRepository;
    }
    public List<Game> getAll() {
        List<Game> games = gameRepository.getAll()
                .orElse(Collections.emptyList());

        if (games.isEmpty()) {
            System.out.println(ServiceMsg.LIST_EMPTY.getDescription());
        }
        return games;

    }
    public Game getOne (int choice) {
        if (choice >= 1 && choice <= getAll().size()) {
            Game selected = getAll().get(choice - 1);
            System.out.println("ID = " + selected.getId() + ". " + selected.getName() + " -  cost: "
                    + selected.getCost() + "$");
            return selected;
        } else {
            System.out.println(ServiceMsg.INCORRECT.getDescription());
            return new Game();
        }
    }

}
