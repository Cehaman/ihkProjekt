package org.customportal.ihkprojekt.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String username;
    private String email;

    @OneToMany(mappedBy = "user")
    //@JsonManagedReference(value= "user-favorite")
    private List<Favorite> favoriteSet;

    @OneToMany(mappedBy = "user")
    //@JsonManagedReference(value = "user-customizing")
    private List<Customizing> customizings;

    @OneToMany(mappedBy = "user")
    //@JsonManagedReference(value = "user-comment")
    private List<Comment> comments;


}
