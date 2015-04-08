# Introduction #

How to read a field from an object using Refloker.


# Details #

We are trying to read fields of the following example class :
```
	public class Example {
		private String hiddenField = "hidden field";
		public String visibleField = "visible field";
	}

```

### Reading a public field ###
```
	FieldRead<Example> fieldReadOperation = 
		Reflector.on(example).getField("visibleField");

	Object value = Reflector.executeAndReturnValue(fieldReadOperation);
```

If we add static import for **Reflector., we can reduce the previous code to :
```
	Object value = executeAndReturnValue(on(example).getField("visibleField"));
```**

### Reading a hidden field ###
In Reflector, _"hidden"_ refers to anything which is not _public_

```
	Object value = executeAndReturnValue(on(example).getHiddenField("hiddenField"));

```

### Reading a field defined in a superclass ###
If the field we want to read is defined in a superclass, we have to tell it :

```
	SubclassOfExample subExample = ... 
	Object value =  executeAndReturnValue(
		on(subExample).getField("visibleField").declaredIn(Example.class)
	);

```

IT works the same with a hidden field.