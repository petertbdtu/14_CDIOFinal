package data.dto;

import java.io.Serializable;

public class IngredientDTO implements Serializable {

    private int id;
    private String name;

    public IngredientDTO() {
        //For Jackson
    }

    public IngredientDTO(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals (Object other)
    {
        if (other instanceof IngredientDTO)
        {
            if (this.id == ((IngredientDTO)other).id);
            return true;
        }

        return false;
    }
}