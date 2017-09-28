package demirkaf;

import java.util.ArrayList;

/*
Name: F. Fulya Demirkan
File: PetList.java
Other Files in this Project: 
- Pet.java
- Breed.java
- FXMLFinalProjectController.java
- FXMLFinalProject.fxml
- main.css

Main class: demirkaf_FinalProject.java
 */
/**
 * The PetList class is used to model a pet list array to store pet objects.
 *
 * @author Fulya Demirkan
 */
public class PetList
{

    /**
     * ArrayList for pet objects
     */
    private ArrayList<Pet> petList = new ArrayList();

    /**
     * Constructs an empty PetList ArrayList.
     */
    PetList()
    {

    }

    /**
     * Adds a new pet object to Petlist if it does not already exist according
     * to the petId value. Otherwise throws and exception.
     *
     * @param pet pet object created by the user
     * @throws IllegalArgumentException if petId is already in the list.
     */
    public void add(Pet pet)
    {
        if (!petList.contains(pet))
        {
            petList.add(pet);
        } else
        {
            throw new IllegalArgumentException(pet.getPetName()
                    + " is already in the list");
        }
    }

    /**
     * Replace existing pet object with the passed object.
     *
     * @param index index of this pet object (petId - 1)
     * @param pet pet object created by the user
     */
    public void set(int index, Pet pet)
    {
        petList.set(index, pet);
    }

    /**
     * Removes an existing pet object from the list according given index. Index
     * must be be equal or greater than zero and less than list size. Otherwise
     * an exception is thrown.
     *
     * @param index index value of this pet object (petId - 1)
     * @throws IllegalArgumentException if index is less than zero or greater
     * than the list size
     */
    public void remove(int index)
    {
        if (index >= 0 && index < petList.size())
        {
            petList.get(index).setSoftDeleted(Boolean.TRUE);
        } else
        {
            throw new IllegalArgumentException("Error: Index must be equal or "
                    + "greater than zero and less than list size.");
        }
    }

    /**
     * Retrieves index number of this pet object and returns index.
     *
     * @param pet pet object created by the user
     * @return index value of this pet object (petId - 1)
     */
    public int indexOf(Pet pet)
    {
        int index = pet.getPetId();

        return index;
    }

    /**
     * Retrieves the pet object at the given index. Index must be be equal or
     * greater than zero and less than list size. Otherwise an exception is
     * thrown.
     *
     * @param index index value of this pet object (petId - 1)
     * @return the pet object at the given index.
     * @throws IllegalArgumentException if index is less than zero or greater
     * than the list size
     */
    public Pet get(int index)
    {
        //checks if the index is valid, if it's valid returns the object.
        if (index >= 0 && index < petList.size())
        {
            return petList.get(index);
        } else
        {
            throw new IllegalArgumentException("Error: Index must be equal or "
                    + "greater than zero and less than list size.");
        }
    }

    /**
     * Searches for name the pet object with the given value and returns index
     * of object.
     *
     * @param name pet name parameter
     * @return index value of this pet object (petId - 1)
     */
    public int findPetByName(String name)
    {
        int index = -1;

        for (int i = 0; i < petList.size(); i++)
        {
            if (petList.get(i).getPetName() == name)
            {
                index = i;
            }
        }
        return index;
    }

    /**
     * Retrieves the length of this list.
     *
     * @return the length of this list.
     */
    public int size()
    {
        return this.petList.size();
    }

    /**
     * String representation of the all pet objects in the list.
     *
     * @return String representation of the all pet objects in the list
     */
    public String toString()
    {
        String summary = "";

        for (int i = 0; i < petList.size(); i++)
        {
            summary += petList.get(i) + "\r\n";
        }
        return summary;
    }
}
