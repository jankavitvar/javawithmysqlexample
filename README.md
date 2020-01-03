# Java with Mysql example

You need a Mysql database (ran e.g. locally) with one table *Student* having attributes *name*, *surname* and *age*.  
Insert two students into the table (e.g. Alice Mala 23 and Bob Velky 25)

Create *Config.java* where you set constants specific to your database. Constants should be accessible from both project  packages.
```java
    public final static String serverName = "localhost"; //if ran locally
    public final static String databaseName = "...";
    public final static int port = 3306; //mysql default port
    public final static String username = "...";
    public final static String password = "...";
```

In version 1:  
Run and explore *MyDatabaseDM.java* to see a simple connection using DriverManager.  
Run and explore *MyDatabaseDS.java* to see a simple connection using Datastore.  
Run and explore *MyDatabaseDSInjection.java* to see an example of a SQLInjection security problem and one way to avoid it using PreparedStatement.  

In version 2:  
Run *UI.java* to test a simple object-relational mapping. Explore package classes.
