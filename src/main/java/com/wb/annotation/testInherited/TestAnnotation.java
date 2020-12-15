package com.wb.annotation.testInherited;

import com.wb.annotation.MyAnnotation;
import com.wb.annotation.testRepeatable.Game;
import com.wb.annotation.testRepeatable.People;
import com.wb.annotation.testRepeatable.PlayGame;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class TestAnnotation {
    public static void main(String[] args) {
        /**
         * 通过 Son 获取 Father 的注解
         */
        Class<Son> sonClass = Son.class;
        MyAnnotation annotation = sonClass.getAnnotation(MyAnnotation.class);
        if (annotation != null) {
            System.out.println("@MyTestAnnotation被@Inherited标注，子类可以获取到标注在父类上的该注解");
        } else {
            System.out.println("@MyTestAnnotation没有被@Inherited标注，子类不能获取到标注在父类上的该注解");
        }


        /**
         * 获取标注在class上的注解属性
         */
        Class<Father> fatherClass = Father.class;
        boolean myAnnotationPresent = fatherClass.isAnnotationPresent(MyAnnotation.class);
        if (myAnnotationPresent) {
            MyAnnotation myAnnotation = fatherClass.getAnnotation(MyAnnotation.class);
            System.out.println("father.MyAnnotation.name: ~~~~~~~~~~" + myAnnotation.name());
            System.out.println("father.MyAnnotation.age: ~~~~~~~~~~" + myAnnotation.age());
        }

        /**
         * 注解应用于字段属性，获取方法注解属性
         */
        try {
            Field age = fatherClass.getDeclaredField("age");
            boolean ageAnnotationPresent = age.isAnnotationPresent(Age.class);
            if (ageAnnotationPresent) {
                Age ageAnnotation = age.getAnnotation(Age.class);
                System.out.println("father.field.annotation.value: ~~~~~~~~~~" + ageAnnotation.value());
            }

            /**
             * 获取标注在PlayGame上的注解属性。PlayGame.play()被@Game标注，@Game被@Repeatable标注
             */
            Class<PlayGame> playGame = PlayGame.class;
            boolean gameAnnotationPresent = playGame.isAnnotationPresent(People.class);
            if (gameAnnotationPresent) {
                People peopleAnnotation = playGame.getAnnotation(People.class);
                Game[] games = peopleAnnotation.value();
                for (Game game : games) {
                    System.out.println("PlayGame.class.annotation.value:~~~~~~~~~~ " + game.value());
                }
            }

            /**
             * 获取被 @Repeatable 标注的方法属性值
             */
            Method playMethod = PlayGame.class.getDeclaredMethod("play");
            if (playMethod != null) {
                People annotation2 = playMethod.getAnnotation(People.class);
                Game[] value = annotation2.value();
                for (Game game : value) {
                    System.out.println("PlayGame.method.annotation.value: ~~~~~~~~~~" + game.value());
                }
            }
        } catch (NoSuchFieldException | NoSuchMethodException e) {
            e.printStackTrace();
        }

    }
}