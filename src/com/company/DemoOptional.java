package com.company;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.function.Supplier;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class DemoOptional {

    @Test
    public  void whenCreatesEmptyOptional_thenCorrect(){

        Optional<String> empty=Optional.empty();
        //System.out.println(empty.isPresent());
        assertFalse("No value present",empty.isPresent());
    }

    @Test
    public void givenNonull_whenCreatesOptional_thenCorrect(){
        String name ="Victor";
        Optional<String> optional=Optional.of(name);
        assertEquals("Value present","Optional[Victor]",optional.toString());


    }

    @Test(expected = NullPointerException.class)
    public void givenNull_whenThrowsErrorOnCreate_thenCorrect(){
        String name =null;
        Optional<String> optional=Optional.of(name);
    }

    @Test
    public void givenNonull_whenCreatesNullable_thenCorrect(){
        String name =null;
        Optional<String> optional=Optional.ofNullable(name);
        assertEquals("","Optional.empty",optional.toString());
    }

    @Test
    public void givenOptional_whenIsPresentWorks_thenCorrect(){
        Optional<String> opt=Optional.of("Victor");
        assertTrue(opt.isPresent());
        opt.ifPresent(name->System.out.println(name.length()));

        opt=Optional.ofNullable(null);
        assertFalse(opt.isPresent());
    }

    @Test
    public void whenOrElseWorks_thenCorrect(){

        String nullName=null;
        String name=Optional.ofNullable(nullName).orElse("Victor");

    }
    @Test
    public void whenOrElseGetWorks_thenCorrect(){
        Supplier<String> ref=()->"Victor";
        String nullName=null;
        String name=Optional.ofNullable(nullName).orElseGet(ref);
        System.out.println(name);
    }

    @Test
    public void whenOrElseGetAndOrElseOverlap_thenCorrect() {
        String text="Text present";

        System.out.println("Using orElseGet: ");
        String defaultText=Optional.ofNullable(text).orElseGet(this::getMyDefault);
        assertEquals("Text present",defaultText);

        System.out.println("Using orElse: ");
        defaultText=Optional.ofNullable(text).orElse(getMyDefault());
        assertEquals("Text present",defaultText);

    }

    @Test
    public void whenOrElseGetAndOrElseDiffer_thenCorrect() {
        String text=null;

        System.out.println("Using orElseGet: ");
        String defaultText=Optional.ofNullable(text).orElseGet(this::getMyDefault);
        assertEquals("Default Value",defaultText);

        System.out.println("Using orElse: ");
        defaultText=Optional.ofNullable(text).orElse(getMyDefault());
        assertEquals("Default Value",defaultText);

    }

    @Test(expected = IllegalArgumentException.class)
    public void whenOrElseThrowWorks_thenCorrect() {
        String nullName=null;
        String name=Optional.ofNullable(nullName).orElseThrow(IllegalArgumentException::new);
    }

    public String getMyDefault() {
        System.out.println("Getting Default Value");
        return "Default Value";
    }

    @Test
    public void givenOptional_whenGetsValue_thenCorrect() {
        Optional<String> opt=Optional.of("Victor");
        String name=opt.get();

        assertEquals("Victor",name);
    }

    @Test(expected = NoSuchElementException.class)
    public void givenOptionalWithNull_whenGetThrowsException_thenCorrect() {
        Optional<String> opt=Optional.ofNullable(null);
        String name=opt.get();

    }

    @Test
    public void whenOptionalFilterWorks_thenCorrect() {
        Integer year=2018;

        Optional<Integer> yearOptional=Optional.of(year);

        boolean bol2018=yearOptional.filter(y->y==2018).isPresent();
        assertTrue(bol2018);
        boolean bol2019=yearOptional.filter(y->y==2019).isPresent();
        assertFalse(bol2019);



    }

    @Test
    public void whenFiltersWithoutOptional_thenCorrect() {
        assertTrue(priceIsInRange2(new Modem(10.0)));
        assertFalse(priceIsInRange2(new Modem(9.9)));
        assertFalse(priceIsInRange2(new Modem(null)));
        assertFalse(priceIsInRange2(new Modem(15.5)));
        assertFalse(priceIsInRange2(null));
    }

    @Test
    public void givenOptional_whenMapWorks_thenCorrect() {
        List<String> companyNames= Arrays.asList("paypal","oracle","","microsoft","","apple");
        Optional<List<String>> listOptional=Optional.of(companyNames);
        int size=listOptional.map(List::size).orElse(0);

        assertEquals(6,size);

    }

    @Test
    public void givenOptional_whenMapWorksWithFilter_thenCorrect() {
        String password=" password ";
        Optional<String> passOpt=Optional.of(password);
        boolean correctPassword=passOpt.filter(p->p.equals("password")).isPresent();
        assertFalse(correctPassword);

        correctPassword=passOpt.map(String::trim).filter(p->p.equals("password")).isPresent();
        assertTrue(correctPassword);

    }

    @Test
    public void givenOptional_whenFlatMapWorks_thenCorrect2() {
        Person person = new Person("john", 26);
        Optional<Person> personOptional = Optional.of(person);

        Optional<Optional<String>> nameOptionalWrapper=personOptional.map(Person::getName);
        Optional<String> nameOptional=nameOptionalWrapper.orElseThrow(IllegalArgumentException::new);
        String name1=nameOptional.orElse("");
        assertEquals("john", name1);

        String name2=personOptional.flatMap(Person::getName).orElse("");

        assertEquals("john", name2);


    }
    public boolean priceIsInRange1(Modem  modem){
        boolean isInRange=false;

        if ((modem !=null && modem.getPrice()!=null) && (modem.getPrice()>=10  && modem.getPrice()<=15)){
            isInRange=true;
        }

        return  isInRange;
    }

    public boolean priceIsInRange2(Modem modem2) {
        return Optional.ofNullable(modem2).map(modem -> modem.getPrice()).filter(p->p>=10 && p<=15).isPresent();
    }
    public static void  main(String args[]){
        DemoOptional demoOptional=new DemoOptional();
        demoOptional.givenOptional_whenFlatMapWorks_thenCorrect2();


    }

    class Modem{

        public Double getPrice() {
            return price;
        }

        public void setPrice(Double price) {
            this.price = price;
        }

        private Double price;

        public Modem(Double price){
            this.price=price;
        }


    }

     class Person {
        private String name;
        private int age;

        public void setPassword(String password) {
            this.password = password;
        }

        private String password;

        public Optional<String> getName() {
            return Optional.ofNullable(name);
        }

        public Optional<Integer> getAge() {
            return Optional.ofNullable(age);
        }

        public Optional<String> getPassword() {
            return Optional.ofNullable(password);
        }

        public Person(String name,int age){
            this.name=name;
            this.age=age;
        }

    }
}
