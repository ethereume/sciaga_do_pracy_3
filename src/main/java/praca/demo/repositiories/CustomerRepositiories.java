package praca.demo.repositiories;


import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import praca.demo.model.Customer;

import java.util.List;

@Repository
public interface CustomerRepositiories extends CrudRepository<Customer,Integer>, JpaSpecificationExecutor<Customer> {
    List<Customer> getAllBy(Pageable pageable);
}
