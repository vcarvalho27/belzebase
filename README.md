![alt tag](https://github.com/vcarvalho27/BelzeBase/blob/master/belzebase.png)


Usage
-----


**1. Gradle dependency** (recommended)

  -  Add the following to your project level `build.gradle`:
 
```gradle
allprojects {
	repositories {
		maven { url "https://jitpack.io" }
	}
}
```
  -  Add this to your app `build.gradle`:
 
```gradle
dependencies {
	compile 'com.github.vcarvalho27:BelzeBase:0.1.1'
}
```

**2. Maven**
- Add the following to the `<repositories>` section of your `pom.xml`:

 ```xml
<repository>
        <id>jitpack.io</id>
        <url>https://jitpack.io</url>
</repository>
```
- Add the following to the `<dependencies>` section of your `pom.xml`:

 ```xml
<dependency>
        <groupId>com.github.vcarvalho27</groupId>
	<artifactId>BelzeBase</artifactId>
	<version>0.1.1</version>
</dependency>
```
