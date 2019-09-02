package za.co.swingy.model;

import org.hibernate.validator.constraints.Range;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Set;

@NotNull(message="is required")
public class ValidationModel {
    @Range(min = 1, max = 3, message = "No such option")
    private int firstMenu = 1; // 1

//    @Size(min = 1, max = 50, message = "Name length >= 1 & <= 50")
//    @NotNull(message = "Name cannot be empty")
    private String heroName  = "d "; // name 4

    @Range(min = 1, max = 3, message = "No such class")
    private int createHeroClass = 1; // 2

    @Range(min = 1, max = 30, message = "No such game")
    private int loadGame = 1; // 3

    @Size(min = 1, max = 1, message = "Name length >= 1 & <= 1\n")
    @NotNull(message = "is required")
    private String move = " "; // movement 5

    public ValidationModel(){}

    public ValidationModel(int cmd, int class_) {
        if (class_ == 1) {
            firstMenu = cmd;
        } else if (class_ == 2) {
            createHeroClass = cmd;
        } else if (class_ == 3) {
            loadGame = cmd;
        }
    }

    public ValidationModel(String cmd, int class_) {

        if (class_ == 4) {
            heroName = cmd;
        } else if (class_ == 5) {
            move = cmd;
        }
    }

    public boolean validator(int cmd, String string, int class_){
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        ValidationModel user;
        if (cmd == 0) {
            System.out.println("cmd was set to 0");
            user = new ValidationModel(string, class_);
        } else {
            user = new ValidationModel(cmd, class_);
        }

        Set<ConstraintViolation<ValidationModel>> constraintViolations = validator.validate(user);

        if (constraintViolations.size() > 0) {
            for (ConstraintViolation<ValidationModel> violation : constraintViolations) {
                System.out.println(violation.getMessage());
            }
            return (false);
        } else {
            return (true);
        }
    }
}
