package memlearn.backend.db;

import memlearn.backend.model.MemCard;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemMongoDb extends PagingAndSortingRepository<MemCard, String> {
}
