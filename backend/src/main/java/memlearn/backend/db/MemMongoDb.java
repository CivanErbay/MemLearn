package memlearn.backend.db;

import memlearn.backend.model.MemCard;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface MemMongoDb extends PagingAndSortingRepository<MemCard, String> {
}
