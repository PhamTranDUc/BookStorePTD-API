package com.BookStorePTD.BookStorePTD.models;

import jakarta.persistence.*;
import lombok.*;
@Entity
@Table(name = "categories")
@Data
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

}
