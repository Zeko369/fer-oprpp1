package hr.fer.oprpp1.hw04.db;

/**
 * The type Student record.
 *
 * @author franzekan
 */
public class StudentRecord {
    private final String jmbag;
    private final String firstName;
    private final String lastName;
    private final int finalGrade;

    /**
     * Instantiates a new Student record.
     *
     * @param jmbag      the jmbag
     * @param firstName  the first name
     * @param lastName   the last name
     * @param finalGrade the final grade
     */
    public StudentRecord(String jmbag, String firstName, String lastName, int finalGrade) {
        this.jmbag = jmbag;
        this.firstName = firstName;
        this.lastName = lastName;
        this.finalGrade = finalGrade;
    }

    /**
     * Parses the TSV line into a StudentRecord object.
     *
     * @param line the line
     * @return the student record
     * @throws StudentRecordParseException if the line is not in the correct format
     */
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

    /**
     * Gets jmbag.
     *
     * @return the jmbag
     */
    public String getJmbag() {
        return jmbag;
    }

    /**
     * Gets first name.
     *
     * @return the first name
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Gets last name.
     *
     * @return the last name
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Gets final grade.
     *
     * @return the final grade
     */
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
