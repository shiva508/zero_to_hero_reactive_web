drop table if exists tbl_user_transactions;
create table tbl_user_transactions(
    TX_ID bigint primary key,
    USER_ID bigint ,
    AMOUNT bigint,
    TX_TIME Datetime
);