INSERT INTO users(ID, USERNAME, PASSWORD, ROLE)
VALUES
('becbab08-719b-4f61-a3ec-be86b37399f7', 'user1', '$2y$10$cF7IR.N8hsuB9j0KlgyiTOphr39YNDJyqVXQL.B0RE3VhY6MyoGea', 'ROLE_ADMIN'),
('31bc6fa9-5511-43be-a167-06b0fcb4d9c1', 'user2', '$2y$10$cF7IR.N8hsuB9j0KlgyiTOphr39YNDJyqVXQL.B0RE3VhY6MyoGea', 'ROLE_USER');

INSERT INTO projects(ID, LABEL, START_DATE) VALUES 
('d4aac837-e1b8-4d72-a58d-18c51e4c0403', 'Projet 1', '2025-08-15'),
('d608b610-2296-4a65-8d96-d517a66fa15b', 'Projet 2', '2025-08-18');

INSERT INTO work_types(ID, LABEL, USER_ID) VALUES 
('ad1a9154-340d-46ce-856b-43d73c574d15', 'Démolition', null),
('d9dc4d26-64d1-4a94-a4d8-c5cc64939b09', 'Nettoyage', null),
('37199c64-e20d-408c-b918-31fbe7ece0e2', 'Peinture', 'becbab08-719b-4f61-a3ec-be86b37399f7'),
('7c68b857-be7c-4fb1-a1c2-2fb8a153996c', 'Not owned', '31bc6fa9-5511-43be-a167-06b0fcb4d9c1'),
('8b902b58-7a94-4635-b1a1-1092a5de4bb3', 'Enduit', 'becbab08-719b-4f61-a3ec-be86b37399f7');

INSERT INTO works(ID, LABEL, PARENT_PROJECT_ID, WORK_TYPE_ID, START_DATE) 
VALUES 
('e02ebba1-47f6-45e8-befd-6c7505fc9142', 'Chantier 1', 'd4aac837-e1b8-4d72-a58d-18c51e4c0403', 'ad1a9154-340d-46ce-856b-43d73c574d15', '2025-08-15'),
('ad969f24-9204-47c1-ad02-8b761620d1ff', 'Chantier 2', 'd4aac837-e1b8-4d72-a58d-18c51e4c0403', 'ad1a9154-340d-46ce-856b-43d73c574d15', '2025-09-05'),
('e743cc50-f866-4a28-9b93-4e6f8ff2f687', 'Chantier 3', 'd608b610-2296-4a65-8d96-d517a66fa15b', 'd9dc4d26-64d1-4a94-a4d8-c5cc64939b09', '2025-08-18');

INSERT INTO user_project_mapping(user_id, project_id) 
VALUES 
('becbab08-719b-4f61-a3ec-be86b37399f7', 'd4aac837-e1b8-4d72-a58d-18c51e4c0403'),
('31bc6fa9-5511-43be-a167-06b0fcb4d9c1', 'd608b610-2296-4a65-8d96-d517a66fa15b');


