import com.example.springbootspocktestingdemo.GreetingController
import com.example.springbootspocktestingdemo.SpockTestExampleApplication
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import spock.lang.Specification

@SpringBootTest(classes = SpockTestExampleApplication.class)
class GreetingControllerSpecs extends Specification{

    @Autowired
    GreetingController greetingController

    def "test helloworld"() {
        when:
        def output = greetingController.hello()

        then:
        output == "helloworld"
    }
}
