Customizations that are important to WicketSource working:

1) WicketApplication.java got an additional line in init(). 
   Suggested that you wrap it in an "if (isDevEnvironment()) { }" for performance.
   
        WicketSource.configure(this);
  
2) pom.xml got the addition of wicketsource:

		<dependency>
			<groupId>com.github.jennybrown8.wicketsource</groupId>
			<artifactId>wicketsource</artifactId>
			<version>7.4.0.1</version>
		</dependency>
		
3) The home page class and html were customized merely to give the user 
   something worthwhile to click on (tags with wicketsource="" attributes).

