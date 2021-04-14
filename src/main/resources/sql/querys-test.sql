select * from yourburgerdatabase.products;

select * from facilities where serving_city='Стебник';

delete from yourburgerdatabase.customers;



drop table yourburgerdatabase.products;
drop table yourburgerdatabase.addresses;
drop table yourburgerdatabase.customers;
drop table yourburgerdatabase.facilities;
drop table yourburgerdatabase.orders;

select orders.address_id from orders
join customers c on orders.cust_id = c.cust_id
where c.cust_id = 1
order by order_date
limit 1;

select * from addresses a
where address_id = (select orders.address_id from orders
    join customers c on orders.cust_id = c.cust_id
    where c.cust_id = 2
    order by order_date desc
    limit 1);