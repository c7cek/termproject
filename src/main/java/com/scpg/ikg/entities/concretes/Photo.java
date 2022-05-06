package com.scpg.ikg.entities.concretes;

import com.scpg.ikg.core.entities.IEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


@Data
@Entity
@Table(name = "photos")
@NoArgsConstructor
@AllArgsConstructor
public class Photo  implements IEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotBlank
    @NotNull
    private String imgUrl;

    @ManyToOne
    @JoinColumn(name = "solution_id")
    private Solution solution;


}
