package com.courier.tracking.implementation;

import com.courier.tracking.entity.Store;
import com.courier.tracking.repository.StoreRepository;
import com.courier.tracking.service.StoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StoreServiceImpl implements StoreService {
    private final StoreRepository storeRepository;

    @Override
    public List<Store> getAllStores() {
        return storeRepository.findAll();
    }
}
