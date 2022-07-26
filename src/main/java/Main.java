import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;


/*Napisz klasę Person, która ma pola:
        - firstName,
        - lastName,
        - age
        - male (boolean)
        Mając listę osób i korzystając ze streamów:
        a. uzyskaj listę mężczyzn
        b. uzyskaj listę dorosłych kobiet (filter)
                c. uzyskaj Optional<Person> z dorosłym Jackiem findAny/findfirst
        d. uzyskaj listę wszystkich nazwisk osób, które są w przedziale wiekowym: 15-19
        (filter)
                e. * uzyskaj sumę wieku wszystkich osób (sum)
                f. * uzyskaj średnią wieku wszystkich mężczyzn (average)
                g. ** znajdź najstarszą osobę w liście (findFirst)
*/


public class Main {
    public static void main(String[] args) {

        Person person1 = new Person("Jacek", "Kowalski", 18, true);
        Person person2 = new Person("Jacek", "Górski", 15, true);
        Person person3 = new Person("Andżelika", "Dżoli", 25, false);
        Person person4 = new Person("Wanda", "Ibanda", 12, false);
        Person person5 = new Person("Marek", "Marecki", 17, true);
        Person person6 = new Person("Johny", "Brawo", 25, true);
        Person person7 = new Person("Stary", "Pan", 80, true);
        Person person8 = new Person("Newbie", "Noob", 12, true);
        Person person9 = new Person("Newbies", "Sister", 19, false);
        List<Person> people = new ArrayList<Person>(Arrays.asList(person1, person2, person3,
                person4, person5, person6, person7, person8, person9));

        System.out.println(people);

        // forma pętla for
        List<Person> wynik1 = new ArrayList<>();
        for (Person person : people) {
            if (person.isMale()) {
                wynik1.add(person);
            }
        }
// koniec.

// forma stream bez lambdy
        List<Person> wynik2 = people.stream()
                .filter(new Predicate<Person>() {
                    @Override
                    public boolean test(Person person) {
                        return person.isMale();
                    }
                }).collect(Collectors.toList());
// koniec.

// forma stream lambda
        List<Person> wynik3 = people.stream()
                .filter((person) -> person.isMale())
                .collect(Collectors.toList());
        System.out.println(wynik3);
// koniec

// forma stream
        List<Person> wynik4 = people.stream()
                .filter(Person::isMale)
                .collect(Collectors.toList());
        System.out.println(wynik4);
// koniec

        List<Person> femaleAdultList = people.stream()
                .filter((f) -> !f.isMale())
                .filter((f) -> f.getAge() >= 18)
                .collect(Collectors.toList());
        ;
        System.out.println(femaleAdultList);


        List<Person> manList = people.stream().filter((m) -> m.isMale()).collect(Collectors.toList());
        System.out.println(manList);

        List<Person> femaleAdultList2 = people.stream().filter((f) -> !f.isMale()).filter((f) -> f.getAge() >= 18).collect(Collectors.toList());
        System.out.println(femaleAdultList2);

        Optional<Person> optionalPerson = people.stream().filter((j) -> j.getFirstName().equals("Jacek")).findAny();
        System.out.println(optionalPerson);
        System.out.println();
/*
        d. uzyskaj listę wszystkich nazwisk osób, które są w przedziale wiekowym: 15-19
        (filter)
                e. * uzyskaj sumę wieku wszystkich osób (sum)
                f. * uzyskaj średnią wieku wszystkich mężczyzn (average)
                g. ** znajdź najstarszą osobę w liście (findFirst)
   */
        List<String> personList = people.stream().filter(person -> person.getAge() > 14 && person.getAge() < 20)
                .map(person -> person.getLastName())
                .collect(Collectors.toList());
        System.out.println(personList);

        Long sumOfAge = Long.valueOf(people.stream().mapToInt(person -> person.getAge()).sum());
        Long lista_w_sumie = people.stream().count();
        System.out.println(sumOfAge + " dla tylu osob " + lista_w_sumie);

        OptionalDouble averageOfAge = people.stream().filter((m) -> m.isMale()).mapToInt(person -> person.getAge()).average();
        Long lista_w_sredniej2 = people.stream().filter((m) -> m.isMale()).count();
        System.out.println(averageOfAge + "dla tylu Male " + lista_w_sredniej2);

        Comparator<Person> comparator = Comparator.comparing(Person::getAge);

        Optional<Person> oldestPerson = people.stream()
                .sorted(comparator).findFirst();
        System.out.println(personList);


//    Stwórz klasę Programmer, która ma klasę Person jako pole. Poza tym, posiada listę
//    języków, którymi się posługuje. Mogą być one reprezentowane przez klasę String.
//    Mając listę programistów i korzystając ze streamów:
//    a) uzyskaj listę programistów, którzy są mężczyznami
//    b) uzyskaj listę niepełnoletnich programistów (obydwóch płci), którzy piszą w Cobolu
//    c) uzyskaj listę programistów, którzy znają więcej, niż jeden język programowania
//    d) uzyskaj listę programistek, które piszą w Javie i Cpp
//    e) uzyskaj listę męskich imion
//    f) uzyskaj set wszystkich języków opanowanych przez programistów
//    g) uzyskaj listę nazwisk programistów, którzy znają więcej, niż dwa języki
//    h) sprawdź, czy istnieje chociaż jedna osoba, która nie zna żadnego języka
//    i)* uzyskaj ilość wszystkich języków opanowanych przez programistki
//    Zadanie 3*:
//    Używając streamów znajdź długość najdłuższej linii w wybranym przez ciebie pliku. Zauważ,
//    że klasa BufferedReader ma metodę stream().
//

        List<String> languages1 = Arrays.asList("Java;Cobol;Cpp;Lisp".split(";"));
        List<String> languages2 = Arrays.asList("Java;Lisp".split(";"));
        List<String> languages3 = Arrays.asList("Java;Cobol;Cpp;Lisp;C#".split(";"));
        List<String> languages4 = Arrays.asList("C#;C;Cpp".split(";"));
        List<String> languages5 = Arrays.asList("Java;Assembler;Scala;Cobol".split(";"));
        List<String> languages6 = Arrays.asList("Java;Scala".split(";"));
        List<String> languages7 = Arrays.asList("C#;C".split(";"));
        List<String> languages8 = Collections.emptyList();
        List<String> languages9 = Arrays.asList("Java");

        Programmer programmer1 = new Programmer(person1, languages1);
        Programmer programmer2 = new Programmer(person2, languages2);
        Programmer programmer3 = new Programmer(person3, languages3);
        Programmer programmer4 = new Programmer(person4, languages4);
        Programmer programmer5 = new Programmer(person5, languages5);
        Programmer programmer6 = new Programmer(person6, languages6);
        Programmer programmer7 = new Programmer(person7, languages7);
        Programmer programmer8 = new Programmer(person8, languages8);
        Programmer programmer9 = new Programmer(person9, languages9);
        List<Programmer> programmers = Arrays.asList(
                programmer1,
                programmer2,
                programmer3,
                programmer4,
                programmer5,
                programmer6,
                programmer7,
                programmer8,
                programmer9);
        System.out.println(programmers);

    }
}
