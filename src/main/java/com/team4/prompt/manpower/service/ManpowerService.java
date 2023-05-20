package com.team4.prompt.manpower.service;

import com.team4.prompt.manpower.domain.ManPower;
import com.team4.prompt.manpower.repository.ManpowerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ManpowerService {
    private final ManpowerRepository manpowerRepository;

    public ManPower findManpowerById(Long id) {
        return manpowerRepository.findById(id).orElseThrow(() -> new IllegalArgumentException(""));
    }
}
