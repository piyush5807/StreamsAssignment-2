import java.security.Key;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Person {

    private int age;
    private String name;
    private String country;
 
    public Person(int age, String name, String country) {
        this.age = age;
        this.name = name;
        this.country = country;
    }
 
    public int getAge() {
        return age;
    }
 
    public void setAge(int age) {
        this.age = age;
    }
 
    public String getName() {
        return name;
    }
 
    public void setName(String name) {
        this.name = name;
    }
 
    public String getCountry() {
        return country;
    }
 
    public void setCountry(String country) {
        this.country = country;
    }
 
    @Override
    public String toString() {
        return name;
 
    }
 
    public static void main(String[] args) {
    
    
    List<Person> people = Arrays.asList(
            new Person(20, "John", "USA"),
            new Person(35, "Sam", "Italy"),
            new Person(15, "Jamie", "England"),
            new Person(30, "Robert", "Italy"),
            new Person(20, "James", "Ireland"),
            new Person(25, "Peter", "USA"),
            new Person(5, "Jessica", "Norway"),
            new Person(40, "Roger", "Netherlands"),
            new Person(50, "Jim", "USA")
    );
    // 1. Print the avg age of all the people



    double age = people.stream().collect(Collectors.averagingInt(Person::getAge));
    System.out.println("Average age of Persons: "+age);

    System.out.println("---------------------------------------");
    // 2.Create a list of all the people who are either greater than 20 or contain any vowel in their name (uppercase or lowercase)
    System.out.println("List of all the people who are either greater than 20 or contain any vowel in their name");
    List<String> vowelsList = new ArrayList<>(Arrays.asList("a", "e", "i", "o", "u"));
    List<Person> li = people.stream().filter(i->i.getAge()>20 
                            ||vowelsList.contains(i.getName())).collect(Collectors.toList());
    li.forEach(System.out::println);


    
    
    // 3.Create a list of people, sorted in ascending order on the basis of age, if age is the same then sort in descending order of name
    // Collections.sort(people,Comparator.comparing(Person::getAge).thenComparing(Comparator.comparing(Person::getName)).reversed());
    System.out.println("---------------------------------------list of people, sorted in ascending order on the basis of age-------------------------");
    people.sort(Comparator.comparing(Person::getAge).thenComparing(Comparator.comparing(Person::getName)).reversed());
    people.forEach(System.out::println);

    // Q4. Create a map from this people list where the key is country name and value is count which means a map will tell how many people live in a particular country
    System.out.println("----------------------------map from this people list where the key is country name and value is count---------------------");
    Map<String,Long> mapWithCountry = people.stream().collect(Collectors.groupingBy(Person::getCountry,Collectors.counting()));
    System.out.println(mapWithCountry);

    // Q5. Create a map which stores avg age of people per country (key should be country and value should be average age i.e, double)
    System.out.println("----------------------map which stores avg age of people per country-----------------");
    Map<String,Double> avgAge = people.stream().collect(Collectors.groupingBy(Person::getCountry,
                                                Collectors.averagingDouble(Person::getAge)));
    System.out.println(avgAge);
    
    // 6.Print the oldest person in every country
    System.out.println("---------------------------oldest person in every country----------------------------");

    Map<String,Optional<Person>> oldest = people.stream().collect(Collectors.groupingBy(
                                                          Person::getCountry,Collectors.maxBy(
                                                          Comparator.comparing(Person::getAge))));
    
    System.out.println(oldest);

    // Q7. Print the country with most people
    System.out.println("---------------------------Print the country with most people----------------------------");

    String country = Collections.max(mapWithCountry.entrySet(),Comparator.comparingLong(Map.Entry::getValue)).getKey();
    System.out.println(country);

    // Q8. Create a list of 20 random integers in the range 0 - 1000 using Java 8 streams
    System.out.println("---------------------------Create a list of 20 random integers in the range 0 - 1000 using Java 8 streams----------------------------");
    Random random = new Random();
    List<Integer> integer = random.ints( 20,0,1000 ).boxed().collect( Collectors.toList() );
    System.out.println(integer);



    }
    

 
 }