INSERT INTO projects(ID, LABEL, START_DATE) VALUES 
('e929bda3-7e1b-4319-96bb-8b212c35ed54', 'Project 3', '2025-09-01'),
('a46fd7c8-ef03-438f-a5d1-fab3b6eeafd1', 'Project 4', '2025-09-07'),
('52cb3ec6-1b6b-4a55-83cd-652e9f4d1bcd', 'Project 5', '2025-09-10'),
('a4884a74-7d00-40c4-bb2e-574d3634272d', 'Project 6', '2025-09-13'),
('6f561236-0c1d-4c59-9adc-acce1160903b', 'Project 7', '2025-09-17'),
('76b8dff6-7d1e-4931-a27f-8f54514be5e6', 'Project 8', '2025-09-19'),
('89083255-ce95-4081-b399-08d013418035', 'Project 9', '2025-09-27'),
('e3620244-6194-4200-9a92-a3311bc6a583', 'Project 10', '2025-10-01'),
('b3bd5d6c-cbed-4ae8-8ae6-9847f5dca589', 'Project 11', '2025-10-07'),
('b44347e9-be93-49b5-a2cb-5226832f78a4', 'Project 12', '2025-10-10'),
('42aa84f5-6d03-4748-9920-0c40932ad606', 'Project 13', '2025-10-13'),
('7dd16b81-7f5f-439d-b9d5-62c448bf9dde', 'Project 14', '2025-10-17'),
('1e462973-b453-4b53-b57a-ba300b585a06', 'Project 15', '2025-10-19'),
('e5d4e36d-c64e-4b5b-9eb0-2149148d50c4', 'Project 16', '2025-10-27'),
('662c1064-e633-43c0-a1ca-042f69c7b70a', 'Project 17', '2025-10-30'),
('a6da2163-ad97-4aeb-8af5-d79e29de08dc', 'Project 18', '2025-11-01'),
('128c9965-cc8f-4736-b8c6-8464a7f045f2', 'Project 19', '2025-11-07'),
('87e7e8fd-1f4e-494f-a821-6b370afd71cb', 'Project 20', '2025-11-10'),
('a95f01ee-3ca7-433f-bc9e-83cbe60f9c0d', 'Project 21', '2025-11-13'),
('11b91d4a-e5cc-48ee-95e9-ae35a3b50d1c', 'Project 22', '2025-11-17'),
('8b99fc4b-858c-4002-9c45-9c592e288144', 'Project 23', '2025-11-19'),
('1fa44075-a333-40d7-b517-0dfa7c22adce', 'Project 24', '2025-11-27'),
('630f3f83-394d-4d5f-9047-eaf1bf10c5ba', 'Project 25', '2025-11-30'),
('d40b5463-55d8-4199-935b-56000baa7158', 'Project 26', '2025-12-01'),
('405191e4-e4da-4226-afe3-deb9429486f7', 'Project 27', '2025-12-07'),
('cf977f52-a962-4e57-ad2b-acb3c89b3a1d', 'Project 28', '2025-12-10'),
('2a7116a8-4a89-4c47-8d6e-e9892eb79f33', 'Project 29', '2025-12-13'),
('a021b5fe-cbe0-4d10-a7bc-d968f7861618', 'Project 30', '2025-12-17'),
('1c271aec-0fc3-4b86-83ed-6ec2c2253043', 'Project 31', '2025-12-19'),
('be37a7f7-1464-4cf9-a944-4d619261a95f', 'Project 32', '2025-12-27'),
('da0b9ae8-049b-4980-8ddd-37de349c2842', 'Project 33', '2025-12-30'),
('d5adf976-a6f3-4fb0-81d5-f61b07660d59', 'Project 34', '2026-01-01'),
('5b99a925-91fb-4184-a927-8104bec0c8f7', 'Project 35', '2026-01-07'),
('31d6bd74-cfd2-4ceb-b382-2e9613526f59', 'Project 36', '2026-01-12'),
('7d6f5456-b4bf-4f15-8e31-9a152b820769', 'Project 37', '2026-01-13'),
('020dcb28-57af-4893-bad6-3bb781430ed5', 'Project 38', '2026-01-17'),
('d64a9d66-bcdb-4240-8b5a-0df27e903439', 'Project 39', '2026-01-19'),
('4a1a07ec-bdc5-49a0-8f1f-ea20e52bd217', 'Project 40', '2026-01-23'),
('9586146d-6bf6-44bc-b1c8-aad87ebf9ad7', 'Project 41', '2026-01-30'),
('b0137d52-958a-4969-be95-dbfb63ea3f97', 'Project 42', '2026-02-01'),
('d2917823-45dd-4c89-b7cb-47caba93a623', 'Project 43', '2026-02-07'),
('f024f158-955d-412f-a38b-2c99f1e31a73', 'Project 44', '2026-02-10'),
('3aef0b40-5191-4f60-bf7e-2ecfeefa1f68', 'Project 45', '2026-02-13'),
('735adf59-c460-47eb-ae25-7b5cd06f4f5f', 'Project 46', '2026-02-17'),
('3b89763d-99a0-4661-87bd-f5a89badeb01', 'Project 47', '2026-02-19'),
('4280533a-e354-4a6a-a522-d27982ea007f', 'Project 48', '2026-02-27'),
('1e41105c-2d01-484f-b480-7d99c48e511c', 'Project 49', '2026-02-28'),
('bec6e887-9b07-4f55-b35d-ba5b48835240', 'Project 50', '2026-03-19'),
('ee62d278-a67e-4928-b145-0af2e80dd8b2', 'Project 51', '2026-03-27'),
('2070822b-9b6d-4b3a-9b16-47fc42e0d998', 'Project 52', '2026-03-30');


