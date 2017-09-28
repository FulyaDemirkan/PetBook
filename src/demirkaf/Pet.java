package demirkaf;

import java.time.LocalDate;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/*
Name: F. Fulya Demirkan
File: Pet.java
Other Files in this Project: 
- PetList.java
- Breed.java
- FXMLFinalProjectController.java
- FXMLFinalProject.fxml
- main.css

Main class: demirkaf_FinalProject.java
 */
/**
 * The Pet class is used to model a Pet object. All Pet objects must have a
 * petId, petName, ownerName, birthdate, gender, species, breed and softDeleted
 * value.
 *
 * @author Fulya Demirkan
 */
public class Pet
{

    private IntegerProperty petId = new SimpleIntegerProperty();
    private StringProperty petName = new SimpleStringProperty();
    private StringProperty ownerName = new SimpleStringProperty();
    private LocalDate birthdate;
    private StringProperty gender = new SimpleStringProperty();
    private StringProperty species = new SimpleStringProperty();
    private Breed breed;
    private boolean softDeleted;

    /**
     * Constructs a default Pet object.
     */
    public Pet()
    {

    }

    /**
     * Constructs a Pet object with user defined values. Values cannot be null,
     * otherwise an exception is thrown.
     *
     * @param petId the function-specified petId parameter
     * @param petName the user-specified petName parameter
     * @param ownerName the user-specified ownerName parameter
     * @param birthday the user-specified birthday parameter
     * @param gender the user-specified gender parameter
     * @param species the user-specified species parameter
     * @param breed the user-specified breed parameter
     * @param bool the function-specified softDeleted parameter
     */
    public Pet(int petId, String petName, String ownerName, LocalDate birthday,
            String gender, String species, Breed breed, boolean bool)
    {
        setPetId(petId);
        setPetName(petName);
        setOwnerName(ownerName);
        setBirthdate(birthday);
        setGender(gender);
        setBreed(breed);
        setSpecies(species);
        setSoftDeleted(bool);
    }

    /**
     * Places a valid id into this Pet's petId IntegerProperty member. petId
     * value is acts as a primary key and always specified by a function.
     * Therefore does not need to throw any exception.
     *
     * @param id the function-specified pet id parameter
     */
    public void setPetId(int id)
    {
        this.petId.set(id);
    }

    /**
     * Retrieves this Pet's petId value.
     *
     * @return this Pet's petId value.
     */
    public int getPetId()
    {
        return petId.getValue();
    }

    /**
     * Retrieves petId IntegerProperty of this Pet.
     *
     * @return petId IntegerProperty of this Pet.
     */
    public IntegerProperty petIdProperty()
    {
        return petId;
    }

    /**
     * Places a valid id into this Pet's petId StringProperty member. petId
     * cannot be null, otherwise an exception is thrown.
     *
     * @param petName the user-specified pet name parameter
     * @throws IllegalArgumentException if pet name is null
     */
    public void setPetName(String petName)
    {
        if (petName != null && !petName.trim().isEmpty())
        {
            this.petName.set(petName);
        } else
        {
            throw new IllegalArgumentException("Pet name cannot be empty.");
        }
    }

    /**
     * Retrieves this Pet's petName value.
     *
     * @return this Pet's petName value.
     */
    public String getPetName()
    {
        return petName.get();
    }

    /**
     * Retrieves petName StringProperty of this Pet.
     *
     * @return petName StringProperty of this Pet.
     */
    public StringProperty petNameProperty()
    {
        return petName;
    }

    /**
     * Places a valid id into this Pet's ownerName StringProperty member.
     * ownerName cannot be null, otherwise an exception is thrown.
     *
     * @param ownerName the user-specified owner name parameter
     * @throws IllegalArgumentException if owner name is null
     */
    public void setOwnerName(String ownerName)
    {
        if (ownerName != null && !ownerName.trim().isEmpty())
        {
            this.ownerName.set(ownerName);
        } else
        {
            throw new IllegalArgumentException("Owner name cannot be empty.");
        }
    }

