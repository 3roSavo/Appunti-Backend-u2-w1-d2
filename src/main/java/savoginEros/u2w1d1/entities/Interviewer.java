package savoginEros.u2w1d1.entities;

public class Interviewer {
    private IStudent student;

    // COSTRUTTORE
    public Interviewer(IStudent student) {
        this.student = student;
    }

    // METODI
    public void askQuestion() {
        System.out.println("Hey " + student.getName() + " please solve this challenge!");
        student.answerQuestion();
    }
    @Override
    public String toString() {
        return "Interviewer{" +
                "student=" + student +
                '}';
    }
}