INSERT INTO user_project_mapping(user_id, project_id) 
VALUES 
('becbab08-719b-4f61-a3ec-be86b37399f7', 'e929bda3-7e1b-4319-96bb-8b212c35ed54'),
('becbab08-719b-4f61-a3ec-be86b37399f7', 'a46fd7c8-ef03-438f-a5d1-fab3b6eeafd1'),
('becbab08-719b-4f61-a3ec-be86b37399f7', '52cb3ec6-1b6b-4a55-83cd-652e9f4d1bcd'),
('becbab08-719b-4f61-a3ec-be86b37399f7', 'a4884a74-7d00-40c4-bb2e-574d3634272d'),
('becbab08-719b-4f61-a3ec-be86b37399f7', '6f561236-0c1d-4c59-9adc-acce1160903b'),
('becbab08-719b-4f61-a3ec-be86b37399f7', '76b8dff6-7d1e-4931-a27f-8f54514be5e6'),
('becbab08-719b-4f61-a3ec-be86b37399f7', '89083255-ce95-4081-b399-08d013418035'),
('becbab08-719b-4f61-a3ec-be86b37399f7', 'e3620244-6194-4200-9a92-a3311bc6a583'),
('becbab08-719b-4f61-a3ec-be86b37399f7', 'b3bd5d6c-cbed-4ae8-8ae6-9847f5dca589'),
('becbab08-719b-4f61-a3ec-be86b37399f7', 'b44347e9-be93-49b5-a2cb-5226832f78a4'),
('becbab08-719b-4f61-a3ec-be86b37399f7', '42aa84f5-6d03-4748-9920-0c40932ad606'),
('becbab08-719b-4f61-a3ec-be86b37399f7', '7dd16b81-7f5f-439d-b9d5-62c448bf9dde'),
('becbab08-719b-4f61-a3ec-be86b37399f7', '1e462973-b453-4b53-b57a-ba300b585a06'),
('becbab08-719b-4f61-a3ec-be86b37399f7', 'e5d4e36d-c64e-4b5b-9eb0-2149148d50c4'),
('becbab08-719b-4f61-a3ec-be86b37399f7', '662c1064-e633-43c0-a1ca-042f69c7b70a'),
('becbab08-719b-4f61-a3ec-be86b37399f7', 'a6da2163-ad97-4aeb-8af5-d79e29de08dc'),
('becbab08-719b-4f61-a3ec-be86b37399f7', '128c9965-cc8f-4736-b8c6-8464a7f045f2'),
('becbab08-719b-4f61-a3ec-be86b37399f7', '87e7e8fd-1f4e-494f-a821-6b370afd71cb'),
('becbab08-719b-4f61-a3ec-be86b37399f7', 'a95f01ee-3ca7-433f-bc9e-83cbe60f9c0d'),
('becbab08-719b-4f61-a3ec-be86b37399f7', '11b91d4a-e5cc-48ee-95e9-ae35a3b50d1c'),
('becbab08-719b-4f61-a3ec-be86b37399f7', '8b99fc4b-858c-4002-9c45-9c592e288144'),
('becbab08-719b-4f61-a3ec-be86b37399f7', '1fa44075-a333-40d7-b517-0dfa7c22adce'),
('becbab08-719b-4f61-a3ec-be86b37399f7', '630f3f83-394d-4d5f-9047-eaf1bf10c5ba'),
('becbab08-719b-4f61-a3ec-be86b37399f7', 'd40b5463-55d8-4199-935b-56000baa7158'),
('becbab08-719b-4f61-a3ec-be86b37399f7', '405191e4-e4da-4226-afe3-deb9429486f7'),
('becbab08-719b-4f61-a3ec-be86b37399f7', 'cf977f52-a962-4e57-ad2b-acb3c89b3a1d'),
('becbab08-719b-4f61-a3ec-be86b37399f7', '2a7116a8-4a89-4c47-8d6e-e9892eb79f33'),
('becbab08-719b-4f61-a3ec-be86b37399f7', 'a021b5fe-cbe0-4d10-a7bc-d968f7861618'),
('becbab08-719b-4f61-a3ec-be86b37399f7', '1c271aec-0fc3-4b86-83ed-6ec2c2253043'),
('becbab08-719b-4f61-a3ec-be86b37399f7', 'be37a7f7-1464-4cf9-a944-4d619261a95f'),
('becbab08-719b-4f61-a3ec-be86b37399f7', 'da0b9ae8-049b-4980-8ddd-37de349c2842'),
('becbab08-719b-4f61-a3ec-be86b37399f7', 'd5adf976-a6f3-4fb0-81d5-f61b07660d59'),
('becbab08-719b-4f61-a3ec-be86b37399f7', '5b99a925-91fb-4184-a927-8104bec0c8f7'),
('becbab08-719b-4f61-a3ec-be86b37399f7', '31d6bd74-cfd2-4ceb-b382-2e9613526f59'),
('becbab08-719b-4f61-a3ec-be86b37399f7', '7d6f5456-b4bf-4f15-8e31-9a152b820769'),
('becbab08-719b-4f61-a3ec-be86b37399f7', '020dcb28-57af-4893-bad6-3bb781430ed5'),
('becbab08-719b-4f61-a3ec-be86b37399f7', 'd64a9d66-bcdb-4240-8b5a-0df27e903439'),
('becbab08-719b-4f61-a3ec-be86b37399f7', '4a1a07ec-bdc5-49a0-8f1f-ea20e52bd217'),
('becbab08-719b-4f61-a3ec-be86b37399f7', '9586146d-6bf6-44bc-b1c8-aad87ebf9ad7'),
('becbab08-719b-4f61-a3ec-be86b37399f7', 'b0137d52-958a-4969-be95-dbfb63ea3f97'),
('becbab08-719b-4f61-a3ec-be86b37399f7', 'd2917823-45dd-4c89-b7cb-47caba93a623'),
('becbab08-719b-4f61-a3ec-be86b37399f7', 'f024f158-955d-412f-a38b-2c99f1e31a73'),
('becbab08-719b-4f61-a3ec-be86b37399f7', '3aef0b40-5191-4f60-bf7e-2ecfeefa1f68'),
('becbab08-719b-4f61-a3ec-be86b37399f7', '735adf59-c460-47eb-ae25-7b5cd06f4f5f'),
('becbab08-719b-4f61-a3ec-be86b37399f7', '3b89763d-99a0-4661-87bd-f5a89badeb01'),
('becbab08-719b-4f61-a3ec-be86b37399f7', '4280533a-e354-4a6a-a522-d27982ea007f'),
('becbab08-719b-4f61-a3ec-be86b37399f7', '1e41105c-2d01-484f-b480-7d99c48e511c'),
('becbab08-719b-4f61-a3ec-be86b37399f7', 'bec6e887-9b07-4f55-b35d-ba5b48835240'),
('becbab08-719b-4f61-a3ec-be86b37399f7', 'ee62d278-a67e-4928-b145-0af2e80dd8b2'),
('becbab08-719b-4f61-a3ec-be86b37399f7', '2070822b-9b6d-4b3a-9b16-47fc42e0d998');