package com.alkemy.ong.model.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import javax.validation.constraints.Email;

@Entity
@Table(name = "contacts")
@Getter
@Setter
@Where(clause = "deleted=false")
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

    private boolean deletedAt = Boolean.FALSE;
}
