package com.ashbyp.scratch.lambda;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class Person {

    public enum Sex {
        MALE, FEMALE
    }

    private String name;
    private LocalDate birthday;
    private Sex gender;
    private String emailAddress;

    public Person(String name, LocalDate birthday, Sex gender, String emailAddress) {
        this.name = name;
        this.birthday = birthday;
        this.gender = gender;
        this.emailAddress = emailAddress;
    }

    public int getAge() {
        Period p = Period.between(this.birthday, LocalDate.now());
        return p.getYears();
    }

    public String printPerson() {
        StringBuilder b = new StringBuilder();
        b.append("name=").append(this.name).append(",age=").append(this.getAge());
        b.append(",gender=").append(this.gender).append(",email=").append(this.emailAddress);
        return b.toString();
    }

    public static void printAll(List<Person> people) {
        for (Person p : people) {
            System.out.println(p.printPerson());
        }
    }

    public static void printPersonsWithPredicate(List<Person> roster, Predicate<Person> tester) {
        for (Person p : roster) {
            if (tester.test(p)) {
                System.out.println(p.printPerson());
            }
        }
    }

    public static void main(String[] args) {
        List<Person> people = new ArrayList<>();
        people.add(new Person("Paul", LocalDate.of(1969, 3, 17), Person.Sex.MALE, "paul@here.com"));
        people.add(new Person("Tom", LocalDate.of(1979, 3, 17), Person.Sex.MALE, "tom@here.com"));
        people.add(new Person("Dick", LocalDate.of(1999, 3, 17), Person.Sex.MALE, "dick@here.com"));
        people.add(new Person("Harry", LocalDate.of(2009, 3, 17), Person.Sex.FEMALE, "harry@here.com"));
        printAll(people);

        System.out.println("=======================");
        printPersonsWithPredicate(people, p -> p.getAge() > 35);
        System.out.println("=======================");   
        people.stream().filter(p -> p.gender == Person.Sex.MALE).map(p->p.emailAddress).forEach(email -> System.out.println(email));
      
    }
}
