CREATE TABLE IF NOT EXISTS topics(
                                     id VARCHAR(255) PRIMARY KEY,
                                     title VARCHAR(255) NOT NULL,
                                     description TEXT NOT NULL,
                                     created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                                     status VARCHAR(255) DEFAULT 'active',
                                     user_id VARCHAR(255) NOT NULL,
                                     FOREIGN KEY (user_id) REFERENCES users(id),
                                     course_id INT NOT NULL,
                                     FOREIGN KEY (course_id) REFERENCES courses(id)
)