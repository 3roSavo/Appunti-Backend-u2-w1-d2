package savoginEros.u2w1d1.entities;

public class FrontEndStudent implements IStudent{
    private String name;

    // COSTRUTTORE
    public FrontEndStudent(String name) {
        this.name = name;
    }

    // GETTER E SETTER
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // METODI
    @Override
    public void answerQuestion() {
        System.out.println("I'm the frontend student...here is the answer.");
    }

    @Override
    public String toString() {
        return "FrontEndStudent{" +
                "name='" + name + '\'' +
                '}';
    }
}
