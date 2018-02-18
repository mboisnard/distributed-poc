import java.util.concurrent.TimeUnit;

import com.esgi.poc.receiver.lib.core.Core;
import com.esgi.poc.receiver.lib.core.utils.kafkaconnector.Receiver;
import com.esgi.poc.receiver.lib.core.utils.kafkaconnector.Sender;
import org.junit.ClassRule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.test.rule.KafkaEmbedded;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Core.class)
@DirtiesContext
public class SpringKafkaApplicationTest {

    private static final String HELLOWORLD_TOPIC = "helloworld.t";

    @ClassRule
    public static KafkaEmbedded embeddedKafka = new KafkaEmbedded(1, true, HELLOWORLD_TOPIC);

    @Autowired
    private Receiver receiver;

    @Autowired
    private Sender sender;

    @Test
    public void testReceive() throws Exception {
        sender.send(HELLOWORLD_TOPIC, "Hello Spring Kafka!");
        receiver.getLatch().await(10000, TimeUnit.MILLISECONDS);
        assert(receiver.getLatch().getCount() == 0);
    }
}