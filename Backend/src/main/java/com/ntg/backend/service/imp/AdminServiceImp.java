package com.ntg.backend.service.imp;

import com.ntg.backend.Mapper.ItemMapper;
import com.ntg.backend.dto.ResponsePagination.PageDto;
import com.ntg.backend.dto.responseDto.ItemDetailsWithVolunteerName;
import com.ntg.backend.entity.Admin;
import com.ntg.backend.entity.Item;
import com.ntg.backend.repository.AdminRepo;
import com.ntg.backend.repository.ItemRepo;
import com.ntg.backend.service.AdminService;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AdminServiceImp implements AdminService {

    private final ItemRepo itemRepo;
    private final ItemMapper itemMapper;
    private final AdminRepo adminRepo;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public AdminServiceImp(ItemRepo itemRepo, ItemMapper itemMapper, AdminRepo adminRepo, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.itemRepo = itemRepo;
        this.itemMapper = itemMapper;
        this.adminRepo = adminRepo;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public PageDto<ItemDetailsWithVolunteerName> GetAllItemDetailsWithVolunteerNameList(int PageNo, int PageSize) {
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

    @Transactional
    @Override
    public void CreateAdmin() {

        if (!adminRepo.existsByEmail("admin@gmail.com")) {
            Admin admin = new Admin();
            admin.setFirstName("Admin");
            admin.setLastName("admin");
            admin.setEmail("admin@gmail.com");
            LocalDate birthday = LocalDate.of(2002, 8, 1); // Format: year, month, day
            admin.setBirthDate(birthday);
            admin.setGender("Male");
            admin.setEmailVerified(true);
            admin.setPassword(bCryptPasswordEncoder.encode("Admin@12345678")); // Encrypt password
            admin.setRole("admin");
            admin.setLocation("cairo");
            admin.setNationalId("30208011300353");
            admin.setPhone("01003197262");

            adminRepo.save(admin); // Save admin to database
            System.out.println("Admin account created.");
        } else {
            System.out.println("Admin account already exists.");
        }
    }
}



