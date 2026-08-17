public class Person {

    String name;
    int age;
    Heart heart;

    Person(String name, int age, String heartType) {
        this.name = name;
        this.age = age;
        this.heart = new Heart(heartType);
    }

}
