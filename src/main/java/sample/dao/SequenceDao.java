package sample.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;
import sample.SequenceExceptions;
import sample.model.Sequence;

/**
 * Created by oleh on 30.05.16.
 */

@Repository
public class SequenceDao {

    @Autowired
    private MongoOperations mongoOperations;

    public Long getNextSequenceId(String key){

        Query query = new Query(Criteria.where("id").is(key));

        Update update = new Update();
        update.inc("sequence",1);

        FindAndModifyOptions options = new FindAndModifyOptions();
        options.returnNew(true);

        Sequence sequence = mongoOperations.findAndModify(query,update,options,Sequence.class);

        if(sequence == null)throw new SequenceExceptions("Unable to get sequence for key: " + key);

        return sequence.getSequence();

    }
}
