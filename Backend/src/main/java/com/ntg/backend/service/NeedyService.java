package com.ntg.backend.service;

import com.ntg.backend.dto.NeedyDto;
import com.ntg.backend.entity.Needy;

import java.util.List;

public interface NeedyService {
    Needy createNeedy(NeedyDto needyDto);
    Needy updateNeedy(NeedyDto needyDto,long id);
    List<Needy> getAllNeedy();
    Needy getNeedyById(long id);
    void deleteNeedy(long id);

}
