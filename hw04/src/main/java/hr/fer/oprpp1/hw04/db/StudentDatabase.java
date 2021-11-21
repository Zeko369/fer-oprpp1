package hr.fer.oprpp1.hw04.db;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Simple student database implementation
 *
 * @author franzekan
 */
public class StudentDatabase {
    private final List<StudentRecord> list;
    private final Map<String, Integer> jmbagIndexMap;

    /**
     * Instantiates a new Student database.
     *
     * @param lines the lines
     * @throws StudentRecordParseException       If it can't parse a specific row
     * @throws StudentDatabaseUniqueKeyException If multiple rows have the same JMBAG
     */
    public StudentDatabase(List<String> lines) throws StudentRecordParseException, StudentDatabaseUniqueKeyException {
        this.list = this.parseLines(Objects.requireNonNull(lines));

        // check for duplicate JMBAG in list
        Set<String> jmbagSet = this.list.stream().map(StudentRecord::getJmbag).collect(Collectors.toSet());
        if (jmbagSet.size() != this.list.size()) {
            throw new StudentDatabaseUniqueKeyException();
        }

        this.jmbagIndexMap = this.list.stream().collect(Collectors.toMap(StudentRecord::getJmbag, this.list::indexOf));
    }

    /**
     * Returns the number of rows in the DB
     *
     * @return the int
     */
    public int size() {
        return this.list.size();
    }

    private List<StudentRecord> parseLines(List<String> lines) throws StudentRecordParseException {
        return lines.stream().map(StudentRecord::fromTSVLine).collect(Collectors.toList());
    }

    /**
     * Returns a student with the given JMBAG or null if not found
     *
     * @param jmbag the jmbag
     * @return the student record
     */
    public StudentRecord forJMBAG(String jmbag) {
        try {
            int index = this.jmbagIndexMap.get(jmbag);
            return this.list.get(index);
        } catch (NullPointerException e) {
            return null;
        }
    }

    /**
     * Filters DB by a given filter
     *
     * @param filter the filter
     * @return the list
     */
    public List<StudentRecord> filter(IFilter filter) {
        return this.list.stream().filter(filter::accepts).collect(Collectors.toList());
    }

    /**
     * Returns all rows in the DB
     *
     * @return the list
     */
    public List<StudentRecord> all() {
        return this.list;
    }
}
