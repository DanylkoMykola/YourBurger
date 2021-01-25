select * from yourburgerdatabase.products;

select * from facilities where serving_city='Стебник';

delete from orders
where order_id=10;


drop table yourburgerdatabase.products;
drop table yourburgerdatabase.addresses;
drop table yourburgerdatabase.customers;
drop table yourburgerdatabase.facilities;
drop table yourburgerdatabase.orders;