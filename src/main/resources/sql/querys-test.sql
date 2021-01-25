select * from yourburgerdatabase.products;

select * from facilities where serving_city='Стебник';

delete from products
where prod_id = 12;

drop table yourburgerdatabase.products;
drop table yourburgerdatabase.addresses;
drop table yourburgerdatabase.customers;
drop table yourburgerdatabase.facilities;
drop table yourburgerdatabase.orders;