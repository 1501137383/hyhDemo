package com.loop.demo.test;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Optional;

@Data
public class Zoo {

    private Dog dog;

    public static void main(String[] args) {


        // Zoo zoo = new Zoo();
        // if (zoo != null) {
        //     Dog dog = zoo.getDog();
        //     if (dog != null) {
        //         System.out.println("dog.getAge() = " + dog.getAge());
        //
        //     }
        // }

        // Zoo zoo = new Zoo();
        // Optional.ofNullable(zoo).orElseThrow(() -> new RuntimeException("zoo null"));
        // zoo.setDog(new Dog());
        // Optional.ofNullable(zoo).map(Zoo::getDog).orElseThrow(() -> new RuntimeException("dog null"));
        // Optional.ofNullable(zoo).map(Zoo::getDog).map(d -> {
        //     if (d.getAge() == null)
        //         d.setAge(19);
        //     return d;
        // });
        // Integer age = zoo.getDog().getAge();
        // System.out.println("zoo.getDog().getAge() = " + age);

        Zoo zoo = new Zoo();
        zoo.setDog(new Dog(20));
        Optional.ofNullable(zoo.getDog())
                .orElseThrow(() -> new RuntimeException("dog null"))
                .setAge(Optional.ofNullable(zoo.getDog().getAge()).orElse(19));
        Integer age = zoo.getDog().getAge();
        System.out.println("zoo.getDog().getAge() = " + age);



        // if (zoo == null) System.out.println("zoo = null" );
        //
        // Optional.ofNullable(zoo).map(z -> zoo.getDog()).orElseThrow(() -> new RuntimeException("错误"))
        //         .map(d -> d.getAge()).ifPresent(age -> {
        //     System.out.println("age = " + age);
        // });

    }
}


@Data
@NoArgsConstructor
class Dog {
    private Integer age;



    public Dog(Integer age) {
        this.age = age;
    }
}

