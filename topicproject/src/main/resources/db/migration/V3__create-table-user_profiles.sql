CREATE TABLE IF NOT EXISTS user_profiles(
    id SERIAL PRIMARY KEY,
    user_id VARCHAR(255) NOT NULL,
    profile_id VARCHAR(255) NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users(id),
    FOREIGN KEY (profile_id) REFERENCES profiles(id)
)