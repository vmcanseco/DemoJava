package com.company;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class DemoFilters {
    public static void main(String args[]){
        List<String> names= Arrays.asList("Melisandre","Sansa","Jon","Daenerys","Joffery");
        List<String> longNames=names.stream().filter(name->name.length()<6).collect(Collectors.toList());
        longNames.forEach(System.out::println);

        longNames=names.stream().filter(name->name.length()>6 &&  name.length()<8).collect(Collectors.toList());
        longNames.forEach(System.out::println);

        List<Integer> num = Arrays.asList(1,2,3,4,5,6);
        List<Integer> squares=num.stream().map(n->n*n).collect(Collectors.toList());
        System.out.println(squares);

        Map<Integer, String> hmap = new HashMap<Integer, String>();
        hmap.put(11, "Apple");
        hmap.put(22, "Orange");
        hmap.put(33, "Kiwi");
        hmap.put(44, "Banana");

        Map<Integer, String> resuult=hmap.entrySet().stream().filter(map->map.getKey().intValue()<=22)
                .collect(Collectors.toMap(map->map.getKey(), map->map.getValue()));

        System.out.println(resuult);

        resuult=hmap.entrySet().stream().filter(map->"Orange".equals(map.getValue()))
                .collect(Collectors.toMap(map->map.getKey(),map->map.getValue().concat("*")));

        System.out.println(resuult);

        Map<Integer, String> hmap2 = new HashMap<Integer, String>();
        hmap.put(1, "ABC");
        hmap.put(2, "XCB");
        hmap.put(3, "ABB");
        hmap.put(4, "ZIO");

        Map<Integer, String> result2 = hmap.entrySet().stream().filter(p->p.getKey().intValue()<=2)
                .filter(map->map.getValue().startsWith("A"))
                .collect(Collectors.toMap(map->map.getKey(),map->map.getValue()));

        System.out.println(result2);

        List<String> list = Arrays.asList("Java", "Stream", null, "Filter", null);
        List<String> result = list.stream()
                .filter(str->str!=null)
                .collect(Collectors.toList());

        result.forEach(System.out::println);

    }
}
