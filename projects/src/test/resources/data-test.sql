drop table KEYS if exists
create table KEYS (COL_KEY Integer)
insert into BOOK (isbn, title, price) values ('TEST-ISBN', 'TEST-TITLE', 19.99)
insert into BOOK (isbn, title, price) values ('TEST-ISBN2', 'TEST-TITLE', 19.99)
insert into STORE (category, item_id, stock) values ('TEST_CATEGORY', 'TEST_ID', 42)
insert into KEYS values (42)
