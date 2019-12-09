## Bien-Ajuste
### Implementing Bien-Ajuste
  This README.md file wrote with class by class explanation.
  Written by Ajou University Domain Analysis and Software Design class Team TEJava 2019
  
  Team member:
  고상원 201820731
  이웅희 201820786
  유준성 201820792
  정민규 201820736

### MainActivity Class

### RegisterActivity Class

### MeasureActivity Class

### GetImageActivity Class

### ImageResizeUtils Class

### User Class

### UserInfo Class

### DB_Handler Class

### Cart Class

### cartActivity Class

### LoginActivity Class

### Shoes Class

### shoesDescriptionActivity Class

### Show3DModelActivity Class
  This class shows shoes' 3D model via user's camera.
  First, there is ModelRenderable variables which is setup for model file as renderable.
  And ArFragment variable make models popup in user's camera. That used for layout layer. 
  Also, we should know what shoes are selected, there is shoesId variable.
  
  At start this activity, it recall saved instance state and use the intent for bring shoesId.
  The boolean variables for describe which shoe is selected are initially false.
  
  We check that device could run this app with checkIsSupportedDeviceOrFinish activity, which is basic activity of androidstudio. After checking, set the content view with this Show3DModelActivity layout. Then, make exitButton which return the state before.
  We also check the shoesId and make each 3D models could appear on user's phone. For example, if the shoesId is 1, set modelOne true, and Builder make model renderable state with setSource, build, accept and exception setups. Then, set avoke the PlaneListener for building 3D model. This app shows the 3D model in real world plane, we used basically offered functions which is find plane and showing the plane with some nodes. If user select one node, then the 3D models appear in node. As the 3D sfb files are very large, we should set scales with getScaleController.
  When user click the exit, user can go to before state
  
  
