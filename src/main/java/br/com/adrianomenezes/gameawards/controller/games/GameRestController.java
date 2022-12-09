package br.com.adrianomenezes.gameawards.controller.games;

import br.com.adrianomenezes.gameawards.controller.BaseRestController;
import br.com.adrianomenezes.gameawards.domain.model.Game;
import br.com.adrianomenezes.gameawards.service.GameService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController

public class GameRestController extends BaseRestController {
    private final GameService businesslayer;

    public GameRestController(GameService businesslayer) {
        this.businesslayer = businesslayer;
    }

    @GetMapping("games")
    public ResponseEntity<List<Game>> findAll(){
        List<Game> games = businesslayer.findAll();
        return  ResponseEntity.ok(games);
    }

    @GetMapping("games/{id}")
    public ResponseEntity<Game> findById(@PathVariable long id){
        return  ResponseEntity.ok(businesslayer.findById(id));
    }


    @PostMapping("games")
    public ResponseEntity<Game> insert(@RequestBody Game game){
        businesslayer.insert(game);
        return  ResponseEntity.ok(game);
    }

    @PatchMapping("games/{id}/vote")
    public ResponseEntity<Game> update(@PathVariable long id){
        businesslayer.vote(id);
        return  ResponseEntity.ok().build();
    }

    @DeleteMapping("games/{id}")
    public ResponseEntity<Game> delete(@PathVariable long id){
        businesslayer.delete(id);
        return  ResponseEntity.ok().build();
    }



}
