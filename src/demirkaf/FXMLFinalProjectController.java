package demirkaf;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.Scanner;
import javafx.application.Platform;
import javafx.beans.InvalidationListener;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Control;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Window;
import javafx.stage.WindowEvent;

/*
Name: F. Fulya Demirkan
File: FXMLFinalProjectController.java
Other Files in this Project: 
- Pet.java
- PetList.java
- Breed.java
- FXMLFinalProject.fxml
- main.css

Main class: demirkaf_FinalProject.java
 */
public class FXMLFinalProjectController implements Initializable
{

    @FXML
    private Label lblError;

    @FXML
    private TextField txtId, txtName, txtOwner, txtSearchName;

    @FXML
    private ComboBox<String> ddlGender, ddlSpecies, ddlBreed;

    @FXML
    private Button btnSave, btnNew, btnToggleSearch, btnEdit, btnDelete,
            btnCancel, btnFirst, btnPrevious, btnNext, btnLast, btnRegister;

    //Localdate variable for birthday variable
    @FXML
    private DatePicker dpBirthdate;

    // main pane for swapping
    @FXML
    private VBox main;

    // subpanes for swapping
    @FXML
    private BorderPane searchPane, mainPane;

    // File output stream
    @FXML
    private PrintWriter fileOut;

    @FXML
    private RadioButton optByPetName, optByOwnerName;

    @FXML
    private TableView<Pet> tblPets, tblSearch;

    // TableColumn property for binding Pet class with TableView
    @FXML
    private TableColumn<Pet, Integer> petId;

    // TableColumn property for binding Breed enum's name with TableView
    @FXML
    private TableColumn<Pet, String> breedColumn;

    // Main ArrayList which stores all the records from pet.txt
    @FXML
    private PetList petList = new PetList();

    @FXML
    private ImageView imgPet;

    @FXML
    private CheckBox chkIsDeleted;

    // current subpane name for swapping subpanes
    private String current = "mainPane";

