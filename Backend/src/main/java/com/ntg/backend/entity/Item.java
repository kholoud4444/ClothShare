package com.ntg.backend.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import jakarta.persistence.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "Item")
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long itemId;

    @NotNull(message = "Type is required")
    @Enumerated(EnumType.STRING)
    private ClothingType type;

    @NotNull(message = "Size is required")
    @Enumerated(EnumType.STRING)
    private ClothingSize size;

    @NotNull(message = "State is required")
    @Enumerated(EnumType.STRING)
    private ItemState state;

    @NotNull(message = "Gender suitability is required")
    @Enumerated(EnumType.STRING)
    private GenderSuitability genderSuitability;

//    @NotBlank(message = "Image URL is required")
//    @Size(max = 255, message = "Image URL should not exceed 255 characters")
    private String imageUrl;

    @Size(max = 500, message = "Description should not exceed 500 characters")
    private String description;

    @NotNull(message = "Status is required")
    @Enumerated(EnumType.STRING)
    private ItemStatus status;

    @ManyToOne
    @JoinColumn(name = "volunteer_id")
    @JsonIgnore
    private Volunteer volunteer;

    @OneToMany(mappedBy = "item", cascade = CascadeType.ALL)
    private List<Request> requests;


    // Enums with Arabic values directly
    @Getter
    public enum ClothingType {
        قميص,
        فستان,
        بنطلون,
        تنورة,
        جاكيت,
        معطف
    }

    @Getter
    public enum ClothingSize {
        صغير,
        متوسط,
        كبير,
        كبير_جداً
    }

    @Getter
    public enum ItemState {
        جديد,
        مستعمل
    }

    @Getter
    public enum GenderSuitability {
        ذكر,
        أنثى,
        مشترك
    }

    @Getter
    public enum ItemStatus {
        معلق,
        قيد_المراجعه,
        تم_الموافقه
    }
}
