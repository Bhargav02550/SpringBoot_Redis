package com.example.springboot_redis;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor

/**
 * UserEntity class represents a user entity in the database.
 * It is annotated with @Entity to indicate that it is a JPA entity.
 * The class contains fields for id, name, and email, along with
 * appropriate annotations for serialization and deserialization.
 */
public class UserEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
}
