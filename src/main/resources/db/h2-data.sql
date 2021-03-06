INSERT INTO PRODUCTS (product_name, in_stock, price, description)
VALUES ('Intelligent Wooden Watch', 80, 52.11, 'Color: black, material: Beauty & Kids, made in Malaysia'),
       ('Practical Granite Clock', 88, 98.31, 'Color: tan, material: Books, made in Saint Vincent and the Grenadines'),
       ('Synergistic Cotton Bottle', 75, 47.66, 'Color: black, material: Tools, made in Slovenia'),
       ('Fantastic Silk Wallet', 97, 23.95, 'Color: lavender, material: Jewelry & Outdoors, made in El Salvador'),
       ('Sleek Cotton Shirt', 24, 44.39, 'Color: ivory, material: Beauty, made in Morocco'),
       ('Ergonomic Paper Watch', 92, 75.67, 'Color: indigo, material: Automotive, Beauty & Computers, made in Andorra'),
       ('Practical Marble Bench', 78, 81.17, 'Color: orchid, material: Electronics, made in Chad'),
       ('Incredible Bronze Clock', 74, 72.02, 'Color: mint green, material: Outdoors, made in Belize'),
       ('Small Concrete Knife', 84, 23.74, 'Color: lavender, material: Automotive, Baby & Beauty, made in Italy'),
       ('Intelligent Plastic Shirt', 72, 97.05, 'Color: green, material: Baby & Music, made in Argentina'),
       ('Synergistic Wool Knife', 37, 76.91, 'Color: ivory, material: Garden & Industrial, made in Australia'),
       ('Incredible Plastic Bag', 2, 73.46, 'Color: olive, material: Grocery & Tools, made in Netherlands'),
       ('Small Bronze Keyboard', 64, 44.19, 'Color: olive, material: Books & Kids, made in Armenia'),
       ('Ergonomic Silk Car', 81, 90.92, 'Color: green, material: Health, made in Tajikistan'),
       ('Awesome Steel Bottle', 77, 14.73, 'Color: mint green, material: Automotive, made in Israel'),
       ('Awesome Bronze Gloves', 70, 6.67, 'Color: lime, material: Clothing, Grocery & Toys, made in Austria'),
       ('Sleek Silk Car', 25, 39.99, 'Color: indigo, material: Jewelry & Movies, made in Nigeria'),
       ('Enormous Leather Bag', 74, 45.71, 'Color: fuchsia, material: Games & Outdoors, made in Bahrain'),
       ('Intelligent Granite Bag', 85, 7.36, 'Color: fuchsia, material: Computers, Music & Shoes, made in Kuwait'),
       ('Mediocre Wool Computer', 88, 87.99, 'Color: white, material: Books, Computers & Shoes, made in Benin'),
       ('Incredible Aluminum Watch', 58, 42.55, 'Color: silver, material: Automotive, made in Comoros'),
       ('Synergistic Wooden Shoes', 37, 29.87, 'Color: purple, material: Toys, made in Belgium'),
       ('Aerodynamic Copper Chair', 75, 81.25,
        'Color: sky blue, material: Automotive, Jewelry & Shoes, made in Portugal'),
       ('Small Concrete Shirt', 0, 51.67, 'Color: fuchsia, material: Games, Home & Movies, made in Uruguay'),
       ('Small Silk Watch', 24, 2.51, 'Color: white, material: Baby, made in Slovakia'),
       ('Durable Bronze Shoes', 78, 39.42, 'Color: silver, material: Health, made in Liechtenstein'),
       ('Lightweight Leather Shirt', 24, 67.88, 'Color: lime, material: Health & Industrial, made in Tunisia'),
       ('Ergonomic Paper Lamp', 36, 55.26, 'Color: green, material: Automotive, made in Mongolia'),
       ('Ergonomic Iron Table', 84, 33.7, 'Color: red, material: Outdoors, made in Albania'),
       ('Practical Silk Knife', 60, 97.53, 'Color: turquoise, material: Automotive & Sports, made in Peru');


INSERT INTO USER (id, username, password, phone, zip, avatar)
VALUES (1, 'admin', '{bcrypt}$2y$12$w7I7MEqU245PnWr9l29Qr.RlXMt2nIDyDGakaxYlujlk2v0yQjj6G', '+37060011111', 'LT-01110', 'https://i1.sndcdn.com/avatars-000563163222-0mmr4k-t500x500.jpg'),
       (2, 'JurgisTheGeorge', 'jurgisthegeorge1', '+37060011111', 'LT-01110',
        'https://i1.sndcdn.com/avatars-000563163222-0mmr4k-t500x500.jpg'),
       (3, 'iron_lady', 'ironlady1', '+37060022222', 'LT-02220',
        'https://img.freepik.com/free-vector/comic-pretty-girl-with-gasses-halftone-background_225004-835.jpg'),
       (4, 'mrpresident', 'mrpresident1', '+37060033333', 'LT-03330',
        'https://i1.sndcdn.com/avatars-000739602577-f2zecu-t500x500.jpg'),
       (5, 'gulag2021', '{bcrypt}$2y$12$aGGM6RbiVGHRwESqJfNnm.pWl.S1V/Nj8lcnb2shP4yxBOPB6YwN2', '+37060044444', 'LT-04440',
        'https://avatarfiles.alphacoders.com/224/224752.png');

        INSERT INTO ROLE (id, role_name) VALUES
        (1, 'ADMIN'),
        (2, 'USER');

        INSERT INTO USER_ROLE (user_id, role_id) VALUES
        (1,1),
        (1,2),
        (2,2),
        (3,2),
        (4,2),
        (5,2);