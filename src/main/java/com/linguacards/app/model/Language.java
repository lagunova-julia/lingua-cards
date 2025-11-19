package com.linguacards.app.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import static jakarta.persistence.GenerationType.IDENTITY;


@Entity
@Table(name = "languages")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Language implements BaseEntity {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @NotBlank
    @Column(unique = true, length = 3)
    private String code;

    @NotBlank
    @Column(length = 20)
    private String name;
}
