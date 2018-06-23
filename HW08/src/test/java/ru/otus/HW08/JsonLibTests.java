package ru.otus.HW08;


import com.google.gson.Gson;
import org.json.simple.JSONValue;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class JsonLibTests {

    private Gson gson = new Gson();

    @Test
    public void shouldTransformNull() throws IllegalAccessException {
        Object o = null;

        String json = JsonLib.toJson(o);

        Assert.assertEquals(gson.toJson(o), json);
    }

    @Test
    public void shouldTransformPrimitive() throws IllegalAccessException {
        int i = 10;

        String json = JsonLib.toJson(i);

        Assert.assertEquals(gson.toJson(i), json);
    }

    @Test
    public void shouldTransformPrimitiveChar() throws IllegalAccessException {
        char c = 'c';

        String json = JsonLib.toJson(c);

        Assert.assertEquals(gson.toJson(c), json);
    }

    @Test
    public void shouldTransformWrapperChar() throws IllegalAccessException {
        Character c = 'c';

        String json = JsonLib.toJson(c);

        Assert.assertEquals(gson.toJson(c), json);
    }

    @Test
    public void shouldTransformPrimitiveDouble() throws IllegalAccessException {
        double d = 1.05;

        String json = JsonLib.toJson(d);

        Assert.assertEquals(gson.toJson(d), json);
    }

    @Test
    public void shouldTransformWrapperDouble() throws IllegalAccessException {
        Double d = 1.05;

        String json = JsonLib.toJson(d);

        Assert.assertEquals(gson.toJson(d), json);
    }

    @Test
    public void shouldTransformPrimitiveBoolean() throws IllegalAccessException {
        boolean b = true;

        String json = JsonLib.toJson(b);

        Assert.assertEquals(gson.toJson(b), json);
    }

    @Test
    public void shouldTransformWrapper() throws IllegalAccessException {
        String f = "false";

        String json = JsonLib.toJson(Boolean.valueOf(f));

        Assert.assertEquals(gson.toJson(Boolean.valueOf(f)), json);
    }

    @Test
    public void shouldTransformString() throws IllegalAccessException {
        String s = "abc";

        String json = JsonLib.toJson(s);

        Assert.assertEquals(gson.toJson(s), json);
    }

    @Test
    public void shouldTransformArrayOfPrimitives() throws IllegalAccessException {
        char[] chars = new char[]{'a', 'b', 'c'};

        String json = JsonLib.toJson(chars);

        Assert.assertEquals(gson.toJson(chars), json);
    }

    @Test
    public void shouldTransformArrayOfWrappers() throws IllegalAccessException {
        int[] integers = new int[]{1, 2, 3};

        String json = JsonLib.toJson(integers);

        Assert.assertEquals(gson.toJson(integers), json);
    }

    @Test
    public void shouldTransformCollectionOfStrings() throws IllegalAccessException {
        List<String> strings = Arrays.asList("abc", "bcd", "cde");

        String json = JsonLib.toJson(strings);

        Assert.assertEquals(gson.toJson(strings), json);
    }

    @Test
    public void shouldTransformCollectionOfIntegers() throws IllegalAccessException {
        List<Integer> integers = Arrays.asList(1, 2, 3);

        String json = JsonLib.toJson(integers);

        Assert.assertEquals(gson.toJson(integers), json);
    }

    @Test
    public void shouldTransformCollectionOfArraysOfChars() throws IllegalAccessException {
        List<char[]> characters = Arrays.asList(new char[]{'a', 'b', 'c'}, new char[]{'c', 'd', 'e'});

        String json = JsonLib.toJson(characters);

        Assert.assertEquals(gson.toJson(characters), json);
    }

    @Test
    public void shouldTransformCollectionOfCharacters() throws IllegalAccessException {
        List<Character> characters = Arrays.asList('a', 'b', 'c');

        String json = JsonLib.toJson(characters);

        Assert.assertEquals(gson.toJson(characters), json);
    }

    @Test
    public void shouldTransformMap() throws IllegalAccessException {
        //given
        Map<Integer, String> map = new HashMap<>();
        map.put(1, "abc");
        map.put(2, "abcd");
        map.put(3, "abcde");

        //when
        String json =JsonLib.toJson(map);

        //then
        Assert.assertEquals(gson.toJson(map), json);
    }

    @Test
    public void shouldTransformVO() throws IllegalAccessException {

        User user1 = new User();
        user1.setAge(12);
        user1.setName("test name 1");
        user1.setDeleted(false);

        User user2 = new User();
        user2.setAge(13);
        user2.setName("test name 2");
        user2.setDeleted(true);

        Group group = new Group();
        group.setId('c');
        group.setActive(true);
        group.setName("test group");
        group.setSomeNumbers(new Integer[]{1, 2, 3, 4});
        group.setUsers(Arrays.asList(user1, user2));

        Assert.assertEquals(JSONValue.parse(new Gson().toJson(group)), JSONValue.parse(JsonLib.toJson(group)));
    }

    public static class User {
        private String name;
        private int age;
        private Boolean deleted;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        public Boolean getDeleted() {
            return deleted;
        }

        public void setDeleted(Boolean deleted) {
            this.deleted = deleted;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            User user = (User) o;
            return age == user.age &&
                Objects.equals(name, user.name) &&
                Objects.equals(deleted, user.deleted);
        }

        @Override
        public int hashCode() {

            return Objects.hash(name, age, deleted);
        }
    }

    public static class GroupParent {
        private char id;

        public char getId() {
            return id;
        }

        public void setId(char id) {
            this.id = id;
        }

        @Override
        public boolean equals(Object o) {

            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            GroupParent that = (GroupParent) o;
            return id == that.id;
        }

        @Override
        public int hashCode() {

            return Objects.hash(id);
        }
    }

    public static class Group extends GroupParent {
        private String name;
        private Integer[] someNumbers;
        private List<User> users;
        private transient boolean active;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Integer[] getSomeNumbers() {
            return someNumbers;
        }

        public void setSomeNumbers(Integer[] someNumbers) {
            this.someNumbers = someNumbers;
        }

        public List<User> getUsers() {
            return users;
        }

        public void setUsers(List<User> users) {
            this.users = users;
        }

        public boolean isActive() {
            return active;
        }

        public void setActive(boolean active) {
            this.active = active;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            if (!super.equals(o)) {
                return false;
            }
            Group group = (Group) o;
            return active == group.active &&
                Objects.equals(name, group.name) &&
                Arrays.equals(someNumbers, group.someNumbers) &&
                Objects.equals(users, group.users);
        }

        @Override
        public int hashCode() {

            int result = Objects.hash(super.hashCode(), name, users, active);
            result = 31 * result + Arrays.hashCode(someNumbers);
            return result;
        }
    }
}
