package gwangjang.server.domain.subscribe.domain.service;

import gwangjang.server.domain.subscribe.domain.entity.Subscribe;
import gwangjang.server.domain.subscribe.domain.repository.SubscribeRepository;
import gwangjang.server.global.annotation.DomainService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Transactional
@DomainService
@RequiredArgsConstructor
public class SubscribeSaveService {

    private final SubscribeRepository subscribeRepository;

    public Subscribe save(Subscribe subscribe) {
        return subscribeRepository.save(subscribe);
    }
}
