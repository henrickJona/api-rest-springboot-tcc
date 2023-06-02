package root.repository;

import root.model.UserAttributes;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class UserAttributesRepository {
    private List<UserAttributes> list = new ArrayList<UserAttributes>();

    public String predict(UserAttributes p) {
        UserAttributes userAttributes = new UserAttributes();
        // userAttributes.setId(p.getId());
        // userAttributes.setName(p.getName());
        // userAttributes.setQuantity(p.getQuantity());
        // userAttributes.setPrice(p.getPrice());
        // System.out.println(userAttributes + "teste");
         return "teste";
    }
}
