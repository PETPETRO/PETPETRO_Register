Run application in IDE (e.g. Eclipse):

1.Go to main class : PETPETRO_Register\src\register\Main.java
2.Right click with mouse in main class -> Run As -> Java Application
3.Enjoy

Run in Command Line (Windows):

1.Go to PETPETRO_Register
2.Copy register.jar (e.g on Desktop)
3.Run Command line windows+r enter "cmd" press OK
4.Go to folder where you copy register.jar file (e.g. cd C:\Users\user1\Desktop)
5.Enter "java -jar register.jar"
6.Enjoy :)

Note:

If you want to save your register in database you should create database call "register".
Application use url "jdbc:mysql://localhost/register" , soo database should be in mysql. User name
is "root" user password is "root". If you don't want to use mysql database go to "PETPETRO_Register\lib"
and replace "mysql-connector-java-5.1.36-bin.jar" with connector to your database. After that you have to add
this connector into build path. Go to "PETPETRO_Register\src\register\loader\DatabaseRegisterLoader.java" and set 
URL, USER and PASSWORD.
