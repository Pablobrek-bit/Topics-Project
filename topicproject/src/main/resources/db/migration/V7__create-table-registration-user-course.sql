CREATE TABLE IF NOT EXISTS registration_user_course (
    id SERIAL PRIMARY KEY,
    user_id VARCHAR(255) NOT NULL,
    course_id INTEGER NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES users(id),
    FOREIGN KEY (course_id) REFERENCES courses(id),
    UNIQUE (user_id, course_id)
)