package gist.pilldispenser.common.utils;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
@RequiredArgsConstructor
public class RedisUtils {

    private final RedisTemplate<String, String> redisTemplate;

    public void saveToken(String key, String token, Long expiresIn) {
        redisTemplate.opsForValue().set(key, token);
        redisTemplate.expire(key, expiresIn, TimeUnit.SECONDS);
    }

    public String findToken(String key) {
        return redisTemplate.opsForValue().get(key);
    }

}
