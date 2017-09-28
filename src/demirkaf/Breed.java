package demirkaf;

import java.util.HashMap;

/*
Name: F. Fulya Demirkan
File: Breed.java
Other Files in this Project: 
- Pet.java
- PetList.java
- FXMLFinalProjectController.java
- FXMLFinalProject.fxml
- main.css

Main class: demirkaf_FinalProject.java
 */
/**
 * The Breed enum represents different pet breeds and also contains index
 * numbers, category for each species and String names of each breed.
 *
 * @author Fulya Demirkan
 */
public enum Breed
{
    DOG_US_BULLDOG(1, "Dog", "American Bulldog"),
    DOG_US_COCKER_SPANIEL(2, "Dog", "American Cocker Spaniel"),
    DOG_US_PIT_BULL_TERRIER(3, "Dog", "American Pit Bull Terrier"),
    DOG_US_STAFFORDSHIRE_TERRIER(4, "Dog", "American Staffordshire Terrier"),
    DOG_BASSET_HOUND(5, "Dog", "Basset Hound"),
    DOG_BEAGLE(6, "Dog", "Beagle"),
    DOG_BICHON_FRISE(7, "Dog", "Bichon Frise"),
    DOG_BORDER_COLLIE(8, "Dog", "Border Collie"),
    DOG_BOSTON_TERRIER(9, "Dog", "Boston Terrier"),
    DOG_BOXER(10, "Dog", "Boxer"),
    DOG_CAVALIER_SPANIEL(11, "Dog", "Cavalier King Charles Spaniel"),
    DOG_CHIHUAHUA(12, "Dog", "Chihuahua"),
    DOG_DACHSHUND(13, "Dog", "Dachshund"),
    DOG_DOBERMAN_PINSCHER(14, "Dog", "Doberman Pinscher"),
    DOG_ENG_BULLDOG(15, "Dog", "English Bulldog"),
    DOG_ENG_SPRINGER_SPANIEL(16, "Dog", "English Springer Spaniel"),
    DOG_FR_BULLDOG(17, "Dog", "French Bulldog"),
    DOG_GER_SHEPHERD(18, "Dog", "German Shepherd"),
    DOG_GER_POINTER(19, "Dog", "German Shorthaired Pointer"),
    DOG_GOLDEN_RETRIEVER(20, "Dog", "Golden Retriever"),
    DOG_GREAT_DANE(21, "Dog", "Great Dane"),
    DOG_JACK_RUSSELL(22, "Dog", "Jack Russell Terrier"),
    DOG_LABRADOR_RETRIEVER(23, "Dog", "Labrador Retriever"),
    DOG_MINIATURE_PINSCHER(24, "Dog", "Miniature Pinscher"),
    DOG_MINIATURE_POODLE(25, "Dog", "Miniature Poodle"),
    DOG_MINIATURE_SCHNAUZER(26, "Dog", "Miniature Schnauzer"),
    DOG_MIXED(27, "Dog", "Mixed Breed Dog"),
    DOG_PEKINGESE(28, "Dog", "Pekingese"),
    DOG_POMERANIAN(29, "Dog", "Pomeranian"),
    DOG_POODLE(30, "Dog", "Poodle"),
    DOG_PUG(31, "Dog", "Pug"),
    DOG_ROTTWEILER(32, "Dog", "Rottweiler"),
    DOG_SHIH_TZU(33, "Dog", "Shih Tzu"),
    DOG_SIBERIAN_HUSKY(34, "Dog", "Siberian Husky"),
    DOG_STANDARD_POODLE(35, "Dog", "Standard Poodle"),
    DOG_TOY_POODLE(36, "Dog", "Toy Poodle"),
    DOG_YORKSHIRE_TERRIER(37, "Dog", "Yorkshire Terrier"),
    DOG_OTHER(38, "Dog", "Other Dog Breeds"),
    CAT_ABYSSINIAN(39, "Cat", "Abyssinian"),
    CAT_US_BOBTAIL(40, "Cat", "American Bobtail"),
    CAT_US_SHORTHAIR(41, "Cat", "American Shorthair"),
    CAT_BENGAL(42, "Cat", "Bengal"),
    CAT_BIRMAN(43, "Cat", "Birman"),
    CAT_BOMBAY(44, "Cat", "Bombay"),
    CAT_BRITISH_SHORTHAIR(45, "Cat", "British Shorthair"),
    CAT_BURMESE(46, "Cat", "Burmese"),
    CAT_CHARTREUX(47, "Cat", "Chartreux"),
    CAT_CHINESE_LI_HUA(48, "Cat", "Chinese Li Hua"),
    CAT_DEVON_REX(49, "Cat", "Devon Rex"),
    CAT_EXOTIC(50, "Cat", "Exotic"),
    CAT_HIMALAYAN(51, "Cat", "Himalayan"),
    CAT_MAINE_COON(52, "Cat", "Maine Coon"),
    CAT_MIXED_BREED(53, "Cat", "Mixed Breed Cat"),
    CAT_NEBELUNG(54, "Cat", "Nebelung"),
    CAT_NORWEGIAN_FOREST(55, "Cat", "Norwegian Forest"),
    CAT_OCICAT(56, "Cat", "Ocicat"),
    CAT_PERSIAN(57, "Cat", "Persian"),
    CAT_RAGAMUFFIN(58, "Cat", "Ragamuffin"),
    CAT_RAGDOLL(59, "Cat", "Ragdoll"),
    CAT_RUSSIAN_BLUE(60, "Cat", "Russian Blue"),
    CAT_SCOTTISH_FOLD(61, "Cat", "Scottish Fold"),
    CAT_SIAMESE(62, "Cat", "Siamese"),
    CAT_SIBERIAN(63, "Cat", "Siberian"),
    CAT_TONKINESE(64, "Cat", "Tonkinese"),
    CAT_TURKISH_ANGORA(65, "Cat", "Turkish Angora"),
    CAT_OTHER(66, "Cat", "Other Cat Breeds"),
    BIRD_BIRD(67, "Bird", "Bird"),
    DRAGON_DRAGON(68, "Dragon", "Dragon"),
    DIREWOLF_DIREWOLF(69, "DireWolf", "DireWolf"),
    RABBIT_RABBIT(70, "Rabbit", "Rabbit"),
    HAMSTER_HAMSTER(71, "Hamster", "Hamster"),
    FISH_FISH(72, "Fish", "Fish"),
    OTHER_PET_OTHER_PET(73, "Other Pet Species", "Other Pet Breeds");

