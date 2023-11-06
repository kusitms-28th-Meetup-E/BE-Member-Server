package gwangjang.server.global.security.jwt.redis;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.concurrent.TimeUnit;

@Slf4j
@Repository
public class RedisUtil {

    private RedisTemplate redisTemplate;

    @Value("${jwt.refresh-token-period}")
    private long refreshTokenValidityTime;

    public RedisUtil(final RedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public void save(String refreshToken, String socialId) {
        log.info("save redis");
        //동일한 key 값으로 저장하면 value값 updat됨
        redisTemplate.opsForValue().set(socialId, refreshToken, refreshTokenValidityTime, TimeUnit.SECONDS);
    }

    public void deleteById(String socialId) {
        redisTemplate.delete(String.valueOf(socialId));
    }

    public Optional<String> findById(final String socialId) {
        String refreshToken = (String) redisTemplate.opsForValue().get(socialId);
        return Optional.ofNullable(refreshToken);
    }

    public Optional<String> getEmailValues(String value) {
        String check = (String) redisTemplate.opsForValue().get(value);
        log.info("chek"+check);
        return Optional.ofNullable(check);
    }

    public boolean checkExistsValue(String key) {
        String value = (String) redisTemplate.opsForValue().get(key);
        log.info("value"+value);
        return value == null;
    }


}
