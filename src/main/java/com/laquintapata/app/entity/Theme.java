package com.laquintapata.app.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Entity
@Table(name = "themes")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class Theme {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El tema es obligatório")
    @Size(min = 2, max = 150, message = "El tema debe tener entre 2 y 150 caracteres")
    @Column(nullable = false, unique = true, length = 150)
    private String name;

    @Size(max = 500, message = "La describición no puede tener más de 500 caracteres")
    @Column(length = 500)
    private String description;

}
