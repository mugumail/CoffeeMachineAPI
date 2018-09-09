create table item (
    id INT PRIMARY KEY,
    itemCode varchar(10),
    description varchar(30)
);

create table inventory (
    itemCode varchar(10),
    quantity INT,
    price decimal(20,2)
);

insert into item (id,description, itemCode) values (1, 'Capucinno', 'STS-001');
insert into inventory (itemCode, quantity, price) values ('STS-001', 10, 10.00);
insert into item (id,description, itemCode) values (2, 'Hot Choclate', 'STS-002');
insert into inventory (itemCode, quantity, price) values ('STS-002', 10, 10.00);
insert into item (id,description, itemCode) values (3, 'Espresso', 'STS-003');
insert into inventory (itemCode, quantity, price) values ('STS-003', 10, 10.00);
insert into item (id,description, itemCode) values (4, 'Caffe Latte', 'STS-004');
insert into inventory (itemCode, quantity, price) values ('STS-004', 10, 10.00);
insert into item (id,description, itemCode) values (5, 'Hot Water', 'STS-005');
insert into inventory (itemCode, quantity, price) values ('STS-005', 100, 0.00);