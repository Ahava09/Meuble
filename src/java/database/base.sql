/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  itu
 * Created: 16 janv. 2024
 */

create database meuble;
 \c meuble;

create table unite (
    id serial primary key,
    nom varchar(50),
    famille int ,
    convertion double precision
);
-- Ajout de données à la table "unite"
INSERT INTO unite (nom, famille, convertion) VALUES
    ('mètres', 40, 1),
    ('kilogrammes', 30, 0.001),
    ('grammes', 30, 1),
    ('litres', 10, 1),
    ('mètres cubes', 1, 1),
    ('unite', 0, 1),
    ('hectares', 20, 0.0001),
    ('millilitres', 10, 0.001),
    ('kilolitres', 1, 0.001),
    ('milligrammes', 30, 0.000001),
    ('centimètres', 40, 0.01),
    ('kilomètres', 1, 1000),
    ('millimètres', 40, 0.001);

create table caracteristique (
    id serial primary key,
    nom varchar(100)
);
-- Ajout de quelques catégories à la table "caracteristique"
INSERT INTO caracteristique (nom) VALUES
    ('Style'),
    ('Categorie');

create table detailsCaractestique (
    id serial primary key,
    nom varchar(100),
    idCaracteristique int,
    foreign key (idCaracteristique) references caracteristique(id)
);
-- Ajout de quelques catégories à la table "categorie"
INSERT INTO detailsCaractestique (nom, idCaracteristique) VALUES
    ('Moderne',1),
    ('Classique',1),
    ('Contemporain',1),
    ('Minimaliste',1),
    ('Vintage',1),
    ('Chaises', 2),
    ('Tables',2),
    ('Armoires',2),
    ('Lits',2),
    ('Canapés',2); -- litres

create table matiere (
    id serial primary key,
    nom varchar(50),
    idUnite int, 
    foreign key (idUnite) references unite(id)
);
-- Ajout de données à la table "matiere"
INSERT INTO matiere (nom, idUnite) VALUES
    ('Bois', 1), -- mètres
    ('Acier', 2), -- kilogrammes
    ('Cuir', 2), -- mètres
    ('Verre', 5), -- mètres cubes
    ('Plastique', 2), -- kilogrammes
    ('Tissu', 1); -- mètres

-- create table prixMatiere (
--     id serial primary key,
--     idMatiere int,
--     idUnite int,
--     prix double precision,
--     dates timestamp,
--     foreign key (idMatiere) references matiere(id), 
--     foreign key (idUnite) references unite(id)
-- );
-- INSERT INTO prixMatiere (idMatiere, idUnite, prix, dates) VALUES
--     (1, 1, 1500, '2023-01-01 10:30:00'), -- Bois en mètres
--     (2, 2, 2000, '2023-01-02 14:45:00'), -- Acier en kilogrammes
--     (3, 1, 1200, '2023-01-03 12:15:00'), -- Cuir en mètres
--     (4, 5, 2500, '2023-01-04 08:00:00'), -- Verre en mètres cubes
--     (5, 2, 1800, '2023-01-05 11:00:00'), -- Plastique en kilogrammes
--     (6, 1, 1000, '2023-01-06 09:30:00'); -- Tissu en mètres

create table poste (
    id serial primary key,
    nom varchar(50),
    salaire double precision
);
-- Ajout de données à la table "poste"
INSERT INTO poste (nom, salaire) VALUES
    ('Menuisier', 2500),
    ('Assembleur', 2200),
    ('Finition', 2000),
    ('Designer', 3000);

-- create table produit (
--     id serial primary key,
--     idStyle int,
--     idCategorie int,
--     foreign key (idStyle) references detailsCaractestique(id),
--     foreign key (idCategorie) references detailsCaractestique(id)
-- );
-- -- Ajout de données à la table "produit"
-- INSERT INTO produit (idStyle, idCategorie) VALUES
--     (6, 1),
--     (6, 2),
--     (7, 3),
--     (7, 4),
--     (8, 5);

create table matiereStyle (
    id serial primary key,
    idStyle int,
    idMatiere int,
    foreign key (idStyle) references detailsCaractestique(id),
    foreign key (idMatiere) references matiere(id)
);
-- Ajout de données à la table "produit"
INSERT INTO matiereStyle (idStyle, idMatiere) VALUES
    (1, 1),
    (1, 2),
    (2, 1),
    (2, 2),
    (2, 6),
    (3, 2),
    (3, 3),
    (3, 4),
    (4, 1),
    (4, 2),
    (4, 3),
    (4, 6),
    (5, 3),
    (5, 4);

