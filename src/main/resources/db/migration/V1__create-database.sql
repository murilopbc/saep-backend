-- users table
CREATE TABLE IF NOT EXISTS users (
    id_user INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL,
    password VARCHAR(100) NOT NULL
);

-- tasks table
CREATE TABLE IF NOT EXISTS tasks (
    id_task INT AUTO_INCREMENT PRIMARY KEY,
    task_description VARCHAR(100) NOT NULL,
    sector VARCHAR(50) NOT NULL,
    task_status VARCHAR(50) NOT NULL,
    priority VARCHAR(50) NOT NULL,
    registered_date DATETIME DEFAULT CURRENT_TIMESTAMP,
    id_user INT NOT NULL,
    FOREIGN KEY (id_user) REFERENCES users(id_user)
);


