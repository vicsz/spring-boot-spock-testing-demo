import com.example.springbootspocktestingdemo.Person
import com.example.springbootspocktestingdemo.PersonRepository
import com.example.springbootspocktestingdemo.SpockTestExampleApplication
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import spock.lang.Specification

@SpringBootTest(classes = SpockTestExampleApplication.class)
class PersonRepositorySpecs extends Specification{

    @Autowired
    PersonRepository personRepository

    def setup(){
        //Wipe H2 data before each test
        personRepository.deleteAll()
    }


    def "Save and Load Test"() {
        given:
        personRepository.save(new Person(firstName: "Bruce", lastName: "Wayne"))

        when:
        List<Person> people = personRepository.findAll()

        then:
        people.size() == 1

        def person = people.first()

        person.firstName == "Bruce"
        person.lastName == "Wayne"

    }
}