    /**
     * Changes application screen between search pane and main pane. If user
     * returns to main page clears all the fields in the search pane.
     *
     * @param event triggers the action
     */
    @FXML
    private void swap(ActionEvent event)
    {
        if (current.equals("mainPane"))
        {
            main.getChildren().remove(mainPane);
            main.getChildren().add(searchPane);
            current = "searchPane";
            btnRegister.setDisable(true);
        } else
        {
            main.getChildren().remove(searchPane);
            main.getChildren().add(mainPane);
            current = "mainPane";
            listPets();
            optByPetName.requestFocus();
            tblSearch.setItems(null);
            txtSearchName.setText(null);
            chkIsDeleted.setSelected(false);
            optByPetName.setSelected(true);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        /*
        Adds a confirmation alert to window close (X) button. If user confirms 
        to close the application then saves all items in the ArrayList to 
        pets.txt file.
         */
        Platform.runLater(new Runnable()
        {
            // abstract run method from Runnable class
            @Override
            public void run()
            {
                // get the value of the property Window
                Window win = btnSave.getScene().getWindow();
                // add an eventhandler for close requests
                win.setOnCloseRequest(new EventHandler<WindowEvent>()
                {
                    /*
                    Display a confirmation alert, if user confirms to close 
                    save items in the ArrayList to pets.txt file and exit, 
                    otherwise cancels the request and app stays open.
                     */
                    @Override
                    public void handle(WindowEvent event)
                    {
                        Alert alert = new Alert(Alert.AlertType.CONFIRMATION,
                                "Are you sure you want to exit?",
                                ButtonType.YES, ButtonType.NO);
                        alert.setHeaderText(null);
                        alert.setTitle("Exit Program");
                        Optional<ButtonType> result = alert.showAndWait();
                        if (result.get().equals(ButtonType.YES))
                        {
                            saveToFile();
                            Platform.exit();
                        } else
                        {
                            event.consume();
                        }
                    }
                });
            }
        });

        // options for gender ComboBox
        String[] strGender =
        {
            "Female", "Male"
        };

        // Collect options into an observable list and set ComboBox items
        ObservableList obsGender = FXCollections.observableArrayList(strGender);
        ddlGender.setItems(obsGender);

        //options for species ComboBox
        String[] strSpecies =
        {
            "Cat", "Dog", "Bird", "Dragon", "DireWolf", "Rabbit", "Hamster",
            "Fish", "Other Pet Species"
        };

        // Collect options into an observable list and set ComboBox items
        ObservableList obsSpecies = FXCollections.
                observableArrayList(strSpecies);
        ddlSpecies.setItems(obsSpecies);

        /* According to users choice of species collect appropriate enum 
        members into an array. Collect array items into an Observable List and
        set items of breed ComboBox.
         */
        ddlSpecies.getSelectionModel().selectedItemProperty().
                addListener(new InvalidationListener()
                {
                    @Override
                    public void invalidated(javafx.beans.Observable observable)
                    {
                        ArrayList strBreed = new ArrayList();
                        // clear current array to prevent the possibility of 
                        // the observable list keeps previous data.
                        strBreed.clear();

                        if (ddlSpecies.getSelectionModel().
                                getSelectedItem() != null)
                        {
                            // clear ComboBox value to prevent the possibility  
                            // of the ComboBox keeps previous data.
                            ddlBreed.setValue(null);
                            for (int i = 1; i <= Breed.values().length; i++)
                            {
                                if (Breed.getBreedByIndex(i).getCategory()
                                        == ddlSpecies.getSelectionModel().
                                                getSelectedItem())
                                {
                                    String breed = Breed.getBreedByIndex(i).
                                            getName();
                                    strBreed.add(breed);
                                }
                            }
                        }

                        ObservableList obsBreed = FXCollections.
                                observableArrayList(strBreed);
                        ddlBreed.setItems(obsBreed);

                        // change image according to selected species
                        if (ddlSpecies.getSelectionModel().
                                getSelectedIndex() >= 0)
                        {
                            changeImage(ddlSpecies.getSelectionModel().
                                    getSelectedIndex());
                        }

                    }
                });

        /*
        Reads records from existing file and sends parseRecord() function 
        to split them into fields.  If file not exists then throw IOException
         */
        try
        {
            File filePets = new File("pets.txt");

            //if file exists then read every record already exists in the 
            //pets.txt file and pass records to parseRecord() function.
            if (filePets.exists())
            {
                Scanner scanner = new Scanner(filePets);
                while (scanner.hasNextLine())
                {
                    String s = scanner.nextLine();
                    parseRecord(s);
                }
                scanner.close();
            }
        } catch (IOException ex)
        {
            ex.printStackTrace();
        }

        // display non-deleted pets in the TableView
        listPets();

        /* if there is at least one item in the ArrayList then display the 
        first pet on the list. If there is no pet in the ArrayList then display
        an empty entry in edit mode.
         */
        if (petList.size() > 0)
        {
            displayPet(petList.get(0));
        } else
        {
            txtId.setText(String.valueOf(petList.size() + 1));
            toggleEditable(true);
        }
    }

    /**
     * Creates a new object for each record using split fields and adds this
     * objects to the ArrayList.
     *
     * @param record A record represents pet object
     */
    @FXML
    private void parseRecord(String record)
    {

        // split the fields using | delimiter
        String[] fields = record.split("\\|");

        // create a new default pet object.
        Pet pet = new Pet();

        // set values of pet object.
        pet.setPetId(Integer.parseInt(fields[0]));
        pet.setPetName(fields[1]);
        pet.setOwnerName(fields[2]);
        pet.setBirthdate(LocalDate.parse(fields[3]));
        pet.setGender(fields[4]);
        pet.setSpecies(fields[5]);
        pet.setBreed(Breed.valueOf(fields[6]));
        pet.setSoftDeleted(Boolean.valueOf(fields[7]));

        petList.add(pet);
    }

    /**
     * Retrieves all non-deleted pet objects from ArrayList and displays on
     * TableView.
     */
    @FXML
    private void listPets()
    {
        /* tempList retrieves all items from ArrayList and holds only the 
        non-deleted ones. 
         */
        ArrayList tempList = new ArrayList();
        for (int i = 0; i < petList.size(); i++)
        {
            // if item is a non-deleted one then add to tempList
            if (!petList.get(i).isSoftDeleted())
            {
                tempList.add(petList.get(i));
            }
        }

        // collect all items in the tempList
        ObservableList obsList = FXCollections.observableArrayList(tempList);

        // refresh the current TableView to apply changes in the observableList
        tblPets.refresh();

        // add all items in the observableList to the TableView
        tblPets.setItems(obsList);

        /* 
        InvalidationListener to retrieve the index of the selected item on 
        the TableView and display the information of selected item.
         */
        tblPets.getSelectionModel().selectedItemProperty().
                addListener(new InvalidationListener()
                {
                    @Override
                    public void invalidated(javafx.beans.Observable observable)
                    {
                        if (!tblPets.getSelectionModel().isEmpty())
                        {
                            Pet pet = tblPets.getSelectionModel().
                                    getSelectedItem();
                            displayPet(pet);
                        }
                    }
                });
    }

    /**
     * Displays information of selected pet object from TableView. Toggles to
     * display mode to prevent accidental changes caused by user.
     *
     * @param pet Pet object to display
     */
    @FXML
    private void displayPet(Pet pet)
    {
        toggleEditable(false);

        txtId.setText(String.valueOf(pet.getPetId()));
        txtName.setText(pet.getPetName());
        txtOwner.setText(pet.getOwnerName());
        dpBirthdate.setValue(pet.getBirthdate());
        ddlGender.getSelectionModel().select(pet.getGender());
        ddlSpecies.getSelectionModel().select(pet.getSpecies());
        ddlBreed.getSelectionModel().select(pet.getBreed().getName());
    }

    /**
     * Creates a new default pet object for adding a new object to the ArrayList
     * or calls existing pet object from ArrayList for editing. If user given
     * values are valid then sets object's values to values given by user,
     * otherwise displays an error message and waits for new entry. If object is
     * added/updated correctly then toggles to display mode, displays current
     * item and saves change to pets.txt.
     *
     * @param event triggers the save action
     */
    @FXML
    private void addEdit(ActionEvent event)
    {
        /*
        Since we don't have a backup system in this project, this function calls
        saveToFile() function after each add/edit to prevent possible lost data 
        situations such as power failure between opening and the closing of 
        the application.
         */
 /* if index of item is in ArrayList retrieve object, otherwise create 
        a default pet object. */
        if (Integer.parseInt(txtId.getText()) <= petList.size())
        {
            // retrive current pet object from ArrayList
            Pet pet = petList.get(Integer.parseInt(txtId.getText()) - 1);

            // check if all values are valid
            // if they are valid then display updated item, save to pets.txt 
            // and toggle to display mode
            if (setItem(pet))
            {
                petList.set(Integer.parseInt(txtId.getText()) - 1, pet);
                lblError.setText("");
                toggleEditable(false);
                saveToFile();
            } else
            {
                // if at least one of the values is not valid display error
                lblError.setText("Fields cannot be empty.");
                // toggle to edit mode again.
                toggleEditable(true);
            }
        } else
        {
            // create a default pet object
            Pet pet = new Pet();

            // check if all values are valid
            // if they are valid then display newly added item, save to pets.txt 
            // and toggle to display mode
            if (setItem(pet))
            {
                petList.add(pet);
                lblError.setText("");
                clearFields();
                listPets();
                toggleEditable(false);
                saveToFile();
            } else
            {
                lblError.setText("Fields cannot be empty.");
                toggleEditable(true);
            }
        }
    }

    /**
     * Places a value to each member of pet object if all of the values are
     * valid and returns true, otherwise returns false. Changes border color of
     * invalid fields.
     *
     * @param pet Pet object for assigning new values
     * @return true if each member of the pet object is valid, otherwise false.
     */
    @FXML
    private boolean setItem(Pet pet)
    {
        // total number of input fields need to be validated.
        int check = 6;

        /* 
        if any one of the values are invalid boolean value turns to false 
        indexes with the false error styleClass will be added, for others error 
        styleClass will be removed.
         */
        boolean[] bool =
        {
            true, true, true, true, true, true
        };

        if (!validateString(txtName.getText()))
        {
            bool[0] = false;
            check--;
        }
        if (!validateString(txtOwner.getText()))
        {
            bool[1] = false;
            check--;
        }
        if (dpBirthdate.getValue() == null)
        {
            bool[2] = false;
            check--;
        }
        if (ddlGender.getSelectionModel().getSelectedItem() == null)
        {
            bool[3] = false;
            check--;
        }
        if (ddlSpecies.getSelectionModel().getSelectedItem() == null)
        {
            bool[4] = false;
            check--;
        }
        if (ddlBreed.getSelectionModel().getSelectedItem() == null)
        {
            bool[5] = false;
            check--;
        }

        // if any one of the values is invalid then set style to error
        if (check != 6)
        {
            setStyle(bool);
            return false;
        } else
        {
            pet.setPetId(Integer.parseInt(txtId.getText()));
            pet.setPetName(txtName.getText());
            pet.setOwnerName(txtOwner.getText());
            pet.setBirthdate(dpBirthdate.getValue());
            pet.setGender(ddlGender.getSelectionModel().getSelectedItem());
            pet.setSpecies(ddlSpecies.getSelectionModel().getSelectedItem());
            pet.setBreed(Breed.getBreedByName(ddlBreed.getSelectionModel().
                    getSelectedItem()));
            pet.setSoftDeleted(Boolean.FALSE);
            return true;
        }
    }

    /**
     * Changes style condition of a specific input field. If field is empty adds
     * error styleClass, if field is not empty anymore removes error styleClass.
     *
     * @param control Control object parameter
     * @param bool function-defined value (true: remove styleClass, false: add
     * styleClass)
     */
    @FXML
    private void setStyle(boolean[] bool)
    {

        Control[] cont =
        {
            txtName, txtOwner, dpBirthdate, ddlGender, ddlSpecies, ddlBreed
        };

        for (int i = 0; i < cont.length; i++)
        {
            if (!bool[i])
            {
                // check if stleClass is already added. If not add.
                if (!cont[i].getStyleClass().contains("error"))
                {
                    cont[i].getStyleClass().add("error");
                    if (cont[i] instanceof TextField)
                    {
                        // if TextField input is empty after trim, clear input
                        TextField b = (TextField) cont[i];
                        b.setText("");
                    }
                }
            } else
            {
                cont[i].getStyleClass().remove("error");
            }
        }
    }

    /**
     * Validates String entries and returns false if value is null or empty or
     * returns true if value is valid.
     *
     * @param str user defined String value
     * @return false if value is null or empty or true if value is valid.
     */
    @FXML
    private boolean validateString(String str)
    {
        if (str != null && !str.trim().isEmpty())
        {
            return true;
        } else
        {
            return false;
        }
    }

    /**
     * Changes editable or disable properties of all user input fields according
     * to the mode (True: edit mode / False: display mode) and requests focus
     * for pet name TextField.
     *
     * @param bool mode defining parameter
     */
    @FXML
    private void toggleEditable(boolean bool)
    {
        /* 
        this method is separate from toggleEdit function which is fired from
        btnEdit because different functions is also using this function with
        sending different boolean values according to the need.
         */
        txtName.setEditable(bool);
        txtOwner.setEditable(bool);
        dpBirthdate.setEditable(bool);
        ddlGender.setDisable(!bool);
        ddlSpecies.setDisable(!bool);
        ddlBreed.setDisable(!bool);

        btnEdit.setDisable(bool);
        btnNew.setDisable(bool);
        btnDelete.setDisable(bool);
        btnSave.setDisable(!bool);
        btnCancel.setDisable(!bool);

        btnToggleSearch.setDisable(bool);
        btnFirst.setDisable(bool);
        btnPrevious.setDisable(bool);
        btnNext.setDisable(bool);
        btnLast.setDisable(bool);

        txtName.requestFocus();
    }

    /**
     * Toggles between edit mode and display mode according to true/false value
     * which is passed from other functions.
     *
     * @param event triggers the action
     */
    @FXML
    private void toggleEdit(ActionEvent event)
    {
        toggleEditable(true);
    }

    /**
     * Toggles to display mode, removes error styleClass from input fields or if
     * user is canceling the process of the adding a new item clears fields.
     *
     * @param event triggers the action
     */
    @FXML
    private void cancel(ActionEvent event)
    {

        if (Integer.parseInt(txtId.getText()) >= petList.size())
        {
            clearFields();
        } else
        {
            boolean[] bool =
            {
                true, true, true, true, true, true
            };
            displayPet(petList.get(Integer.parseInt(txtId.getText()) - 1));
            setStyle(bool);
        }
        toggleEditable(false);
        lblError.setText("");
    }

    /**
     * Clears fields, toggles to edit mode and requests focus to pet name
     * TextField.
     *
     * @param event triggers the action
     */
    @FXML
    private void newEntry(ActionEvent event)
    {
        clearFields();
        toggleEditable(true);
        txtName.requestFocus();
    }

    /**
     * Changes softDelete member of selected record to false according to user
     * confirmation with passing index of selected record to remove function of
     * the ArrayList and displays next or previous pet in the TableView
     * according to index of removed pet.
     *
     * @param event triggers the action
     */
    @FXML
    private void delete(ActionEvent event)
    {
        /*
        Since we don't have a backup system in this project, this function calls
        saveToFile() function after each delete to prevent possible lost data 
        situations such as power failure between opening and the closing of 
        the application.
         */

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION,
                "Are you sure you wish to delete this entry?", ButtonType.YES,
                ButtonType.NO);
        alert.setTitle("Delete Entry");
        alert.setHeaderText(null);
        Optional<ButtonType> result = alert.showAndWait();

        if (result.get() == ButtonType.YES)
        {
            int index = (Integer.parseInt(txtId.getText()) - 1);
            petList.remove(index);
            if (index != 0)
            {
                tblPets.getSelectionModel().selectPrevious();
            } else
            {
                tblPets.getSelectionModel().selectNext();
            }
            listPets();
            saveToFile();
        } else
        {
            cancel(new ActionEvent());
        }
    }

