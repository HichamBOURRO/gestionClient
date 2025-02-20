-- Création de la séquence
CREATE SEQUENCE livraison_id_seq
    START WITH 1
    INCREMENT BY 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;

--Création de la table
CREATE TABLE t_livraison (
    id_livraison BIGINT DEFAULT nextval('livraison_id_seq') PRIMARY KEY,
    date_creation TIMESTAMP,                          -- Date de création de la livraison
    date_livraison TIMESTAMP,                         -- Date de livraison
    id_client_f BIGINT,                              -- Clé étrangère vers la table Client
    id_type_livraison_f BIGINT,                      -- Clé étrangère vers la table TypeLivraison
    FOREIGN KEY (id_client_f) REFERENCES t_client(id_client), -- Référence à la table Client
    FOREIGN KEY (id_type_livraison_f) REFERENCES t_type_livraison(id_type_livraison) -- Référence à la table TypeLivraison
);
