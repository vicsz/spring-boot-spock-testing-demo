import com.example.springbootspocktestingdemo.Person
import com.example.springbootspocktestingdemo.PersonRepository
import com.example.springbootspocktestingdemo.SpockTestExampleApplication
import groovy.json.JsonSlurper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import spock.lang.Specification

@SpringBootTest(classes = SpockTestExampleApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class PersonRepositoryIntegrationSpecs extends Specification{

    @Autowired
    TestRestTemplate restTemplate

    @Autowired
    PersonRepository personRepository

    def setup(){
        //Wipe H2 data before each test
        personRepository.deleteAll()
    }

    def "Default Page Load with no results"() {
        given:

        when:
        def output = new JsonSlurper().parseText(restTemplate.getForObject("/persons", String.class))

        then:
        output.page.totalElements == 0

    }

    def "Post call to save a record"(){
        given:
            String jsonInput = """
                {
                    "firstName":"Kent",
                    "lastName":"Clark"
                }
            """

        when:

        ResponseEntity<String> response = restTemplate.postForEntity("/persons", new HttpEntity<>(jsonInput, new HttpHeaders(contentType: MediaType.APPLICATION_JSON)), String.class)

        then:
        response.statusCode == HttpStatus.CREATED

        def createdPerson = new JsonSlurper().parseText(response.body)

        createdPerson.firstName == "Kent"
        createdPerson.lastName == "Clark"

        personRepository.count() == 1

    }
}
