

INSERT INTO CUSTOMER (name, mail)
VALUES('CUSTOMER1', 'apicarrentalservice@onet.pl');

INSERT INTO TRACKING_INFO (location, status)
VALUES( 'LOCATION1', 'IN PROGRESS');

INSERT INTO ORDERS (customer_id, tracking_info_id, order_date)
VALUES( 1, 1, '2023-07-10');

INSERT INTO ORDERS (customer_id, tracking_info_id, order_date)
VALUES( 1, 1, '2023-07-11');