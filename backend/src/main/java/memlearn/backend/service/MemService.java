package memlearn.backend.service;

import memlearn.backend.db.MemMongoDb;
import memlearn.backend.model.MemCard;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class MemService {

    private MemMongoDb memMongoDb;

    public MemService(MemMongoDb memMongoDb) {
        this.memMongoDb = memMongoDb;
    }

    public List<MemCard> getMemCards() {
        return (List<MemCard>) memMongoDb.findAll();
    }

    public void addMemCard(MemCard memCard) {
        memCard.setId(UUID.randomUUID()+"");
        memMongoDb.save(memCard);
    }
}