    /**
     * Clears all user input fields, changes image to default, toggles to edit
     * mode, removes all error styleClass and requests focus for pet name
     * TextField.
     */
    @FXML
    private void clearFields()
    {
        //sets petId to the next available id (Incremented petList size.)
        txtId.setText(String.valueOf(petList.size() + 1));
        txtName.clear();
        txtOwner.clear();
        dpBirthdate.setValue(null);
        ddlGender.getSelectionModel().clearSelection();
        ddlSpecies.getSelectionModel().clearSelection();
        ddlBreed.getSelectionModel().clearSelection();
        toggleEditable(true);
        txtName.requestFocus();
        Image img = new Image(getClass().getResource("res/other.png").
                toExternalForm());
        imgPet.setImage(img);

        boolean[] bool =
        {
            true, true, true, true, true, true
        };
        setStyle(bool);
    }

    /**
     * Starts a new file stream for pets.txt, retrieves all pet objects from
     * ArrayList and stores all records into file using | as a separator and
     * closes the stream.
     *
     * @throws IOException if file is not exists.
     */
    @FXML
    private void saveToFile()
    {
        try
        {
            fileOut = new PrintWriter(new BufferedWriter(new FileWriter("pets.txt", false)));
            // check if output stream is not null
            if (fileOut != null)
            {
                for (int i = 0; i < petList.size(); i++)
                {
                    fileOut.println(petList.get(i).getPetId() + "|"
                            + petList.get(i).getPetName() + "|"
                            + petList.get(i).getOwnerName() + "|"
                            + petList.get(i).getBirthdate() + "|"
                            + petList.get(i).getGender() + "|"
                            + petList.get(i).getSpecies() + "|"
                            + petList.get(i).getBreed() + "|"
                            + petList.get(i).isSoftDeleted());
                }
                fileOut.close();
            };
        } catch (IOException ex)
        {
            ex.printStackTrace();
        }
    }

