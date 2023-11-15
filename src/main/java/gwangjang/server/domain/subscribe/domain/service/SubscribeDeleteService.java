package gwangjang.server.domain.subscribe.domain.service;


import gwangjang.server.domain.subscribe.domain.entity.Subscribe;
import gwangjang.server.domain.subscribe.domain.repository.SubscribeRepository;
import gwangjang.server.global.annotation.DomainService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Transactional
@DomainService
@RequiredArgsConstructor
public class SubscribeDeleteService {
    private final SubscribeRepository subscribeRepository;

    public void delete(Subscribe subscribe) {
        subscribeRepository.delete(subscribe);
    }
}