create table employeeProduit (
    id serial primary key,
    idStyle int,
    idCategorie int,
    idPoste int,
    foreign key (idStyle) references detailsCaractestique(id),
    foreign key (idCategorie) references detailsCaractestique(id),
    foreign key (idPoste) references poste(id)
);
--     (6, 1),
--     (6, 2),
--     (7, 3),
--     (7, 4),
--     (8, 5);
-- Ajout de données à la table "produit"
INSERT INTO employeeProduit (idStyle, idCategorie, idPoste) VALUES
    (6, 1, 1),
    (6, 1, 2),
    (6, 2, 1),
    (6, 2, 2),
    (6, 2, 3),
    (7, 3, 2),
    (7, 3, 3),
    (7, 3, 4),
    (7, 4, 1),
    (7, 4, 2),
    (7, 4, 3),
    (7, 4, 4),
    (8, 5, 3),
    (8, 5, 4);

create table taille ( 
    id serial primary key,
    nom varchar(50),
    volumeMin double precision,
    voulumeMax double precision
);
-- Ajout de données à la table "taille"
INSERT INTO taille (nom, volumeMin, voulumeMax) VALUES
    ('Petite', 0.1, 1.0),
    ('Moyenne', 1.1, 5.0),
    ('Grande', 5.1, 10.0),
    ('Très Grande', 10.1, 20.0);

create table informationProduit (
    id serial primary key,
    idStyle int,
    idCategorie int,
    idTaille int,
    volume double precision,
    prixVente double precision,
    dureeDebut timestamp,
    dureeFin timestamp,  
    foreign key (idStyle) references detailsCaractestique(id),
    foreign key (idCategorie) references detailsCaractestique(id),
    foreign key (idTaille) references taille(id)
);

-- Ajout de données à la table "informationProduit"
INSERT INTO informationProduit (idStyle,idCategorie, idTaille, volume, prixVente, dureeDebut, dureeFin) VALUES
    (6, 1, 1, 2.5, 500.0, '2023-01-01 08:00:00', '2023-01-10 18:00:00'),
    (6, 1, 2, 5.0, 1200.0, '2023-01-03 10:30:00', '2023-01-15 16:45:00'),
    (6, 1, 3, 7.0, 1500.0, '2023-01-05 12:00:00', '2023-01-08 14:30:00'),
    (6, 1, 4, 13.0, 8000.0, '2023-01-08 09:45:00', '2023-01-20 17:15:00');

create table quantiteMatiereInformationProduit (
    id serial primary key,
    idInformationProduit int,
    idMatiereStyle int,
    quantite double precision,
    idUnite int,
    foreign key (idInformationProduit) references informationProduit(id),
    foreign key (idMatiereStyle) references MatiereStyle(id),
    foreign key (idUnite) references unite(id)
);
-- Ajout de données à la table "quantiteMatiereInformationProduit"
INSERT INTO quantiteMatiereInformationProduit (idInformationProduit, idMatiereStyle, quantite, idUnite) VALUES
    (1, 1, 10.0, 1),  -- 10 mètres de Bois
    (1, 2, 5.0, 2),   -- 5 kilogrammes d'Acier
    (2, 3, 2.0, 3),   -- 2 mètres de Cuir
    (2, 4, 8.0, 5),   -- 8 mètres cubes de Verre
    (3, 5, 3.0, 2),   -- 3 kilogrammes de Plastique
    (3, 4, 15.0, 1),  -- 15 mètres de Tissu
    (4, 1, 7.0, 1),   -- 7 mètres de Bois
    (4, 2, 4.0, 2);   -- 4 kilogrammes d'Acier