    /**
     * Retrieves this Pet's ownerName value.
     *
     * @return this Pet's ownerName value.
     */
    public String getOwnerName()
    {
        return ownerName.get();
    }

    /**
     * Retrieves ownerName StringProperty of this Pet.
     *
     * @return ownerName StringProperty of this Pet.
     */
    public StringProperty ownerNameProperty()
    {
        return ownerName;
    }

    /**
     * Places a valid id into this Pet's birthdate LocalDate member. birthdate
     * cannot be null, otherwise an exception is thrown.
     *
     * @param birthdate the user-specified birthdate parameter
     * @throws IllegalArgumentException if birthdate is null
     */
    public void setBirthdate(LocalDate birthdate)
    {
        if (birthdate != null)
        {
            this.birthdate = birthdate;
        } else
        {
            throw new IllegalArgumentException("Birthdate cannot be empty.");
        }
    }

    /**
     * Retrieves this Pet's birthdate value.
     *
     * @return this Pet's birthdate value.
     */
    public LocalDate getBirthdate()
    {
        return birthdate;
    }

    /**
     * Places a valid id into this Pet's gender StringProperty member. gender
     * cannot be null, otherwise an exception is thrown.
     *
     * @param gender the user-specified gender parameter
     * @throws IllegalArgumentException if gender is null
     */
    public void setGender(String gender)
    {
        if (gender != null)
        {
            this.gender.set(gender);
        } else
        {
            throw new IllegalArgumentException("Gender cannot be empty.");
        }
    }

    /**
     * Retrieves this Pet's gender value.
     *
     * @return this Pet's gender value.
     */
    public String getGender()
    {
        return gender.get();
    }

    /**
     * Retrieves gender StringProperty of this Pet.
     *
     * @return gender StringProperty of this Pet.
     */
    public StringProperty genderProperty()
    {
        return gender;
    }

    /**
     * Places a valid id into this Pet's breed StringProperty member. breed
     * cannot be null, otherwise an exception is thrown.
     *
     * @param breed the user-specified breed value
     */
    public void setBreed(Breed breed)
    {
        if (breed != null)
        {
            this.breed = breed;
        } else
        {
            throw new IllegalArgumentException("Breed cannot be empty.");
        }
    }

    /**
     * Retrieves this Pet's breed value.
     *
     * @return this Pet's breed value.
     */
    public Breed getBreed()
    {
        return breed;
    }

    /**
     * Retrieves breed StringProperty of this Pet.
     *
     * @return breed StringProperty of this Pet.
     */
    /*public StringProperty breedProperty()
    {
        return breed;
    }*/
    /**
     * Places a valid id into this Pet's species StringProperty member. species
     * cannot be null, otherwise an exception is thrown.
     *
     * @param species the user-specified species value
     * @throws IllegalArgumentException if species is null
     */
    public void setSpecies(String species)
    {
        if (species != null)
        {
            this.species.set(species);
        } else
        {
            throw new IllegalArgumentException("Species cannot be empty.");
        }
    }

    /**
     * Retrieves this Pet's species value.
     *
     * @return this Pet's species value.
     */
    public String getSpecies()
    {
        return species.get();
    }

    /**
     * Retrieves species StringProperty of this Pet.
     *
     * @return species StringProperty of this Pet.
     */
    public StringProperty speciesProperty()
    {
        return species;
    }

    /**
     * Places a valid id into this Pet's softDeleted member. softDeleted cannot
     * be null, otherwise an exception is thrown.
     *
     * @param bool function-defined softDeleted value
     */
    public void setSoftDeleted(Boolean bool)
    {
        this.softDeleted = bool;
    }

    /**
     * Retrieves this Pet's softDeleted value.
     *
     * @return this Pet's softDeleted value.
     */
    public boolean isSoftDeleted()
    {
        return softDeleted;
    }

    /**
     * String representation of this pet object.
     *
     * @return String representation of this pet object.
     */
    @Override
    public String toString()
    {
        return this.getPetName() + " - " + this.getOwnerName() + " - "
                + this.getSpecies();
    }

}
