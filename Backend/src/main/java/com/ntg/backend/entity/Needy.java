package com.ntg.backend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "Needy")
public class Needy extends User {


    @OneToMany(mappedBy = "needy", cascade = CascadeType.ALL)
    private List<Request> requests;
}
