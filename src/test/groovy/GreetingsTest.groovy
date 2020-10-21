import com.example.demo.CustomersAndUsers.UserType.IsBasicUser
import com.example.demo.CustomersAndUsers.UserType.IsPremiumUser
import com.example.demo.CustomersAndUsers.UserType.IsStandardUser
import org.apache.camel.Exchange
import org.apache.camel.impl.DefaultCamelContext
import spock.lang.*
import org.apache.camel.builder.ExchangeBuilder


class GreetingsTest extends Specification {
        def camelContext = new DefaultCamelContext();
        Exchange exchange = ExchangeBuilder.anExchange(camelContext)
                .build();
        def isPremiumUser = new IsPremiumUser();
        def isStandardUser = new IsStandardUser();
        def isBasicUser = new IsBasicUser();





    def "PremiumUser predicate"(){
        given:
        exchange.getIn().setHeader("permission", "Premium")


        when:
        boolean a = isPremiumUser.matches(exchange)

        then:
        a

    }

    def "StandardUser predicate" (){
        given:
        exchange.getIn().setHeader("permission", "Standard")


        when:
        boolean b = isStandardUser.matches(exchange)

        then:
        b == true

    }

    def "BasicUser predicate"(){
        given:
        exchange.getIn().setHeader("permission", "Basic")


        when:
        boolean c = isBasicUser.matches(exchange)

        then:
        c == true

    }

}

