package msg.challenge.api.message;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepository extends JpaRepository<MessageInfo, Long> {
}
