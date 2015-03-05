package challange.walmart.internals.jackson;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.hibernate4.Hibernate4Module;
import com.fasterxml.jackson.datatype.jdk7.Jdk7Module;

/**
 * Created by Romero Meireles on 05/03/15.
 */
public class CustomJacksonMapper extends ObjectMapper {

	public CustomJacksonMapper() {
		super();
		registerModule(new Jdk7Module());
		registerModule(new Hibernate4Module());
	}

}