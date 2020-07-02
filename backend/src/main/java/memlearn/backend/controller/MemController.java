package memlearn.backend.controller;


import memlearn.backend.model.MemCard;
import memlearn.backend.service.MemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/mem")
public class MemController {

    @Autowired
    private MemService memService;

    @GetMapping
    public List<MemCard> getMemCards() {
       return memService.getMemCards();
    }

    @PutMapping
    public void addMemCard(@RequestBody MemCard memCard) {
        memService.addMemCard(memCard);
    }
}
