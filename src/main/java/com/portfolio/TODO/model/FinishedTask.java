package com.portfolio.TODO.model;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.sql.Timestamp;
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name="finished_tasks")
@Data
@Builder
@Setter
@Getter
public class FinishedTask {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;
    @Column(name="name")
    public String name;

    @Column(name = "created_date", columnDefinition = "DATETIME", nullable = false, updatable = false)
    @CreatedDate
    private Timestamp createdDate;

    @Column(name = "finished_date", columnDefinition = "DATETIME", nullable = false)
    @LastModifiedDate
    private Timestamp finishedDate;

}
