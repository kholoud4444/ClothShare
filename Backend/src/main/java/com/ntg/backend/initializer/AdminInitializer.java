package com.ntg.backend.initializer;

import com.ntg.backend.service.imp.AdminServiceImp;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

@Component
public class AdminInitializer {

    private  final AdminServiceImp adminService;

    public AdminInitializer(AdminServiceImp adminService) {
        this.adminService = adminService;
    }

    @PostConstruct
    public void init() {
        adminService.CreateAdmin();
    }
}
