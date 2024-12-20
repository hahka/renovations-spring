INSERT INTO users(ID, USERNAME, PASSWORD, ROLE)
VALUES
('becbab08-719b-4f61-a3ec-be86b37399f7', 'user1', '$2y$10$cF7IR.N8hsuB9j0KlgyiTOphr39YNDJyqVXQL.B0RE3VhY6MyoGea', 'ROLE_ADMIN'),
('31bc6fa9-5511-43be-a167-06b0fcb4d9c1', 'user2', '$2y$10$cF7IR.N8hsuB9j0KlgyiTOphr39YNDJyqVXQL.B0RE3VhY6MyoGea', 'ROLE_USER');

INSERT INTO projects(ID, LABEL) VALUES 
('d4aac837-e1b8-4d72-a58d-18c51e4c0403', 'Projet 1'),
('d608b610-2296-4a65-8d96-d517a66fa15b', 'Projet 2');

INSERT INTO work_types(ID, LABEL, USER_ID) VALUES 
('ad1a9154-340d-46ce-856b-43d73c574d15', 'Démolition', null),
('d9dc4d26-64d1-4a94-a4d8-c5cc64939b09', 'Nettoyage', null),
('37199c64-e20d-408c-b918-31fbe7ece0e2', 'Peinture', 'becbab08-719b-4f61-a3ec-be86b37399f7'),
('7c68b857-be7c-4fb1-a1c2-2fb8a153996c', 'Not owned', '31bc6fa9-5511-43be-a167-06b0fcb4d9c1'),
('8b902b58-7a94-4635-b1a1-1092a5de4bb3', 'Enduit', 'becbab08-719b-4f61-a3ec-be86b37399f7');

INSERT INTO works(ID, LABEL, PARENT_PROJECT_ID, WORK_TYPE_ID) 
VALUES 
('e02ebba1-47f6-45e8-befd-6c7505fc9142', 'Chantier 1', 'd4aac837-e1b8-4d72-a58d-18c51e4c0403', 'ad1a9154-340d-46ce-856b-43d73c574d15'),
('ad969f24-9204-47c1-ad02-8b761620d1ff', 'Chantier 2', 'd4aac837-e1b8-4d72-a58d-18c51e4c0403', 'ad1a9154-340d-46ce-856b-43d73c574d15'),
('e743cc50-f866-4a28-9b93-4e6f8ff2f687', 'Chantier 3', 'd608b610-2296-4a65-8d96-d517a66fa15b', 'd9dc4d26-64d1-4a94-a4d8-c5cc64939b09');

INSERT INTO user_project_mapping(user_id, project_id) 
VALUES 
('becbab08-719b-4f61-a3ec-be86b37399f7', 'd4aac837-e1b8-4d72-a58d-18c51e4c0403'),
('31bc6fa9-5511-43be-a167-06b0fcb4d9c1', 'd608b610-2296-4a65-8d96-d517a66fa15b');