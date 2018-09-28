package lvh.naheulbeuk.repository;

import java.util.Optional;

import lvh.naheulbeuk.model.User;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {
	
	public Optional<User> findByToken(final String id);
	
	public Optional<User> findByGivenName(final String name);

}
