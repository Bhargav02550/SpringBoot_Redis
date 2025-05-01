package com.example.springboot_redis;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Retrieve a user by ID from the database for the first time.
     * This will trigger a cache miss and fetch the user from the database.
     * Later calls with the same ID will fetch the user from the cache.
     * The data in the cache will be saved with the key "users::id".
     * By using the command keys users* in the Redis CLI, you can see the cache keys.
     * We can also use the command get "users::<id>" to get data from the cache based on the key.
     * */
    @Cacheable(value = "users", key = "#id")
    public UserEntity getUserById(Long id) {
        System.out.println("Fetching from DB...");
        return userRepository.findById(id).orElse(null);
    }

    @CachePut(value = "users", key = "#userEntity.id")
    public UserEntity saveUser(UserEntity userEntity) {
        return userRepository.save(userEntity);
    }

    @CacheEvict(value = "users", key = "#id")
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    public UserEntity addUser(UserEntity userEntity) {
        return userRepository.save(userEntity);
    }

}
