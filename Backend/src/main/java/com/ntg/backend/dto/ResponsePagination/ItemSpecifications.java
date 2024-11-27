package com.ntg.backend.dto.ResponsePagination;

import com.ntg.backend.entity.Item;
import org.springframework.data.jpa.domain.Specification;

public class ItemSpecifications {
    public static Specification<Item> hasType(Item.ClothingType type) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("type"), type);
    }

    public static Specification<Item> hasSize(Item.ClothingSize size) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("size"), size);
    }

    public static Specification<Item> hasState(Item.ItemState state) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("state"), state);
    }

    public static Specification<Item> hasGenderSuitability(Item.GenderSuitability genderSuitability) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("genderSuitability"), genderSuitability);
    }

    public static Specification<Item> hasStatus(Item.ItemStatus status) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("status"), status);
    }
}



