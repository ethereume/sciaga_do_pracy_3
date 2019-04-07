package praca.demo.services;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import praca.demo.model.Customer;
import praca.demo.repositiories.CustomerRepositiories;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Service
public class CustomerServices {

    private CustomerRepositiories customerRepositiories;
    public CustomerServices(CustomerRepositiories customerRepositiories) {
        this.customerRepositiories = customerRepositiories;
    }
    public List<Customer> getWithPages(Integer page) {
        return customerRepositiories.getAllBy(new PageRequest(page,10));
    }
    public Long getCountedValues(){
        return customerRepositiories.count();
    }
    public List<Customer>findWithCryteria(Integer page,Map<String,String> map){
        Page pages = customerRepositiories.findAll(new Specification<Customer>() {
            @Override
            public Predicate toPredicate(Root root, CriteriaQuery criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicates = new ArrayList<>();
                if(map != null){
                    Set<Map.Entry<String, String>> entries = map.entrySet();
                    entries.stream().forEach(c->{
                        predicates.add((criteriaBuilder.equal(root.get(c.getKey()),c.getValue())));
                    });
                }
            return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
            }
        },new PageRequest(page,10));
        return pages.getContent();
    }

}
