INSERT INTO users(Id, USERNAME, PASSWORD, ROLE)
VALUES
('becbab08-719b-4f61-a3ec-be86b37399f7', 'user1', '$2y$10$cF7IR.N8hsuB9j0KlgyiTOphr39YNDJyqVXQL.B0RE3VhY6MyoGea', 'ROLE_ADMIN'),
('31bc6fa9-5511-43be-a167-06b0fcb4d9c1', 'user2', '$2y$10$cF7IR.N8hsuB9j0KlgyiTOphr39YNDJyqVXQL.B0RE3VhY6MyoGea', 'ROLE_USER');

INSERT INTO projects(LABEL) VALUES ('Projet 1');
INSERT INTO projects(LABEL) VALUES ('Projet 2');

INSERT INTO works(LABEL, PARENT_PROJECT_ID) 
VALUES 
('Chantier 1', SELECT id FROM projects ORDER BY ID DESC LIMIT 1),
('Chantier 2', SELECT id FROM projects ORDER BY ID DESC LIMIT 1);

INSERT INTO user_project_mapping(user_id, project_id) 
VALUES 
('becbab08-719b-4f61-a3ec-be86b37399f7', 1),
('31bc6fa9-5511-43be-a167-06b0fcb4d9c1', 2);