create table nombreEmployeeInformationProduit (
    id serial primary key,
    idInformationProduit int,
    idPoste int,
    nombre int,
    duree double precision,
    foreign key (idInformationProduit) references informationProduit(id),
    foreign key (idPoste) references poste(id)
);
-- Ajout de données à la table "nombreEmployeeInformationProduit"
INSERT INTO nombreEmployeeInformationProduit (idInformationProduit, idPoste, nombre, duree) VALUES
    (1, 1, 2, 8.0),    -- 2 Menuisiers travaillent pendant 8 heures
    (1, 2, 1, 8.0),    -- 1 Assembleur travaille pendant 8 heures
    (2, 3, 3, 10.0),   -- 3 Finitions travaillent pendant 10 heures
    (2, 4, 1, 6.0),    -- 1 Designer travaille pendant 6 heures
    (3, 1, 2, 5.0),    -- 2 Menuisiers travaillent pendant 5 heures
    (3, 3, 1, 3.5),    -- 1 Finition travaille pendant 3.5 heures
    (4, 2, 2, 7.0),    -- 2 Assembleurs travaillent pendant 7 heures
    (4, 4, 1, 4.0);    -- 1 Designer travaille pendant 4 heures

create view DetailsProduit as
SELECT ms.id idMatierestyle, m.nom AS nomMatiere, ds.nom AS nomStyle,dc.nom AS nomCategorie,
    ms.idStyle idStyle, ms.idMatiere idMatiere, qmp.quantite quantite, ip.idCategorie idCategorie, t.nom nomTaille, t.id idTaille, u.nom nomUnite,
    ip.prixVente prixVente
    FROM InformationProduit ip 
    Join QuantiteMatiereInformationProduit qmp ON qmp.idinformationproduit = ip.id
    JOIN matiereStyle ms ON ms.id = qmp.idMatierestyle
    join taille t on t.id = ip.idTaille
    JOIN matiere m ON m.id = ms.idMatiere
    JOIN detailsCaractestique ds ON ms.idStyle = ds.id
    JOIN detailsCaractestique dc ON ip.idCategorie = dc.id
    join unite u on u.id = m.idUnite
    group by ms.idStyle,ip.idCategorie, ms.id, m.nom,dc.nom,ms.idMatiere,qmp.quantite, ip.idCategorie, t.nom, t.id, u.nom,ds.nom ,prixVente
    order by ms.idStyle, ip.idCategorie, ms.idMatiere, t.nom;

create view DetailsProduitPrix as
SELECT ip.id idinformationproduit, m.nom AS nomMatiere, ds.nom AS nomStyle,dc.nom AS nomCategorie,
    qmp.quantite quantite, e.prix prixUnitaireMatiere, (e.prix * qmp.quantite) prixMatiere , m.id AS idMatiere,
    SUM(SUM(e.prix * qmp.quantite)) OVER (PARTITION BY ip.idCategorie, ms.idStyle, ip.volume) AS  prixTotal
    FROM InformationProduit ip 
    Join QuantiteMatiereInformationProduit qmp ON qmp.idinformationproduit = ip.id
    JOIN matiereStyle ms ON ms.id = qmp.idMatierestyle
    JOIN matiere m ON m.id = ms.idMatiere
    JOIN detailsCaractestique ds ON ms.idStyle = ds.id
    JOIN detailsCaractestique dc ON ip.idCategorie = dc.id
    join entree e on e.idMatiere =  ms.idMatiere
    where e.dateEntree = (select max(dateEntree) from entree where idMatiere = ms.idMatiere)
    group by ms.idStyle, ms.id, m.nom,dc.nom,qmp.quantite,ds.nom ,e.prix,ip.idCategorie,ip.volume,ip.id,m.id
    order by ms.idStyle, ip.idCategorie, ms.idMatiere;

create view DetailsProduitPoste as
SELECT ip.id idinformationproduit, ds.nom AS nomStyle,dc.nom AS nomCategorie, ip.volume,
    p.nom nomPoste,p.id idPoste, p.salaire salairePoste, nbp.nombre nombre , nbp.duree,
    (p.salaire * nbp.nombre *nbp.duree) prixDureeTotalPoste ,
    SUM(SUM(p.salaire * nbp.nombre *nbp.duree)) OVER (PARTITION BY ds.nom, dc.nom,ip.volume) AS  prixTotal
    FROM InformationProduit ip 
    JOIN detailsCaractestique ds ON ip.idStyle = ds.id
    JOIN detailsCaractestique dc ON ip.idCategorie = dc.id
    join nombreEmployeeInformationProduit nbp on nbp.idInformationProduit =ip.id
    join poste p on p.id = nbp.idPoste
    group by  dc.nom,ds.nom , ip.volume,p.nom,p.salaire,nbp.nombre, nbp.duree,ip.id,p.id
    order by ds.nom, dc.nom,ip.volume;



