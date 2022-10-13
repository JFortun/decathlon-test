CREATE TABLE stock
(
    stock_id    INT PRIMARY KEY,
    stock_state VARCHAR(25) NOT NULL
);

CREATE TABLE shoe
(
    shoe_id       INT PRIMARY KEY,
    shoe_color    VARCHAR(25) NOT NULL,
    shoe_size     INT         NOT NULL,
    shoe_quantity INT         NOT NULL,
    stock_id      INT,
    foreign key (stock_id) references stock (stock_id)
);
