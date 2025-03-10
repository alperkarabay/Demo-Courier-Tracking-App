package com.courier.tracking.handler;


import com.courier.tracking.entity.Courier;
import com.courier.tracking.entity.Store;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
@RequiredArgsConstructor
@Slf4j
public class RedisStoreEntryCheckHandler extends StoreEntryCheckHandler {
    private final StringRedisTemplate redisTemplate;
    private static final String REDIS_KEY_PREFIX = "store_entry:";
    private static final long EXPIRATION_TIME_SECONDS = 60;

    @Override
    public boolean checkEntry(Courier courier, Store store) {
        String key = REDIS_KEY_PREFIX + courier.getId() + ":" + store.getId();

        //If the key exists in Redis, the courier is already in the store's radius
        if (Boolean.TRUE.equals(redisTemplate.hasKey(key))) {
            return false;
        }

        //Set the key in Redis
        redisTemplate.opsForValue().set(key, "logged", EXPIRATION_TIME_SECONDS, TimeUnit.SECONDS);

        //Move to the next handler
        if (nextHandler != null) {
            return nextHandler.checkEntry(courier, store);
        }
        return true;
    }
}
