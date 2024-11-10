package com.ntg.backend.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import jakarta.persistence.*;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "requests", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"needy_id", "item_id"})
})
public class Request {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long requestId;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "needy_id")
    private Needy needy;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "item_id")
    private Item item;

    @Column(nullable = false)
    private LocalDate date;

    @NotNull(message = "Status is required")
    @Enumerated(EnumType.STRING)
    private RequestStatus status;

    @Size(max = 500, message = "Reason should not exceed 500 characters")
    private String reason;

    @PrePersist
    protected void onCreate() {
        this.date = LocalDate.now(); // Set the current date
    }

    @Getter
    public enum RequestStatus {
        مرفوض,
        قيد_المراجعه,
        تم_الموافقه
    }
}
