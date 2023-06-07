package com.portfolio.TODO.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name="tasks")
@Data
@Builder
@Setter
@Getter
public class Task {


    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;
    @Column(name="name")
    public String name;
    @Column(name="deleted")
    private boolean deleted;

    @Column(name = "created_date", columnDefinition = "DATETIME", nullable = false, updatable = false)
    @CreatedDate
    private Timestamp createdDate;

    @Column(name = "last_update", columnDefinition = "DATETIME", nullable = false)
    @LastModifiedDate
    private Timestamp lastUpdate;







}
