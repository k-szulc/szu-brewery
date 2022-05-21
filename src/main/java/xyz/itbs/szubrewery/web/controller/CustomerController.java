package xyz.itbs.szubrewery.web.controller;


import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import xyz.itbs.szubrewery.services.CustomerService;
import xyz.itbs.szubrewery.web.model.BeerDTO;
import xyz.itbs.szubrewery.web.model.CustomerDTO;

import java.util.UUID;

@RequestMapping("/api/v1/customer")
@RestController
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/{customerId}")
    public ResponseEntity<CustomerDTO> getCustomer(@PathVariable("customerId") UUID customerId) {
        return new ResponseEntity<>(customerService.getCustomerById(customerId), HttpStatus.OK);

    }

    @PostMapping
    public ResponseEntity<CustomerDTO> handlePost(@RequestBody CustomerDTO customerDTO) {

        CustomerDTO savedDto = customerService.saveNewCustomer(customerDTO);
        HttpHeaders headers = new HttpHeaders();
        headers.add("location", "192.168.100.2:8080/api/v1/customer/" + savedDto.getId().toString());

        return new ResponseEntity<>(headers,HttpStatus.OK);
    }

    @PutMapping({"/{customerId}"})
    public  ResponseEntity<CustomerDTO> handleUpdate(@PathVariable("customerId") UUID customerId, @RequestBody CustomerDTO customerDTO){

        customerService.updateCustomer(customerId, customerDTO);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping({"/{customerId}"})
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void handleDelete(@PathVariable("customerId") UUID customerId) {
        customerService.deleteById(customerId);
    }
}
