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
        this.jmbagIndexMap = this.list.stream().collect(Collectors.toMap(StudentRecord::getJmbag, this.list::indexOf));

        // check for duplicate JMBAG in list
        Set<String> jmbagSet = this.list.stream().map(StudentRecord::getJmbag).collect(Collectors.toSet());
        if (jmbagSet.size() != this.list.size()) {
            throw new Error("Duplicate JMBAG in database");
        }
    }

    private List<StudentRecord> parseLines(List<String> lines) {
        return lines.stream().map(StudentRecord::new).collect(Collectors.toList());
    }

    public StudentRecord forJMBAG(String jmbag) {
        return this.list.get(this.jmbagIndexMap.get(jmbag));
    }

    public List<StudentRecord> filter(IFilter filter) {
        return this.list.stream().filter(filter::accepts).collect(Collectors.toList());
    }
}