    /**
     * Navigates between records in the TableView according to direction
     * selected and displays the pet selected in the TableView. If next index
     * does not belong to an existing record then activates edit mode.
     *
     * @param event triggers the action
     */
    @FXML
    private void navigation(ActionEvent event)
    {
        int index = Integer.parseInt(txtId.getText()) - 1;

        if (event.getSource() == btnFirst)
        {
            index = 0;
        } else if (event.getSource() == btnNext)
        {
            /* if next index belongs to an existing record then increment the 
            current index.
             */
            if (index < petList.size())
            {
                index++;
            }
        } else if (event.getSource() == btnPrevious)
        {
            /* if current index is not zero or greater than petList size then
            decraments the current index */
            if (index > petList.size() || index != 0)
            {
                index--;
            }
        } else if (event.getSource() == btnLast)
        {
            index = petList.size() - 1;
        }

        /* if next index belongs to an existing record then displays the pet 
        item. Otherwise, clear fields, toggle to edit mode and request focus 
        to pet name TextField.
         */
        if (index < petList.size())
        {
            tblPets.getSelectionModel().select(index);
            Pet pet = petList.get(index);
            displayPet(pet);
        } else
        {
            clearFields();
        }
    }

    /**
     * Searches (case-insensitive) by pet name or owner name with the option of
     * searching in deleted pets and displays the possible matches on the
     * TableView. If there is no possible match or user enters an invalid value
     * then displays a warning.
     *
     * @param event triggers the action
     */
    @FXML
    private void search(ActionEvent event)
    {
        // if user selects search in deleted item sets register button enabled, 
        // else disables.
        if (chkIsDeleted.isSelected())
        {
            btnRegister.setDisable(false);
        } else
        {
            btnRegister.setDisable(true);
        }

        String query = txtSearchName.getText();

        // arraylist to hold the pet items which matches with the query
        ArrayList tempList = new ArrayList();

        if (query == null || query.equals(""))
        {
            Alert alert = new Alert(Alert.AlertType.WARNING,
                    "Please enter a name.", ButtonType.OK);
            alert.setHeaderText("");
            alert.setTitle("Search Value");
            Optional<ButtonType> result = alert.showAndWait();
            txtSearchName.setText("");
            txtSearchName.requestFocus();
        } else
        {
            // changes the case of string for case-insensitive search
            query = query.trim().toLowerCase();

            if (optByPetName.isSelected())
            {
                for (int i = 0; i < petList.size(); i++)
                {
                    if (!chkIsDeleted.isSelected())
                    {
                        /* 
                        if pet is NOT deleted and lowercase petName contains
                        the query, store the pet item into arraylist 
                         */
                        if (!petList.get(i).isSoftDeleted()
                                && petList.get(i).getPetName().toLowerCase().
                                        contains(query))
                        {
                            tempList.add(petList.get(i));
                        }
                    } else
                    {
                        /* 
                        if pet IS deleted and lowercase petName contains
                        the query, store the pet item into arraylist 
                         */
                        if (petList.get(i).isSoftDeleted()
                                && petList.get(i).getPetName().toLowerCase().
                                        contains(query))
                        {
                            tempList.add(petList.get(i));
                        }
                    }
                }
            } else if (optByOwnerName.isSelected())
            {
                for (int i = 0; i < petList.size(); i++)
                {
                    if (!chkIsDeleted.isSelected())
                    {
                        /* 
                        if pet is NOT deleted and lowercase ownerName 
                        contains the query, store the pet item into arraylist 
                         */
                        if (!petList.get(i).isSoftDeleted()
                                && petList.get(i).getOwnerName().toLowerCase().
                                        contains(query))
                        {
                            tempList.add(petList.get(i));
                        }
                    } else
                    {
                        /* 
                        if pet IS deleted and lowercase ownerName 
                        contains the query, store the pet item into arraylist 
                         */
                        if (petList.get(i).isSoftDeleted()
                                && petList.get(i).getOwnerName().toLowerCase().
                                        contains(query))
                        {
                            tempList.add(petList.get(i));
                        }
                    }
                }
            }

            // clear input field
            txtSearchName.setText(null);

            /* 
            in order to add Breed enum's name value to TableView, I used 
            cellValueFactory in here. 
            When I add this in FXML instead of here,it only shows 
            enum (DOG_GOLDEN_RETRIEVER) not the name (Golden Retriever)
             */
            // TODO: find a way to create it in FXML
            breedColumn.setCellValueFactory(cellData
                    -> new SimpleStringProperty(cellData.getValue().getBreed().
                            getName()));

            if (tempList.size() > 0)
            {
                // collect items in the arraylist and send them into TableView
                ObservableList<Pet> obsList
                        = FXCollections.observableArrayList(tempList);
                tblSearch.setItems(obsList);
            } else
            {
                Alert alert = new Alert(Alert.AlertType.WARNING,
                        "No matching records found.", ButtonType.OK);
                alert.setHeaderText("");
                alert.setTitle("Search");
                Optional<ButtonType> result = alert.showAndWait();
                txtSearchName.requestFocus();
                tblSearch.setItems(null);
                txtSearchName.setText(null);
            }
        }
    }

