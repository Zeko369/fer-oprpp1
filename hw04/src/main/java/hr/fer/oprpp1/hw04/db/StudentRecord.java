package hr.fer.oprpp1.hw04.db;

public class StudentRecord {
    private final String jmbag;
    private final String firstName;
    private final String lastName;
    private final int finalGrade;

    public StudentRecord(String jmbag, String firstName, String lastName, int finalGrade) {
        this.jmbag = jmbag;
        this.firstName = firstName;
        this.lastName = lastName;
        this.finalGrade = finalGrade;
    }

    public static StudentRecord fromTSVLine(String line) throws StudentRecordParseException {
        String[] parts = line.split("\t");
        if (parts.length != 4) {
            // TODO: Custom
            throw new StudentRecordParseException("Wrong number of elements");
        }

        try {
            int tmpFinalGrade = Integer.parseInt(parts[3]);
            if (tmpFinalGrade < 1 || tmpFinalGrade > 5) {
                throw new StudentRecordParseException("Final grade must be between 1 and 5");
            }

            return new StudentRecord(parts[0], parts[2], parts[1], tmpFinalGrade);
        } catch (NumberFormatException e) {
            throw new StudentRecordParseException("Final grade must be an integer");
        }
    }

    public String getJmbag() {
        return jmbag;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getFinalGrade() {
        return finalGrade;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof StudentRecord)) {
            return false;
        }

        return ((StudentRecord) obj).getJmbag().equals(this.jmbag);
    }

    @Override
    public String toString() {
        return String.format("STUDENT: [%s %s %s %d]", jmbag, firstName, lastName, finalGrade);
    }
}
