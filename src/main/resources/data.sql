-- Create electronics category and sub categories

insert into category (name, parent_id) values ('electronics', null);
insert into category (name, parent_id) values ('laptops', 1);
insert into category (name, parent_id) values ('smartphones', 1);
insert into category (name, parent_id) values ('Samsung', 3);
insert into category (name, parent_id) values ('Asus', 2);

-- Create products

insert into product (name, price, currency, category_id) values ('Samsung Galaxy S7 Edge', 99.87, 'USD', 4);
insert into product (name, price, currency, category_id) values ('Asus pro book', 399.57, 'USD', 5);
