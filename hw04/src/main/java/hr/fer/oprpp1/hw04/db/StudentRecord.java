package hr.fer.oprpp1.hw04.db;

public class StudentRecord {
    private String jmbag;
    private String firstName;
    private String lastName;
    private int finalGrade;

    public StudentRecord(String jmbag, String firstName, String lastName, int finalGrade) {
        this.jmbag = jmbag;
        this.firstName = firstName;
        this.lastName = lastName;
        this.finalGrade = finalGrade;
    }

    public StudentRecord(String line) {
        String[] parts = line.split("\t");
        if (parts.length != 4) {
            // TODO: Custom
            throw new Error("Wrong number of elements");
        }

        try {

            int tmpFinalGrade = Integer.parseInt(parts[3]);
            if (tmpFinalGrade < 1 || tmpFinalGrade > 5) {
                throw new Error("Final grade must be between 1 and 5");
            }

            this.jmbag = parts[0];
            this.lastName = parts[1];
            this.firstName = parts[2];
            this.finalGrade = tmpFinalGrade;
        } catch (NumberFormatException e) {
            throw new Error("Final grade must be an integer");
        }
    }

    public String getJmbag() {
        return jmbag;
    }

    public void setJmbag(String jmbag) {
        this.jmbag = jmbag;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getFinalGrade() {
        return finalGrade;
    }

    public void setFinalGrade(int finalGrade) {
        this.finalGrade = finalGrade;
    }

    @Override
    public String toString() {
        return String.format("STUDENT: [%s,%s %s, %d]", jmbag, firstName, lastName, finalGrade);
    }
}