create table entree (
    id serial primary key,
    idMatiere int,
    idUnite int,
    prix double precision,
    quantite double precision,
    dateEntree timestamp,
    foreign key (idMatiere) references matiere(id), 
    foreign key (idUnite) references unite(id)
);
INSERT INTO entree (idMatiere, idUnite, quantite, prix, dateEntree) VALUES
    (1, 1, 29,1500, '2023-01-05 10:30:00'), -- Bois en mètres
    (2, 2, 10,2000, '2023-01-05 14:45:00'), -- Acier en kilogrammes
    (3, 1, 12,1200, '2023-01-05 12:15:00'), -- Cuir en mètres
    (4, 5, 26,2500, '2023-01-05 08:00:00'), -- Verre en mètres cubes
    (5, 2, 15,1800, '2023-01-05 11:00:00'), -- Plastique en kilogrammes
    (6, 1, 10, 1000,'2023-01-08 09:30:00'); -- Tissu en mètres

create table sortieproduit (
    id serial primary key,
    idInformationProduit int,
    nombre int,
    datesortie timestamp,
    foreign key (idInformationProduit) references informationProduit(id)
); 
create table sortiematiere (
    id serial primary key,
    idMatiere int,
    quantite double precision,
    datesortie timestamp,
    foreign key (idMatiere) references matiere(id)
); 
INSERT INTO sortiematiere (idMatiere, quantite, datesortie) VALUES
    (1,0, '2023-01-05 10:30:00'),
    (2,0, '2023-01-05 10:30:00'),
    (3,0, '2023-01-05 10:30:00'),
    (4,0, '2023-01-05 10:30:00'),
    (5,0, '2023-01-05 10:30:00'),
    (6,0, '2023-01-05 10:30:00'),
    (7,0, '2023-01-05 10:30:00');

create view etatstock as
select m.id idMatiere, m.nom nomMatiere, 
    (sum(e.quantite)-sum(s.quantite)) quantite , u.nom
    from matiere m 
    join entree e on e.idMatiere = m.id
    join sortiematiere s on s.idMatiere=m.id
    join unite u on u.id = m.idUnite
    group by m.id,m.nom,u.nom;

create table niveau (
    id serial primary key,
    nom varchar(30),
    dureemin int,
    dureemax int
);
insert into niveau (nom, dureemin, dureemax) values
    ('Ouvrier',0,2),
    ('Senior',2,3),
    ('Expert',3,10);
    
create table employee (
    id serial primary key,
    nom varchar(50),
    prenoms varchar(50),
    dtn date,
    dateDebut date,
    idPoste int,
    salaire double precision,
    foreign key (idPoste) references poste(id)
);

create view employee_niveau as
select e.id id, e.nom nom, e.prenoms prenoms, p.nom poste, p.id idposte,e.salaire salaire, e.dateDebut,
    n.nom niveau, EXTRACT(YEAR FROM AGE(CURRENT_DATE,e.dateDebut)) duree, (e.salaire*EXTRACT(YEAR FROM AGE(CURRENT_DATE,e.dateDebut))) taux
    from employee e 
    join niveau n on n.dureemin <= EXTRACT(YEAR FROM AGE(CURRENT_DATE,e.dateDebut)) 
                    and n.dureemax > EXTRACT(YEAR FROM AGE(CURRENT_DATE,e.dateDebut)) 
    join poste p on p.id=e.idposte

create table client (
    id serial primary key,
    nom varchar(50),
    prenom varchar(100),
    idgenre int,
    mail varchar(100),
    foreign key (idgenre) references genre(id)
);

create table achat (
    id serial primary key,
    idInformationProduit int,
    idClient int,
    dateAchat timestamp,
    nombre int,
    foreign key (idInformationProduit) references informationProduit(id),
    foreign key (idClient) references client(id)
);

Create table genre (
    id serial primary key,
    nom varchar (20)
);
insert into genre (nom) values ('Homme'),('Femme');

-- SELECT
--     a.idInformationProduit,
--     SUM(CASE WHEN c.genre = 1 THEN sum(a.nombre) ELSE 0 END) AS Male,
--     SUM(CASE WHEN c.genre != 1 THEN sum(a.nombre) ELSE 0 END) AS Female
-- FROM 
--     achat a
-- JOIN 
--     client c ON c.id = a.idClient
-- join genre g on c.idgenre = g.id
-- GROUP BY 
--     a.idInformationProduit;

