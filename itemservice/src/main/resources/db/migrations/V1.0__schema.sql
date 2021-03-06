CREATE TABLE ITEM(
    ID VARCHAR(255) PRIMARY KEY NOT NULL,
    DESCRIPTION VARCHAR(255)
);

CREATE TABLE STORE(
    ID VARCHAR(255) PRIMARY KEY NOT NULL,
    ITEM_ID VARCHAR(255) NOT NULL,
    QUANTITY INTEGER DEFAULT 0,
    CONSTRAINT FK_ITEM FOREIGN KEY (ITEM_ID) REFERENCES ITEM(ID)
);