package gwangjang.server.domain.subscribe.adapter.producer.web.dto;

import lombok.*;

import java.util.List;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MainBubbleRes {
    private String name;
    private List<MainBubbleData> data;
}