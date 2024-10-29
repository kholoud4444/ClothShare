package com.ntg.backend.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Entity
public class Needy extends User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long needyId;

    @OneToMany(mappedBy = "needy", cascade = CascadeType.ALL)
    private List<Request> requests;
}
