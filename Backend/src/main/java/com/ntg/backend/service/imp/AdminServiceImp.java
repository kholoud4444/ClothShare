package com.ntg.backend.service.imp;

import com.ntg.backend.Mapper.ItemMapper;
import com.ntg.backend.dto.ResponsePagination.PageDto;
import com.ntg.backend.dto.responseDto.ItemDetailsWithVolunteerName;
import com.ntg.backend.entity.Item;
import com.ntg.backend.repository.ItemRepo;
import com.ntg.backend.service.AdminService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AdminServiceImp implements AdminService {

    private final ItemRepo itemRepo;
    private final ItemMapper itemMapper;

    public AdminServiceImp(ItemRepo itemRepo, ItemMapper itemMapper) {
        this.itemRepo = itemRepo;
        this.itemMapper = itemMapper;
    }

    @Override
    public PageDto<ItemDetailsWithVolunteerName> GetAllItemDetailsWithVolunteerNameList(int PageNo,int PageSize) {
        Pageable pageable = PageRequest.of(PageNo, PageSize);
        Page<Item> itemsPage = itemRepo.findAll(pageable);
        List<ItemDetailsWithVolunteerName> itemDetails = itemsPage.getContent().stream()
                .map(itemMapper::mapToItemDetailsWithVolunteerName)
                .collect(Collectors.toList());

        // Create and return a PageDto with the mapped content and pagination details
        return new PageDto<>(
                itemDetails,
                itemsPage.getTotalElements(),
                itemsPage.getTotalPages(),
                itemsPage.getNumber(),
                itemsPage.getSize(),
                itemsPage.isLast()
        );
    }

}
