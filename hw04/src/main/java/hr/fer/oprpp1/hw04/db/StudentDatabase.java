package hr.fer.oprpp1.hw04.db;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class StudentDatabase {
    private final List<StudentRecord> list;
    private final Map<String, Integer> jmbagIndexMap;

    public StudentDatabase(List<String> lines) {
        this.list = this.parseLines(lines);

        // check for duplicate JMBAG in list
        Set<String> jmbagSet = this.list.stream().map(StudentRecord::getJmbag).collect(Collectors.toSet());
        if (jmbagSet.size() != this.list.size()) {
            throw new IllegalStateException("Duplicate JMBAG in database");
        }

        this.jmbagIndexMap = this.list.stream().collect(Collectors.toMap(StudentRecord::getJmbag, this.list::indexOf));
    }

    public int size() {
        return this.list.size();
    }

    private List<StudentRecord> parseLines(List<String> lines) {
        return lines.stream().map(StudentRecord::fromTSVLine).collect(Collectors.toList());
    }

    public StudentRecord forJMBAG(String jmbag) {
        try {
            int index = this.jmbagIndexMap.get(jmbag);
            return this.list.get(index);
        } catch(NullPointerException e) {
            return null;
        }
    }

    public List<StudentRecord> filter(IFilter filter) {
        return this.list.stream().filter(filter::accepts).collect(Collectors.toList());
    }

    public List<StudentRecord> all() {
        return this.list;
    }
}
