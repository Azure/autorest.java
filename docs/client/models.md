# <img align="center" src="../images/logo.png">  Accessing Models and Enums

## General

Models and enums are generated in the `models` sub-package.

You access properties of the model through getters, so getting the name of the dog would look like

```java
package com.azure.pets;

import azure.pets.models.Dog;

public static void main(String args[])
{
    String dogName = Dog().getName();
}
```

You set properties of the model through setters, so setting the name of the dog would look like

```java
package com.azure.pets;

import azure.pets.models.Dog;

public static void main(String args[])
{
    Dog dog = new Dog();
    dog.setName("goodBoy");
}
```

Enums are also listed in the `models` sub-package, so say you have enum class `DogTypes`. To access the `DALMATION` enum, your code would look like

```java
package com.azure.pets;

import azure.pets.models.DogTypes;

public static void main(String args[])
{
    DogTypes myDogType = DogTypes.DALMATION;
}
```
