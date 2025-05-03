import java.util.*;
import java.lang.reflect.Field;

// Data class
class PersonData {
    public int id;
    public String name;
    public String role;

    public PersonData() {
    }

    public PersonData(int id, String name, String role) {
        this.id = id;
        this.name = name;
        this.role = role;
    }

    @Override
    public String toString() {
        return String.format("{id=%d, name=%s, role=%s}",
                id,
                name == null ? "null" : name,
                role == null ? "null" : role);
    }
}
public class Solution {
    public static void main(String[] args) {
        List<PersonData> a = Arrays.asList(
            new PersonData(1, "A", null),
            new PersonData(2, "B", null),
            new PersonData(3, "C", null),
            new PersonData(4, "D", null)
        );

        List<PersonData> b = Arrays.asList(
            new PersonData(2, null, "X"),
            new PersonData(3, null, "Y"),
            new PersonData(4, null, "Z"),
            new PersonData(5, null, "M")
        );

        // Convert lists to maps
        Map<Integer, PersonData> mapA = new HashMap<>();
        for (PersonData p : a) mapA.put(p.id, p);

        Map<Integer, PersonData> mapB = new HashMap<>();
        for (PersonData p : b) mapB.put(p.id, p);

        // Get all unique ids
        Set<Integer> allIds = new HashSet<>();
        allIds.addAll(mapA.keySet());
        allIds.addAll(mapB.keySet());

        // Final merged list
        List<PersonData> result = new ArrayList<>();
        for (int id : allIds) {
            PersonData p1 = mapA.get(id);
            PersonData p2 = mapB.get(id);
            result.add(mergeObjects(p1, p2));
        }

        // Print output
        for (PersonData p : result) {
            System.out.println(p);
        }
    }

    // Generic merge using reflection
    private static PersonData mergeObjects(PersonData p1, PersonData p2) {
        if (p1 == null && p2 == null) return null;

        PersonData merged = new PersonData();
        merged.id = (p1 != null) ? p1.id : p2.id;

        try {
            for (Field field : PersonData.class.getDeclaredFields()) {
                if (field.getName().equals("id")) continue;
                field.setAccessible(true);
                Object val1 = (p1 != null) ? field.get(p1) : null;
                Object val2 = (p2 != null) ? field.get(p2) : null;
                field.set(merged, val1 != null ? val1 : val2);
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        return merged;
    }
}
