package za.co.swingy.model;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class ValidationModel {
    ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    Validator validator = factory.getValidator();
    ValidationModel command = new ValidationModel();

    @NotNull
    @Range(max = 3, min = 1, message = "Try again!")
    int welcomeCmd;
    @NotNull
    @Range(max = 3, min = 1, message = "Choose a valid class!")
    int createCharacterClass;
    @NotNull
    @Size(max = 50, message = "Name to long")
    String createCharacterName;
    @NotNull
    @Range(max = 500, min = 1, message = "Choose a valid class!")
    int loadGame;

    public ValidationModel(){
        super();
    }

    public boolean intValidator(int cmd, String class_){
        switch (class_) {
            case "gameLoop":
                this.welcomeCmd = cmd;
                break;
            case "createHeroClass":
                this.createCharacterClass = cmd;
                break;
            case "getGame":
                this.loadGame = cmd;
                break;
        }

        return consValidation();
    }

    public boolean stringValidator(String name, String class_){
        if(class_.equals("gameLoop")){
            this.createCharacterName = name;
        }

        return consValidation();
    }

    private boolean consValidation() {
        Set<ConstraintViolation<ValidationModel>> constraintViolations = validator.validate(command);
        if (constraintViolations.size() <= 0) {
            return(true);
        } else {
            for (ConstraintViolation<ValidationModel> violation : constraintViolations) {
                System.out.println(violation.getMessage());
            }
            return (false);
        }
    }
}
