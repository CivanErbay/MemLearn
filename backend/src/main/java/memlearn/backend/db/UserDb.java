package memlearn.backend.db;


import memlearn.backend.model.PlanningUser;
import org.springframework.data.repository.PagingAndSortingRepository;


public interface UserDb extends PagingAndSortingRepository<PlanningUser,String> {

}
