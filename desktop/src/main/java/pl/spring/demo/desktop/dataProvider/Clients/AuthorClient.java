package pl.spring.demo.desktop.dataProvider.Clients;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import pl.spring.demo.to.AuthorTo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class AuthorClient {

	@Value("${server.authorServer.list}")
    private String toListAdress;

	@Value("${server.authorServer.save}")
    private String toSaveAdress;

	@Autowired
	private RestTemplate restTemplate;

	public List<AuthorTo> list() {
		String url =toListAdress;
		AuthorTo[] authors = restTemplate.getForObject(url, AuthorTo[].class);

		return Arrays.asList(authors);
	}

	public AuthorTo saveAuthor(AuthorTo aut) {
		String url = toSaveAdress;
		AuthorTo returned;
		List<HttpMessageConverter<?>> messageConverters = new ArrayList<HttpMessageConverter<?>>();

		messageConverters.add(new MappingJackson2HttpMessageConverter());

		restTemplate.setMessageConverters(messageConverters);
		returned = restTemplate.postForObject(url, aut, AuthorTo.class);
		return returned;
	}

}