import javax.inject.Named;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Named("HelloWorld")
public class HelloWorldBean {
    private String name;
    private List<String> messages;
    private List<SuperHero> superHeroes;

    public HelloWorldBean() {
        name = "Batman";
        messages = new ArrayList<>();
        messages.add("Pesho");
        messages.add("Gosho");
        messages.add("Tosho");
        superHeroes = new ArrayList<>();
        superHeroes.add(new SuperHero("Batman", "Bruce Wayne"));
        superHeroes.add(new SuperHero("SuperMan", "Clark Kent"));
    }

    public List<String> getMessages() {
        return messages;
    }

    public void setMessages(List<String> messages) {
        this.messages = messages;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDate() {
        return new Date();
    }

    public List<SuperHero> getSuperHeroes() {
        return superHeroes;
    }

    public void setSuperHeroes(List<SuperHero> superHeroes) {
        this.superHeroes = superHeroes;
    }
}
