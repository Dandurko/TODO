package com.portfolio.TODO.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import java.sql.Timestamp;


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

    @Column(name = "created_date", columnDefinition = "DATETIME", nullable = false, updatable = false)
    @CreatedDate
    private Timestamp createdDate;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "fk_user_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private User user;








}
