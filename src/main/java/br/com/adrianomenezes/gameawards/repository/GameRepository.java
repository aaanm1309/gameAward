package br.com.adrianomenezes.gameawards.repository;

import br.com.adrianomenezes.gameawards.domain.model.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GameRepository extends JpaRepository<Game, Long> {
}
