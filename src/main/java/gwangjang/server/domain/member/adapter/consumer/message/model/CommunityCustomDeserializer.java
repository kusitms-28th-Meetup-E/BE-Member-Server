package gwangjang.server.domain.member.adapter.consumer.message.model;

import com.fasterxml.jackson.databind.ObjectMapper;
import gwangjang.server.domain.member.adapter.consumer.web.dto.post.MemberDto;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.common.header.Headers;
import org.apache.kafka.common.serialization.Deserializer;

import java.util.Map;

@RequiredArgsConstructor
public class CommunityCustomDeserializer implements Deserializer<MemberDto> {

    private final ObjectMapper objectMapper;

    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {
        Deserializer.super.configure(configs, isKey);
    }

    @Override
    public MemberDto deserialize(String topic, byte[] data) {
        if (data == null) {
            return null;
        }

        try {
            return objectMapper.readValue(data, MemberDto.class);
        } catch (Exception e) {
            throw new RuntimeException("Deserialization error: " + e.getMessage(), e);
        }
    }

    @Override
    public MemberDto deserialize(String topic, Headers headers, byte[] data) {
        return Deserializer.super.deserialize(topic, headers, data);
    }

    @Override
    public void close() {
        Deserializer.super.close();
    }
}
