package praca.demo.controllers;

import org.springframework.web.bind.annotation.*;
import praca.demo.commons.HelperConverter;
import praca.demo.model.Customer;
import praca.demo.services.CustomerServices;

import javax.validation.Valid;
import java.util.*;

@RestController
@CrossOrigin
@RequestMapping("/customer")
public class CustomerControllers {

    private CustomerServices customerServices;

    public CustomerControllers(CustomerServices customerServices) {
        this.customerServices = customerServices;
    }

    @GetMapping("/parametes/{page}")
    public List<Customer> getCustomerWithParametes
            (@PathVariable Integer page,
             @RequestParam(required = false) Optional<String> name,
             @RequestParam(required = false) Optional<String> surname,
             @RequestParam(required = false) Optional<String> salary,
             @RequestParam(required = false) Optional<String> persent,
             @RequestParam(required = false) Optional<String> qute,
             @RequestParam(required = false) Optional<String> cos) {


        Map<String,String> mapsOfParametersString = new HashMap<>();

        if(HelperConverter.convertToString(name) != null){
            mapsOfParametersString.put("name",name.get());
        }
        if(HelperConverter.convertToString(surname) != null){
            mapsOfParametersString.put("surname",surname.get());
        }
        if(HelperConverter.checkIfDouble(salary) != null){
            mapsOfParametersString.put("salary",salary.get());
            System.out.println("Pensja wynosi "+salary.get());
        }
        if(HelperConverter.checkIfDouble(persent) != null){
            mapsOfParametersString.put("persent",persent.get());
        }
        if(HelperConverter.convertToString(qute) != null){
            mapsOfParametersString.put("qute",qute.get());
        }
        if(HelperConverter.convertToString(cos) != null){
            mapsOfParametersString.put("cos",cos.get());
        }
        return customerServices.findWithCryteria(page,mapsOfParametersString);
    }
    @GetMapping("/pages/{page}")
    public List<Customer>getCustomerWithPage(@PathVariable Integer page){
        return customerServices.getWithPages(page);
    }
    @GetMapping("/customer/count")
    public Long getCountPages(){
        return customerServices.getCountedValues();
    }


}
