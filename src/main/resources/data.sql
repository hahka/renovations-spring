INSERT INTO users(USERNAME, PASSWORD, ROLE) VALUES ('username', '$2y$10$cF7IR.N8hsuB9j0KlgyiTOphr39YNDJyqVXQL.B0RE3VhY6MyoGea', 'ROLE_ADMIN');

INSERT INTO projects(LABEL) VALUES ('Projet 1');

INSERT INTO works(LABEL, PARENT_PROJECT_ID) 
VALUES 
('Chantier 1', SELECT id FROM projects ORDER BY ID DESC LIMIT 1),
('Chantier 2', SELECT id FROM projects ORDER BY ID DESC LIMIT 1);

INSERT INTO user_project_mapping(user_id, project_id) 
VALUES 
((SELECT id FROM users ORDER BY ID DESC LIMIT 1), (SELECT id FROM projects ORDER BY ID DESC LIMIT 1));