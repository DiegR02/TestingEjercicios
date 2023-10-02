package Ejercicio3;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class PetCounter {
    public static Map<String, Long> countPetsByName(List<Map<String, Object>> petInfo) {
        List<Pet> pets = petInfo.stream()
                .map(petMap -> {
                    Object idObj = petMap.get("id");
                    Long id;
                    if (idObj instanceof Integer) {
                        id = ((Integer) idObj).longValue();
                    } else if (idObj instanceof Long) {
                        id = (Long) idObj;
                    } else {
                        throw new IllegalArgumentException("Tipo de dato 'id' incorrecto: " + idObj.getClass());
                    }
                    String name = (String) petMap.get("name");
                    return new Pet(id, name);
                })
                .collect(Collectors.toList());

        return pets.stream()
                .collect(Collectors.groupingBy(Pet::getName, Collectors.counting()));
    }
}