    private int breedCode;
    private String category;
    private String name;

    private static HashMap<Integer, Breed> lookupByNumber;
    private static HashMap<String, Breed> lookupByName, lookupByCategory;

    private Breed(int breedCode, String category, String name)
    {
        this.breedCode = breedCode;
        this.category = category;
        this.name = name;
    }

    /**
     * Returns breed's predefined String name.
     *
     * @return breed's predefined String name.
     */
    public String getName()
    {
        return name;
    }

    /**
     * Returns breed's predefined index.
     *
     * @return breed's predefined index.
     */
    public int getBreedCode()
    {
        return breedCode;
    }

    /**
     * Returns breed's predefined index.
     *
     * @return breed's predefined index.
     */
    public String getCategory()
    {
        return category;
    }

    /**
     * Reverse Breed enum lookup according to the index number of enum.
     */
    private static void initNumberLookup()
    {
        // create the HashMap object with Integer keys and Breed values
        lookupByNumber = new HashMap<Integer, Breed>();

        // for each Breed constant in the enum:
        for (Breed b : values())
        {
            // put a new element into the HashMap, using
            // the breedCode as the key and the constant as the value
            lookupByNumber.put(b.breedCode, b);
        }
    }

    /**
     * Returns any Breed enum with reverse lookup according to index number
     * parameter.
     *
     * @param breedCode breed's predefined index.
     * @return any Breed enum according to index number parameter.
     * @throws IllegalArgumentException if breed code is invalid
     */
    public static Breed getBreedByIndex(int breedCode)
    {
        if (lookupByNumber == null)
        {
            initNumberLookup();
        }
        Breed b = lookupByNumber.get(breedCode);

        if (b == null)
        {
            throw new IllegalArgumentException("Invalid breed code");
        }
        return b;
    }

    /**
     * Reverse Breed enum lookup according to the String name of enum.
     */
    private static void initNameLookup()
    {
        // create the HashMap object with String keys and Breed values
        lookupByName = new HashMap<String, Breed>();

        // for each Breed constant in the enum:
        for (Breed b : values())
        {
            // put a new element into the HashMap, using
            // the name as the key and the constant as the value
            lookupByName.put(b.name, b);
        }
    }

    /**
     * Returns any Breed enum with reverse lookup according to String name
     * parameter.
     *
     * @param name breed's predefined String name.
     * @return any Breed enum according to String name parameter.
     * @throws IllegalArgumentException if breed name is invalid
     */
    public static Breed getBreedByName(String name)
    {
        if (lookupByName == null)
        {
            initNameLookup();
        }
        Breed b = lookupByName.get(name);

        if (b == null)
        {
            throw new IllegalArgumentException("Invalid breed name");
        }
        return b;
    }

    /**
     * Reverse Breed enum lookup according to the category of enum.
     */
    private static void initCategoryLookup()
    {
        // create the HashMap object with String keys and Breed values
        lookupByCategory = new HashMap<String, Breed>();

        // for each Breed constant in the enum:
        for (Breed b : values())
        {
            // put a new element into the HashMap, using
            // the name as the key and the constant as the value
            lookupByCategory.put(b.category, b);
        }
    }

    /**
     * Returns any Breed enum with reverse lookup according to category
     * parameter.
     *
     * @param category breed's predefined category.
     * @return any Breed enum according to category parameter.
     * @throws IllegalArgumentException if category is invalid
     */
    public static Breed getBreedByCategory(String category)
    {
        if (lookupByCategory == null)
        {
            initCategoryLookup();
        }
        Breed b = lookupByCategory.get(category);

        if (b == null)
        {
            throw new IllegalArgumentException("Invalid breed category");
        }
        return b;
    }
}