    /**
     * Retrieves the petId of the selected pet, closes search pane and displays
     * information of selected pet on the main pane. Clears TableView content
     * and TextField, changes RadioButton selection to default. If user selects
     * a deleted item displays a warning to register.
     *
     * @param event triggers the action
     */
    @FXML
    private void select(ActionEvent event
    )
    {
        int index = tblSearch.getSelectionModel().getSelectedItem().
                getPetId() - 1;
        Pet pet = petList.get(index);

        if (chkIsDeleted.isSelected() && pet.isSoftDeleted())
        {
            Alert alert = new Alert(Alert.AlertType.WARNING,
                    "You need to register the pet to see details.",
                    ButtonType.OK);
            alert.setHeaderText("");
            alert.setTitle("Register");
            Optional<ButtonType> result = alert.showAndWait();

        } else
        {
            swap(new ActionEvent());
            tblSearch.setItems(null);
            txtSearchName.setText(null);
            chkIsDeleted.setSelected(false);
            listPets();
            tblPets.getSelectionModel().select(index, petId);
            displayPet(pet);
        }
    }

    /**
     * Changes image according to the selected species.
     *
     * @param index index value of selected species
     */
    @FXML
    private void changeImage(int index
    )
    {
        String[] imgLink =
        {
            "cat", "dog", "bird", "dragon", "direwolf", "rabbit", "hamster",
            "fish", "other"
        };

        String link = imgLink[index];
        Image img = new Image(getClass().getResource("res/" + link + ".png").
                toExternalForm());
        imgPet.setImage(img);
    }

    /**
     * Changes softDelete member of a pet object to true to register the pet
     * again according to user confirmation.
     *
     * @param event triggers the action
     */
    @FXML
    private void retrieveItem(ActionEvent event
    )
    {
        if (!tblSearch.getSelectionModel().isEmpty())
        {
            int index = tblSearch.getSelectionModel().getSelectedItem().
                    getPetId() - 1;
            Pet pet = petList.get(index);
            if (chkIsDeleted.isSelected() && pet.isSoftDeleted())
            {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION,
                        "Are you sure you wish to register this pet again?",
                        ButtonType.YES, ButtonType.NO);
                alert.setHeaderText("");
                alert.setTitle("Add");
                Optional<ButtonType> result = alert.showAndWait();
                if (result.get().equals(ButtonType.YES))
                {
                    pet.setSoftDeleted(Boolean.FALSE);
                }
            }
        }
    }
}
