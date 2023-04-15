package com.example.kurs.service;

import com.example.kurs.domain.entity.Storage;
import com.example.kurs.dto.StorageDto;
import com.example.kurs.mapper.CommonMapper;
import com.example.kurs.repository.StorageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class StorageService {
    private final StorageRepository storageRepository;

    @Autowired
    public StorageService(StorageRepository storageRepository) {
        this.storageRepository = storageRepository;
    }

    public StorageDto getByLibraryLocation(String libraryLocation) {
        return storageRepository.findByLibraryLocation(libraryLocation)
                .map(storage -> CommonMapper.map(storage, StorageDto.class))
                .orElse(null);
    }

    @Transactional
    public StorageDto create(StorageDto storageDtoToCreate, String oldLibraryLocation) {
        if (!ObjectUtils.isEmpty(oldLibraryLocation)) {
            var createdStorage = getByLibraryLocation(oldLibraryLocation);

            if (!ObjectUtils.isEmpty(createdStorage)) {
                storageDtoToCreate.setId(createdStorage.getId());
            }
        }
        return Optional.of(storageDtoToCreate)
                .map(storageDto -> CommonMapper.map(storageDto, Storage.class))
                .map(storageRepository::save)
                .map(storage -> CommonMapper.map(storage, StorageDto.class))
                .orElse(null);
    }
}
