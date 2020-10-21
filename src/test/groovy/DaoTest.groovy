import com.example.demo.CustomersAndUsers.Customer
import com.example.demo.DAO.CustomerDAO
import com.example.demo.DAO.JdbcCustomerDAO
import com.example.demo.DAO.NamedParameterJdbcCustomerDAO
import com.example.demo.TableSetUp
import com.fasterxml.jackson.databind.util.Named
import org.springframework.beans.factory.annotation.Autowire
import org.springframework.boot.test.context.TestComponent
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.core.PreparedStatementCreator
import org.springframework.stereotype.Component
import spock.lang.Specification
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate

@Component
class DaoTest extends Specification  {
    JdbcTemplate jdbc = Mock(JdbcTemplate)
    CustomerDAO customerDAO = Mock(CustomerDAO)



   // def TableSetUp = new TableSetUp(jdbc, customerDAO);






 /*   def "Test of insert" () {
        given:
        customerDAO.insert(c1);
        int a = 2

        when:
        jdbc.update("insert into PERMISSIONS (name, id, bank, permission) values (Olle, 6, Nordea, Standard)")


        then:
        a == jdbc.execute("SELECT COUNT(id) FROM PERMISSIONS")


    }*/

    def "Test of count"(){
        given:


        when:
        jdbc.update(_ as PreparedStatementCreator)

        then:
        1 * jdbc.update() >>> []
        customerDAO.count() == 2

        where:
        CustomerName | CustomerId
        "Joel" | 1
        "Kalle" | 2
     }

   /* def "Test of findById" () {
        given:

        when:

        then:


    }

    def "Test of delete" () {
        given:

        when:

        then:

    }

    def "Test of update" () {
        given:

        when:

        then:
    }*/

}
