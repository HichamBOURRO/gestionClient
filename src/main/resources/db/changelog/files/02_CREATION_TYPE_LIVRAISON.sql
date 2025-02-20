-- Création de la séquence
CREATE SEQUENCE type_id_seq
    START WITH 1
    INCREMENT BY 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;

--Création de la table
CREATE TABLE t_type_livraison (
    id_type_livraison BIGINT DEFAULT nextval('type_id_seq') PRIMARY KEY,
    type_livraison VARCHAR(255) NOT NULL                  -- Nom du type de livraison
);
