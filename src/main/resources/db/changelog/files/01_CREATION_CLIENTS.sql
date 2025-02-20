-- Création de la séquence
CREATE SEQUENCE client_id_seq
    START WITH 1
    INCREMENT BY 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;

--Création de la table
CREATE TABLE t_client (
    id_client BIGINT DEFAULT nextval('client_id_seq') PRIMARY KEY,
    nom VARCHAR(255),
    prenom VARCHAR(255),
    email VARCHAR(255) NOT NULL,
    mot_de_passe VARCHAR(255) NOT NULL
);
