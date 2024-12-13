db = db.getSiblingDB('mongo-db');
db.createUser({
  user: "mongo",
  pwd: "mongo",
  roles: [{ role: "readWrite", db: "mongo-db" }]
});

db.createCollection('users');