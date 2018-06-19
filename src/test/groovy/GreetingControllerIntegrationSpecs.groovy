import com.example.springbootspocktestingdemo.SpockTestExampleApplication
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import spock.lang.Specification
import spock.lang.Unroll

@SpringBootTest(classes = SpockTestExampleApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class GreetingControllerIntegrationSpecs extends Specification{

    @Autowired
    TestRestTemplate restTemplate


    def "test helloworld endpoint "() {
        when:
        def output = restTemplate.getForObject("/", String.class)

        then:
        output == "helloworld"
    }

    @Unroll
    def "test greeting for #input value"() {
        when:
        def output = restTemplate.getForObject("/greeting?value={value}", String.class, input)

        then:
        output == expected_result

        where:
        input      ||  expected_result
        "World!"   || '{"message":"Hello World!"}'
        "Canada !" || '{"message":"Hello Canada !"}'

    }


}
