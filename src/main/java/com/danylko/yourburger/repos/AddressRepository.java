package com.danylko.yourburger.repos;

import com.danylko.yourburger.entities.Address;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface AddressRepository extends CrudRepository<Address, Long> {

    @Query(value = "select * from yourburgerdatabase.addresses a\n" +
            "where address_id = (select yourburgerdatabase.orders.address_id from yourburgerdatabase.orders\n" +
            "    join yourburgerdatabase.customers c on orders.cust_id = c.cust_id\n" +
            "    where c.cust_id = ?1\n" +
            "    order by order_date desc\n" +
            "    limit 1)", nativeQuery = true)
    Address findAddressByCustomerId(Long id);
    Address findByCity(String city);
    Address findByStreet(String street);
}
