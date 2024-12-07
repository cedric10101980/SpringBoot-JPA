CREATE TABLE IF NOT EXISTS "users" (
  "id" INTEGER PRIMARY KEY,
  "name" varchar NOT NULL
);

INSERT INTO users (id, name) VALUES (1, 'user1')
ON CONFLICT (id) DO NOTHING;

INSERT INTO users (id, name) VALUES (2, 'user2')
ON CONFLICT (id) DO NOTHING;