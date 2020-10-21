import com.example.demo.CustomersAndUsers.UserType.IsBasicUser
import com.example.demo.CustomersAndUsers.UserType.IsPremiumUser
import com.example.demo.CustomersAndUsers.UserType.IsStandardUser
import com.example.demo.Greetings.BasicGreeting
import com.example.demo.Greetings.PremiumGreeting
import com.example.demo.Greetings.StandardGreeting
import com.example.demo.Routes.InitialRoute;
import org.apache.camel.CamelContext;
import org.apache.camel.Exchange
import org.apache.camel.ProducerTemplate;
import org.apache.camel.builder.ExchangeBuilder
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;
import spock.lang.Specification;

public class RouteTest extends Specification {
    CamelContext camelContext = new DefaultCamelContext();

    Exchange exchange = ExchangeBuilder.anExchange(camelContext)
            .build();

    IsPremiumUser isPremiumUser = Mock(IsPremiumUser)
    PremiumGreeting premiumGreeting = Mock(PremiumGreeting)

    IsStandardUser isStandardUser = Mock(IsStandardUser)
    StandardGreeting standardGreeting = Mock(StandardGreeting)

    IsBasicUser isBasicUser = Mock(IsBasicUser)
    BasicGreeting basicGreeting = Mock(BasicGreeting)

    def setup(){
        RouteBuilder route = new InitialRoute(
                premiumGreeting,
                standardGreeting,
                basicGreeting,
                isPremiumUser,
                isStandardUser,
                isBasicUser)
        camelContext.addRoutes(route)
        camelContext.start()
    }

    def cleanup(){
        camelContext.close()
    }

    def "route is executed with premiumUser"() {
        given:
        ProducerTemplate producer = camelContext.createProducerTemplate()


        when:
        producer.send(InitialRoute.ENDPOINT_URI,exchange)

        then:
        1 * isPremiumUser.matches(_ as Exchange) >> true
        0 * isStandardUser.matches(_ as Exchange)
        0 * isBasicUser.matches(_ as Exchange)
    }

  def "route is executed with basicUser"(){
       given:
        ProducerTemplate producer = camelContext.createProducerTemplate()
        isBasicUser.matches(_ as Exchange) >> true

        when:
        producer.send(InitialRoute.ENDPOINT_URI,exchange)

        then:
        1 * isBasicUser.matches(exchange)

    }

}
