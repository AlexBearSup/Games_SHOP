package org.example.repository.DAO;

import org.example.model.Game;

import java.util.List;
import java.util.Optional;

public interface GameReposytory {
    Optional<List<Game>> getAll();
}
