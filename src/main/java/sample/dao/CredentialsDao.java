package sample.dao;

import org.springframework.data.mongodb.repository.MongoRepository;
import sample.model.Credentials;

import java.util.List;

/**
 * Created by oleh on 31.05.16.
 */
public interface CredentialsDao extends MongoRepository<Credentials,String> {

    public Credentials findByFirstName(String firstName);

    public List<Credentials> findByLastName(String lastName);

}
