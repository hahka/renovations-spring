INSERT INTO users(ID, USERNAME, PASSWORD, ROLE)
VALUES
('becbab08-719b-4f61-a3ec-be86b37399f7', 'user1', '$2y$10$cF7IR.N8hsuB9j0KlgyiTOphr39YNDJyqVXQL.B0RE3VhY6MyoGea', 'ROLE_ADMIN'),
('31bc6fa9-5511-43be-a167-06b0fcb4d9c1', 'user2', '$2y$10$cF7IR.N8hsuB9j0KlgyiTOphr39YNDJyqVXQL.B0RE3VhY6MyoGea', 'ROLE_USER');

INSERT INTO projects(ID, LABEL) VALUES 
('d4aac837-e1b8-4d72-a58d-18c51e4c0403', 'Projet 1'),
('d608b610-2296-4a65-8d96-d517a66fa15b', 'Projet 2');

INSERT INTO works(ID, LABEL, PARENT_PROJECT_ID) 
VALUES 
('e02ebba1-47f6-45e8-befd-6c7505fc9142', 'Chantier 1', 'd4aac837-e1b8-4d72-a58d-18c51e4c0403'),
('ad969f24-9204-47c1-ad02-8b761620d1ff', 'Chantier 2', 'd4aac837-e1b8-4d72-a58d-18c51e4c0403'),
('e743cc50-f866-4a28-9b93-4e6f8ff2f687', 'Chantier 3', 'd608b610-2296-4a65-8d96-d517a66fa15b');

INSERT INTO user_project_mapping(user_id, project_id) 
VALUES 
('becbab08-719b-4f61-a3ec-be86b37399f7', 'd4aac837-e1b8-4d72-a58d-18c51e4c0403'),
('31bc6fa9-5511-43be-a167-06b0fcb4d9c1', 'd608b610-2296-4a65-8d96-d517a66fa15b');