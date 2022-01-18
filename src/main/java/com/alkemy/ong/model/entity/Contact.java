package com.alkemy.ong.model.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import javax.validation.constraints.Email;

@Entity
@Table(name = "contacts")
@Getter
@Setter
@SQLDelete(sql = "UPDATE CATEGORIES SET SOFT_DELETE=true WHERE id=?")
@Where(clause = "SOFT_DELETE=false")

public class Contact {

    @Id
    @Column(name = "CONTACT_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private Long phone;

    @Email
    private String email;

    private String message;

    @Column(name = "SOFT_DELETE")
    private boolean softDelete;

}
