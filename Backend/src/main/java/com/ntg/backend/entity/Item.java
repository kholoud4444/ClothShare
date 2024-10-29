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
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long itemId;

    private String type;
    private String size;
    private String state;
    private String genderSuitability;
    private String imgUrl;
    private String description;
    private String status;

    @ManyToOne
    @JoinColumn(name = "volunteer_id")
    private Volunteer volunteer;

    @OneToMany(mappedBy = "item", cascade = CascadeType.ALL)
    private List<Request> requests;

}
