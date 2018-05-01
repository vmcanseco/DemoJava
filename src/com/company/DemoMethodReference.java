package demos;


import java.util.Arrays;
import java.util.Collection;
import java.util.function.BiFunction;
import java.util.stream.Collectors;

@FunctionalInterface
interface MyInterface{
    void display();
}

@FunctionalInterface
interface MyMathInterface{
    void operation(int a, int b);
}

public class DemoMethodReference {

    public void myMethod(){
        System.out.println("Instance Method");
    }

    public  void sum(int a, int b){
        System.out.print(a+b);
    }

    public static  void main(String[] args){
        DemoMethodReference demoMethodReference=new DemoMethodReference();

        MyInterface ref=demoMethodReference::myMethod;
        MyMathInterface ref2=(a,b)-> {System.out.println(a+b);};
        ref.display();
        ref2.operation(2,3);

        BiFunction<Integer,Integer,Integer> product=Multiplication::multiply;

        int pr=product.apply(11,5);
        System.out.println(pr);

        String[] stringArray = { "Steve", "Rick", "Aditya", "Negan", "Lucy", "Sansa", "Jon"};
        Arrays.sort(stringArray,(o1, o2) -> o1.length()-o2.length());
        for(String str:stringArray){
            System.out.println(str);
        }
        Arrays.sort(stringArray,String::compareToIgnoreCase);
        String sortedString=Arrays.stream(stringArray).collect(Collectors.joining(","));
        System.out.println(sortedString);


    }
}

class Multiplication{
    public static int multiply(int a, int b){
        return  a*b;
    }
}
