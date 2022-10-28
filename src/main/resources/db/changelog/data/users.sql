INSERT INTO USER(id, username, password, email, first_name, last_name, phone_number, enabled)
VALUES (SEQ_USER.NEXTVAL, 'admin', 'admin', 'admin@admin.com', 'Robert', 'De Niro', '0038598777666', true);

INSERT INTO USER(id, username, password, email, first_name, last_name, phone_number, enabled)
VALUES (SEQ_USER.NEXTVAL, 'tom', 'pass1', 'tom@cruise.com', 'Tom', 'Cruise', '0038598777666', true);

INSERT INTO USER(id, username, password, email, first_name, last_name, phone_number, enabled)
VALUES (SEQ_USER.NEXTVAL, 'brad', 'pass1', 'brad@pitt.com', 'Brad', 'Pitt', '0038598777666', false);