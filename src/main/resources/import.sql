INSERT INTO `pizza`(`price`, `created_at`, `description`, `image`, `name`) VALUES (10.00,'2023-06-22 16:44:00','La pizza Margherita è la tipica pizza napoletana, condita con pomodoro, mozzarella, basilico fresco, sale e olio','/img/pizza-margherita.jpg','Pizza margherita')
INSERT INTO `pizza`(`price`, `created_at`, `description`, `image`, `name`) VALUES (10.00,'2023-06-22 16:44:00','La pizza Margherita è la tipica pizza napoletana, condita con pomodoro, mozzarella, basilico fresco, sale e olio','/img/pizza-fume.jpg','Pizza fumè')
INSERT INTO `pizza`(`price`, `created_at`, `description`, `image`, `name`) VALUES (10.00,'2023-06-22 16:44:00','La pizza Margherita è la tipica pizza napoletana, condita con pomodoro, mozzarella, basilico fresco, sale e olio','/img/pizza-capricciosa.jpg','Pizza capricciosa')
INSERT INTO `pizza`(`price`, `created_at`, `description`, `image`, `name`) VALUES (10.00,'2023-06-22 16:44:00','La pizza Margherita è la tipica pizza napoletana, condita con pomodoro, mozzarella, basilico fresco, sale e olio','/img/pizzaquattrostagioni.jpg','Pizza quattro stagioni')
INSERT INTO `pizza`(`price`, `created_at`, `description`, `image`, `name`) VALUES (10.00,'2023-06-22 16:44:00','La pizza Margherita è la tipica pizza napoletana, condita con pomodoro, mozzarella, basilico fresco, sale e olio','/img/bufalina.jpeg','Pizza bufalina')

INSERT INTO `offers`(`end_date`, `pizza_id`, `start_date`, `title`) VALUES ('2023-07-22','1','2023-06-29','Offerta 1')
INSERT INTO `offers`(`end_date`, `pizza_id`, `start_date`, `title`) VALUES ('2023-07-22','1','2023-06-29','Offerta 2')
INSERT INTO `offers`(`end_date`, `pizza_id`, `start_date`, `title`) VALUES ('2023-07-22','1','2023-06-29','Offerta 3')
INSERT INTO `offers`(`end_date`, `pizza_id`, `start_date`, `title`) VALUES ('2023-07-22','2','2023-06-29','Offerta 1')
INSERT INTO `offers`(`end_date`, `pizza_id`, `start_date`, `title`) VALUES ('2023-07-22','2','2023-06-29','Offerta 2')
INSERT INTO `offers`(`end_date`, `pizza_id`, `start_date`, `title`) VALUES ('2023-07-22','3','2023-06-29','Offerta 1')
INSERT INTO `offers`(`end_date`, `pizza_id`, `start_date`, `title`) VALUES ('2023-07-22','3','2023-06-29','Offerta 2')
INSERT INTO `offers`(`end_date`, `pizza_id`, `start_date`, `title`) VALUES ('2023-07-22','3','2023-06-29','Offerta 3')
INSERT INTO `offers`(`end_date`, `pizza_id`, `start_date`, `title`) VALUES ('2023-07-22','3','2023-06-29','Offerta 4')
INSERT INTO `offers`(`end_date`, `pizza_id`, `start_date`, `title`) VALUES ('2023-07-22','4','2023-06-29','Offerta 1')
INSERT INTO `offers`(`end_date`, `pizza_id`, `start_date`, `title`) VALUES ('2023-07-22','4','2023-06-29','Offerta 2')

INSERT INTO `ingredients`(`name`) VALUES ('Farina')
INSERT INTO `ingredients`(`name`) VALUES ('Pomodoro')
INSERT INTO `ingredients`(`name`) VALUES ('Mozzarella')
INSERT INTO `ingredients`(`name`) VALUES ('Prosciutto')
INSERT INTO `ingredients`(`name`) VALUES ('Funghi')

INSERT INTO `pizza_ingredient`(`ingredient_id`, `pizza_id`) VALUES ('1', '1')
INSERT INTO `pizza_ingredient`(`ingredient_id`, `pizza_id`) VALUES ('2', '1')
INSERT INTO `pizza_ingredient`(`ingredient_id`, `pizza_id`) VALUES ('3', '1')
INSERT INTO `pizza_ingredient`(`ingredient_id`, `pizza_id`) VALUES ('4', '1')
INSERT INTO `pizza_ingredient`(`ingredient_id`, `pizza_id`) VALUES ('1', '2')
INSERT INTO `pizza_ingredient`(`ingredient_id`, `pizza_id`) VALUES ('2', '2')
INSERT INTO `pizza_ingredient`(`ingredient_id`, `pizza_id`) VALUES ('1', '3')
INSERT INTO `pizza_ingredient`(`ingredient_id`, `pizza_id`) VALUES ('4', '3')
INSERT INTO `pizza_ingredient`(`ingredient_id`, `pizza_id`) VALUES ('5', '3')
INSERT INTO `pizza_ingredient`(`ingredient_id`, `pizza_id`) VALUES ('1', '4')
INSERT INTO `pizza_ingredient`(`ingredient_id`, `pizza_id`) VALUES ('2', '4')
INSERT INTO `pizza_ingredient`(`ingredient_id`, `pizza_id`) VALUES ('5', '4')

INSERT INTO `users`(`email`, `password`, `username`) VALUES ('user@gmail.com','{noop}user','user')
INSERT INTO `users`(`email`, `password`, `username`) VALUES ('admin@gmail.com','{noop}admin','admin')

INSERT INTO `roles`(`name`) VALUES ('USER')
INSERT INTO `roles`(`name`) VALUES ('ADMIN')

INSERT INTO `users_roles`(`user_id`, `roles_id`) VALUES (1, 1)
INSERT INTO `users_roles`(`user_id`, `roles_id`) VALUES (2, 2)






