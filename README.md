# Gender Detector
 
## About The Project
 
Webapp that can detect gender by given name. \
Algorithm compares name with tokens stored in database. \
Comparison can take place in two variants. \
First takes into account only the first token of provided name, \
the second checks all tokens and majority rule is used. \
[GUI](https://bgenderator.herokuapp.com) 
built with Thymeleaf template engine. 

 
 ### Built With
 * Java
* Spring
* Gradle
* PostgreSQL
* Hibernate 
* Thymeleaf
* JS
* HTML&CSS
* Deployed on Heroku 
  
  
## Endpoints
 
### GET /api/gender/{name}
Returns detected gender ("MALE", "FEMALE" or "INCONCLUSIVE")
 ###### PATH PARAMETERS
String name - name you want to run the algorithm with
###### QUERY PARAMETERS
String variant specifies mode of performed action. Avaiable values are "1" or "2". Default value is 1.

### GET /api/tokens
Return list of all available tokens.

### GET /api/tokens/female
Return list of all female tokens.

### GET /api/tokens/male
Return list of all male tokens.

### GET /api/tokens/{id}
Return specified token dto.
###### PATH PARAMETERS
Long id - primary key of chosen token

### POST /api/tokens
Create one token.
###### BODY
JSON: token dto

### POST /api/tokens/fill
Create all tokens provided in list.
###### BODY
JSON: token dto

### PUT /api/tokens
Update token.
###### BODY
JSON: token dto

### DELETE /api/tokens{id}
Create one token.
###### PATH PARAMETERS
Long id - primary key of chosen token


## Contact
 
Arkadiusz Jędrzejewski\
arkadiusz.jedrzej@gmail.com


###Links:
* [Project GUI](https://bgenderator.herokuapp.com)
* [Project repo](https://github.com/Arkodiusz/genderDetector)
* [My portfolio](https://arkodiusz.github.io)
