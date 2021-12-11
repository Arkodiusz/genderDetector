# Gender Detector
 
## About The Project
 
It's a simple Java application, that can detect gender by given name. Algorithm compares name with tokens stored in atabase. 
Comparison can take place in two variants. 
First takes into account only the first token of provided name, the second chcecks all tokens and majority rule is used.
 
 
### Built With
 
* Java
* Gradle
* Spring
* PostgreSQL
* Hibernate 

* Deployed on Heroku 
  
  
  
## Endpoints
 
* #### DETECT GENDER
GET /api/gender/{name}
 
Returns detected gender ("MALE", "FEMALE" or "INCONCLUSIVE")
 
##### PATH PARAMETER
String name
The name you want to run the algorithm with
 
##### QUERY PARAMETERS
String variant
Specifying the mode of performed action. 
Avaiable values are "1" or "2".
Default value is 1.
 
 
* #### SHOW TOKENS
GET /api/tokens/{gender}
 
 Returns list of all avaiable tokens of chosen gender.
 
##### PATH PARAMETER
String gender
Specifying which token should be listed. 
Avaiable values are "male" or "female".
 
 
 

 
 
## Contact
 
Arkadiusz Jędrzejewski\
arkadiusz.jedrzej@gmail.com


###Links:
* [Project GUI](https://bgenderator.herokuapp.com)
* [Project repo](https://github.com/Arkodiusz/genderDetector)
* [My portfolio](https://arkodiusz.github.io)
