package com.ntg.backend.service;

import com.ntg.backend.dto.NeedyDto;
import com.ntg.backend.dto.VolunteerDto;

import java.util.List;

public interface NeedyService {
    NeedyDto createNeedy(NeedyDto needyDto);
    NeedyDto updateNeedy(NeedyDto needyDto,long id);
    List<NeedyDto> getAllNeedy();
    NeedyDto getNeedyById(long id);
    void deleteNeedy(long id);

}
