## Presentation ##
Refloker provides a fluent API do execute methods and read / write fields through reflection.

It tries to help to write expressive reflection invocations without cluttering your code with exception handling.

Refloker requires **java 1.5** or more.

Refloker is still in alpha stage, _API may be subject to change_.

## Usage crash course ##
Set a hidden field of an object

```
// the object which field we want to set
TargetClass target = ...

FieldSet<TargetClass> fieldSet = Reflector.on(target).setHiddenField("fieldToSet").to(newValue));

Reflector.execute(fieldSet);
```

You can achieve even more expressiveness with a static import :
```
import static fr.armida.refloker.Reflector.*;
...

execute(on(target).setHiddenField("fieldToSet").to(newValue));
```

More information on how to use Refloker is (well, will be) available through [Wiki pages marked as Usage](http://code.google.com/p/refloker/w/list?q=label:Usage) or in the form of unit tests in the [source repository](http://refloker.googlecode.com/svn/trunk/src/test/java/fr/armida/refloker/example/)

## Maven repository ##
You can use Refloker in your maven project by adding the dependency :
```
<dependency>
	<groupId>armida</groupId>
	<artifactId>refloker</artifactId>
	<version>0.6</version>
</dependency>
```

and the Refloker repository :
```
<repositories>
	<repository>
		<id>refloker-repo</id>
		<name>Refloker m2 releases repository</name>
		<url>http://refloker.googlecode.com/svn/m2-repo/release</url>
	</repository>
</repositories>
```