package com.farmmart.service.customer;

import com.farmmart.data.exception.CustomerException;
import com.farmmart.data.model.Customer;
import com.farmmart.data.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImp implements CustomerService {


    private CustomerRepository customerRepository;


    //To implement at the controller layer(web)
//    @Autowired
//    private ModelMapper modelMapper;

    //To implement at the controller layer(web)
//    private CustomerDTO convertToCustomerDTO(Customer customer){
//        CustomerDTO customerDTO = new CustomerDTO();
//
//        customerDTO.setId(customer.getId());
//        customerDTO.setFirstName(customer.getFirstName());
//        customerDTO.setLastName(customer.getLastName());
//        customerDTO.setPhoneNumber(customer.getPhoneNumber());
//        customerDTO.setEmailAddress(customer.getEmailAddress());
////        customerDTO.setPassword(customer.getPassword());
//
//        Address address = new Address();
//        customerDTO.setStreetNumber(address.getStreetNumber());
//        customerDTO.setStreetName(address.getStreetName());
//        customerDTO.setCity(address.getCity());
//
//        return customerDTO;
//    }

//    private String encryptPassword(String password){
//        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
//
//        return bCryptPasswordEncoder.encode(password);
//    }

    @Override
    public Customer addCustomer(Customer customer) throws CustomerException{
//        customer.setPassword(encryptPassword(customer.getPassword()));

        return customerRepository.saveCustomer(customer);
    }

    @Override
    public Customer findCustomerById(Integer id) throws CustomerException {
        Customer customer =customerRepository.findById(id).orElse(null);
        if(customer!=null){
            return customer;
        }else{
            throw  new CustomerException("Customer not found");
        }

    }

    @Override
    public List<Customer> findAllCustomers() {

        return  customerRepository.findAll();
    }

    @Override
    public Customer updateCustomer(Integer id, Customer customers) throws CustomerException {
        Customer customer =customerRepository.findById(id).orElse(null);
        customer.setLastName(customers.getLastName());
        customer.setFirstName(customers.getFirstName());
        customer.setBillingDetails(customers.getBillingDetails());
        customer.setPhoneNumber(customers.getPhoneNumber());
        customer.setAddresses(customers.getAddresses());


        return customerRepository.saveCustomer(customer);
    }

    @Override
    public void deleteCustomerById(Integer id) {

        customerRepository.deleteById(id);

    }

    @Override
    public void deleteAllCustomers() {
        customerRepository.deleteAll();

    }

}