create view Statistique as
select a.idInformationProduit idInformationProduit,
    c.idgenre idgenre,  g.nom Nomgenre, sum(a.nombre) nombre 
    from achat a 
    join client c on c.id = a.idClient 
    join genre g on c.idgenre = g.id
    join informationProduit i on i.id = a.id
    GROUP BY c.idgenre,g.nom,a.idInformationProduit

-- select  i.id idInformationProduit,
--     s.idgenre idgenre,
--     s.Nomgenre Nomgenre,
-- CASE WHEN s.nombre is null THEN 0 else s.nombre end as nombre
-- from informationProduit i
-- left join statistique s on i.id = s.idInformationProduit
-- left join genre g on g.id = s.idgenre;

select  i.id idInformationProduit,
    CASE WHEN g.id = 1 THEN (CASE WHEN s.nombre is null THEN 0 else s.nombre end )END AS Male,
    CASE WHEN g.id = 2 THEN (CASE WHEN s.nombre is null THEN 0 else s.nombre end) END AS Femme
    from informationProduit i
left join statistique s on i.id = s.idInformationProduit
left join genre g on g.id = s.idgenre

SELECT  
    i.id AS idInformationProduit,
    SUM(CASE WHEN g.id = 1 THEN COALESCE(s.nombre, 0) ELSE 0 END) AS genre_1,
    SUM(CASE WHEN g.id = 2 THEN COALESCE(s.nombre, 0) ELSE 0 END) AS genre_2
FROM 
    informationProduit i
LEFT JOIN 
    statistique s ON i.id = s.idInformationProduit
LEFT JOIN 
    genre g ON g.id = s.idgenre
GROUP BY
    i.id;

create view StatistiqueParProduit as
SELECT  
    i.id AS idInformationProduit,
    SUM(CASE WHEN g.id = 1 THEN COALESCE(s.nombre, 0) ELSE 0 END) AS Male,
    SUM(CASE WHEN g.id = 2 THEN COALESCE(s.nombre, 0) ELSE 0 END) AS Femme
FROM 
    informationProduit i
LEFT JOIN 
    statistique s ON i.id = s.idInformationProduit
LEFT JOIN 
    genre g ON g.id = s.idgenre
GROUP BY
    i.id;

create table panier (
    id serial primary key,
    idClient int,
    datepanier timestamp,
    foreign key (idClient) references client (id)
);
INSERT INTO panier (idClient, datepanier)
VALUES
    (1, '2023-01-02 14:57:00'), -- Kanto Tahina
    (2, '2023-01-03 09:30:00'); -- Raherinirina Nomena
INSERT INTO panier (idClient, datepanier)
VALUES
    (3, '2023-01-04 15:20:00'), -- Rotsy RAndria
    (4, '2023-01-05 12:45:00'); -- Sababa Ny aina

create table detailsPanier (
    id serial primary key,
    idPanier int,
    idInformationProduit int,
    nombre int,
    foreign key (idInformationProduit) references informationProduit(id),
    foreign key (idPanier) references panier(id)
);
INSERT INTO detailsPanier (idPanier, idInformationProduit, nombre)
VALUES
    (3, 2, 2),  -- Panier de Rotsy RAndria : 2 articles du produit id 2
    (3, 4, 1),  -- Panier de Rotsy RAndria : 1 article du produit id 4
    (4, 1, 3),  -- Panier de Sababa Ny aina : 3 articles du produit id 1
    (4, 3, 2);  -- Panier de Sababa Ny aina : 2 articles du produit id 3
INSERT INTO detailsPanier (idPanier, idInformationProduit, nombre)
VALUES
    (1, 1, 2),  -- Panier de Kanto Tahina : 2 articles du produit id 1
    (1, 2, 1),  -- Panier de Kanto Tahina : 1 article du produit id 2
    (2, 3, 3),  -- Panier de Raherinirina Nomena : 3 articles du produit id 3
    (2, 4, 1);  -- Panier de Raherinirina Nomena : 1 article du produit id 4

select p.idClient idClient,  p.datepanier datepanier, d.idInformationProduit idInformationProduit,
    d.nombre nombre
        from panier p 
        join detailsPanier d on d.idPanier = p.id