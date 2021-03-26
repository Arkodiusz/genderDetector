# Gender Detector
 
## About The Project
 
It's a simple Java application, that can detect gender by given name. Algorithm compares name with tokens provided in *.txt files. 
Comparison can take place in two variants. 
First takes into account only the first token of provided name, the second chcecks all tokens and majority rule is used.
 
 
### Built With
 
* Java
* Gradle
* Spring
 
 
 
  
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
GET /api/gender/tokens/{gender}
 
Returns list of all avaiable tokens of chosen gender.
 
##### PATH PARAMETER
String gender
Specifying which token should be listed. 
Avaiable values are "male" or "female".
 
 
 
## Information
* Avaiable tokens lists are provided as *.txt files. Encoding is set to UTF-8, and this charset should be set in your IDE when working with code.
* Token lists do not contains names with Polish characters
 
 
 
## Contact
 
Arkadiusz Jędrzejewski - arkadiusz.jedrzej@gmail.com
 
Project Link: [https://github.com/Arkodiusz/genderDetector](https://github.com/Arkodiusz/genderDetector)
