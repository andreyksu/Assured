Java REST-Assured

1. К одному зарпосу одна спецификация, т.к. происходит переопределение значений. И при наличии всяких хелперов можно получить заранее не понятный запрос.
2. Не стоит использовать статическую конфигурацию. Т.к. при многопоточке будет печаль.
	Т.е. что-то подобное плохо: RestAssured.baseURI = "http://cookiemonster.com";
3. Порядок фильтров так же имеет значение.
	Через фильтры можно делать авторизацию.

	Так не рекамендуется:
		OAuth2Scheme scheme = new OAuth2Scheme();
		scheme.setAccessToken(accessToken);
		RestAssured.authentication = scheme;

		Или

		RestAssured.basePath = basePath;


// плохая практика написания:
given().header("content-type", "application/json").header("accept", "application/json")...;

// хорошая практика написания:
given().contentType(ContentType.JSON).accept(ContentType.JSON)...;