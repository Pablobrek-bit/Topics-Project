CREATE TABLE IF NOT EXISTS responses(
    id VARCHAR(255) PRIMARY KEY,
    message TEXT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    solution VARCHAR(255),
    user_id VARCHAR(255),
    topic_id VARCHAR(255),
    FOREIGN KEY (user_id) REFERENCES users(id),
    FOREIGN KEY (topic_id) REFERENCES topics(id)
)