package repository.custom;

import model.Customer;
import repository.CrudRepository;

import java.util.List;

public interface CustomerRepository extends CrudRepository<Customer,String> {



    boolean create(Customer customer);

    boolean update(Customer customer);

    boolean deleteById(String id);

    Customer getById(String id);

    List<Customer> getAll();
}
