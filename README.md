## Bien-Ajuste
### Implementing Bien-Ajuste
  This README.md file wrote with class by class explanation.
  Written by Ajou University Domain Analysis and Software Design class Team TEJava 2019
  
  Team member:
  고상원 201820731
  이웅희 201820786
  유준성 201820792
  정민규 201820736

### MainActivity.java

### RegisterActivity.java

### MeasureActivity.java

### GetImageActivity.java

### ImageResizeUtils.java

### User.java

### UserInfo.java

### DB_Handler.java

### Cart.java

### cartActivity.java

### LoginActivity.java

### Shoes.java

### shoesDescriptionActivity.java

### Show3DModelActivity.java
  This class shows shoes' 3D model via user's camera.
  First, there is ModelRenderable variables which is setup for model file as renderable.
  And ArFragment variable make models popup in user's camera. That used for layout layer. 
  Also, we should know what shoes are selected, there is shoesId variable.
  
  At start this activity, it recall saved instance state and use the intent for bring shoesId.
  The boolean variables for describe which shoe is selected are initially false.
  

### Login.php
  This php file is used to make requests to the server from the LoginActivity class. After receiving the userID and userPassword, the server finds the matching ID & Password in the DB and returns the userId, userPassword, userName, userAge, userEmail, userAddress, userFootsize, userGender, cartID, and response success.
### Makecart.php
  This php file is used to request the server from the RegisterActivity class.
  The server receives a cartID. After that, it stores the data in the DB.
### Register.php
  This php file is used to request the server from the RegisterActivity class.
  The server receives userId, userPassword, userName, userAge, userEmail, userAddress, userFootsize, userGender, cartID. It then stores this information in the DB and returns the response success.
### Updatecart.php
 This php file is used to request the server from the shoe description activity class. The server receives a cartID and a list of shoes. After that, it updates the DB.
### Validate.php
  This php file is used to request the server from the RegisterActivity class.
  The server receives a userID. After that, it checks if there is a duplicate ID and returns the value.
