package lvh.naheulbeuk;

import java.util.List;

import lvh.naheulbeuk.model.Store;
import lvh.naheulbeuk.repository.StoreRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="store")
public class StoreApi {

	@Autowired
	private StoreRepository storeRepository;
	
	@Autowired
	private UserServices userServices;
	
	@RequestMapping(value="", method = RequestMethod.PUT)
	public ResponseEntity<Store> addStore(@RequestHeader(value="Authorization") String token, @RequestBody Store store) {
		try {
			userServices.checkToken(token);
			return new ResponseEntity<Store>(storeRepository.save(store), HttpStatus.OK);
		} catch (Exception e){
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
		
	}
	
	@RequestMapping(value="/{id}", method = RequestMethod.GET)
	public ResponseEntity<Store> getStore(@RequestHeader(value="Authorization") String token, @PathVariable String id) {
		try {
			userServices.checkToken(token);
			final Store store = storeRepository.findById(id).orElse(null);
			if (store != null) {
				return new ResponseEntity<Store>(store, HttpStatus.OK);
			} else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
		} catch (Exception e){
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
	}
	
	@RequestMapping(value="/all", method = RequestMethod.GET)
	public ResponseEntity<List<Store>> getAll(@RequestHeader(value="Authorization") String token) {
		try {
			userServices.checkToken(token);
			return new ResponseEntity<List<Store>>(storeRepository.findAll(), HttpStatus.OK);
		} catch (Exception e){
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
	}

}