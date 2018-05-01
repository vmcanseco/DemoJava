package com.company;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class DemoStream {

    public static void main(String[] args){
        List<String> strings= Arrays.asList("abc","","bc","efg","abcd","","jk1");
        List<String> filtered=strings.stream().filter(string -> !string.isEmpty()).collect(Collectors.toList());
        String mergedString = strings.stream().filter(string -> !string.isEmpty()).collect(Collectors.joining(", "));
        System.out.println(mergedString);
        filtered.forEach(System.out::println);

        List<Integer>   numbers=Arrays.asList(3,2,2,3,7,3,5);
        List<Integer>   squaresList=numbers.stream().map(integer -> integer*integer).distinct().collect(Collectors.toList());
        squaresList.forEach(System.out::println);


        List<Hero> heroes = Arrays.asList(
                new Hero("Hulk", false),
                new Hero("Superman", true),
                new Hero("Batman", false));

        List<String> result = getNamesMeeetingCondition(heroes,h->h.canFly);
        result.forEach(System.out::println);
        result = getNamesMeeetingCondition(heroes, h -> h.name.contains("man"));
        result.forEach(System.out::println);

        heroes.stream().filter(h->h.canFly).map(h->h.name.toUpperCase()).collect(Collectors.toList()).forEach(System.out::println);
    }

    public static List<String> getNamesMeeetingCondition(List<Hero> heroList, Predicate<Hero> condition){
     List<String> foundNames=new ArrayList<>();
     for(Hero hero:heroList){
         if (condition.test(hero)){
             foundNames.add(hero.name);
         }
     }

     return  foundNames;


    }

    private static class Hero {
        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        String name;
        boolean canFly;

        Hero(String name, boolean canFly) {
            this.name = name;
            this.canFly = canFly;
        }
    }
}
