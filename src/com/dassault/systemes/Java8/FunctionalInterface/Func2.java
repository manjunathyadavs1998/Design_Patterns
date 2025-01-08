package src.com.dassault.systemes.Java8.FunctionalInterface;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Person{
    public Person(){}
    public Person(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private Integer id;
    private String name;

}
public class Func2 {
    public static void main(String[] args) {
        List<Person>personList=new ArrayList<>();
        personList.add(new Person(1,"Alex"));
        personList.add(new Person(2,"Bala"));
        personList.add(new Person(3,"Cary"));
        for(Person p:personList){
            System.out.println(p.getId()+" "+p.getName());
        }
        Map<Integer,String>personMap=new HashMap<>();
        personList.forEach((p)->personMap.put(p.getId(),p.getName()));
        System.out.println("********************************");
        for(Integer key:personMap.keySet()){
            System.out.println(personMap.get(key));
        }
    }
}
