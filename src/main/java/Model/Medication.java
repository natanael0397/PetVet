package Model;

public class Medication extends Product {
    private Double milligrams;

    public Medication(Integer id, String name, Double price, String description,Double milligrams) {
        super(id, name, price, description);
        this.milligrams = milligrams;
    }

    public Double calculateDosage(Integer animalWeight) {
        return milligrams * animalWeight;
    }

    public String describe() {
        return getDescription() + milligrams;
    }

    public Double getMilligrams() {
        return milligrams;
    }

    public void setMilligrams(Double milligrams) {
        this.milligrams = milligrams;
    }
}
