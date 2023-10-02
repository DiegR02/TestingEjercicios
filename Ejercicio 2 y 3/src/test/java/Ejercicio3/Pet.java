package Ejercicio3;

public class Pet {
    private Long id; // Cambiar de int a Long
    private String name;

    public Pet() {
    }

    public Pet(Long id, String name) { // Cambiar el constructor
        this.id = id;
        this.name = name;
    }

    public Long getId() { // Cambiar el tipo de retorno de int a Long
        return id;
    }

    public void setId(Long id) { // Cambiar el tipo de parámetro de int a Long
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
