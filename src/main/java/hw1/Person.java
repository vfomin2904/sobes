package hw1;

public class Person {

    private String name;
    private Byte age;

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private Person person;

        Builder() {
            person = new Person();
        }

        public Builder withName(String name) {
            person.name = name;
            return this;
        }

        public Builder withAge(Byte age) {
            person.age = age;
            return this;
        }

        public Person build() {
            return person;
        }
    }
}
