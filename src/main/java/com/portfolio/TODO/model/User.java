package com.portfolio.TODO.model;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import java.sql.Timestamp;


@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Table(name="users")
public class User {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name="username")
    private String username;
    @Column(name="password")
    private String password;
    @Column(name = "created_date", columnDefinition = "DATETIME", nullable = false, updatable = false)
    @CreatedDate
    private Timestamp createdDate;
}
