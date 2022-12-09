package br.com.adrianomenezes.gameawards.service.impl;

import br.com.adrianomenezes.gameawards.domain.model.Game;
import br.com.adrianomenezes.gameawards.repository.GameRepository;
import br.com.adrianomenezes.gameawards.service.GameService;
import br.com.adrianomenezes.gameawards.service.exception.BusinessException;
import br.com.adrianomenezes.gameawards.service.exception.NoContentException;
import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class GameServiceImpl implements GameService {

    @Autowired
    private GameRepository gameRepository;

    @Override
    public List<Game> findAll() {

        return gameRepository.findAll(Sort.by(Sort.Direction.DESC,"votes"));
    }

    @Override
    public Game findById(Long id) {
        Optional<Game> game = gameRepository.findById(id);
        return game.orElseThrow(() -> new NoContentException());
    }

    @Override
    public void insert(Game game) {
        if (Objects.nonNull(game.getId())){
            throw new BusinessException("O ID deve ser nulo !, não foi possivel salvar");
        }
        gameRepository.save(game);

    }

    @Override
    public void update(Long id, Game game) {
        Game gameDb = findById(id);
        if (gameDb.getId().equals(game.getId())){
            gameRepository.save(game);
        } else {
            throw new BusinessException("Os IDs para alteraçao são divergentes ou ID não existe");
        }




    }

    @Override
    public void delete(Long id) {
        Game gameDb = findById(id);
        if (gameDb.getId().equals(id)){
            gameRepository.deleteById(id);
        } else {
            throw new BusinessException("Os IDs para exclusão nao existe!");
        }


    }

    @Override
    public void vote(long id) {
        Game gameDb = findById(id);
        gameDb.setVotes(gameDb.getVotes()+1);

        gameRepository.save(gameDb);
    }
}
