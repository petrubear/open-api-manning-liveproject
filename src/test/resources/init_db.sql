create table transactions
(
    id             bigint       not null primary key,
    account_number int          null,
    amount         double       null,
    currency       varchar(255) null,
    date           datetime(6)  null,
    merchant_logo  varchar(255) null,
    merchant_name  varchar(255) null,
    type           varchar(255) null
);

insert into transactions
(id, account_number, amount, currency, date, merchant_logo, merchant_name, type)
values (1, 123456, 10, 'USD', sysdate(), 'amazon.png', 'Amazon', 'credit');
