# Release History

## 1.0.0-beta.5 (2022-05-24)

### Features Added

- Support for constructor customizations.
  - Added get and add constructor to class customization.
  - Add or remove annotations from constructor.
  - Get constructor javadoc customization.
  - Replace parameters or body of constructor.
  - Set constructor modifier.
- Support for constant customizations.
  - Added get constant to class customization.
  - Add or remove annotations from constant.
  - Get constant javadoc customization.
  - Rename constant.
  - Set constant modifier.
- Added remove method to class customization.
- Support for adding imports to a class when performing customizations.
- Added ability to customize the class abstract syntax tree to cover scenarios that haven't been developed yet.
- Support for adding a static initializer to a class.

### Bugs Fixed

- Fixes for javadoc parsing.
- Fixes for line indentation.

### Other Changes

- General performance improvements when performing customizations.

## 1.0.0-beta.4 (2021-04-19)

### Features Added

- Added ability to change method parameters in method customization.
- Added listing all classes in package customization.

### Breaking Changes

- Added `Logger` parameter to `Customization` constructor.
- Customizations now return a new instance when chaining instead of returning the modified instance.

### Bugs Fixed

- Fixed a bug where parsing deprecated javadocs checked for sees javadocs.

### Other Changes

- General performance improvements when performing customizations.

## 1.0.0-beta.3 (2021-04-06)

### Features Added

- Added ability to replace method body in method customization.
- Support for setting multiple modifiers to class and method customizations.
- Added getters for javadoc customization properties.
- Added ability to remove since and deprecated in javadoc customization.

### Breaking Changes

- Replaced usage of `Modifier` class with Java's `Modifier` int flag.

### Other Changes

- General performance improvements when performing customizations.

## 1.0.0-beta.2 (2021-01-28)

### Features Added

- Added remove annotation to method customization.
- Added add and remove annotation to property customization.

## 1.0.0-beta.1 (2020-12-18)

Initial release of `azure-autorest-customization`.

### Features Added

- Support for customizing classes.
  - Renaming and changing modifiers of the class.
  - Adding and removing annotations on the class.
  - Renaming enums.
  - Adding and getting methods.
  - Getting properties and javadocs of the class.
- Support for customizing methods.
  - Renaming, changing modifiers, and changing return type of the method.
  - Adding annotations on the method.
  - Getting javadocs of the method.
- Support for customizing properties.
  - Renaming the property.
  - Generating a getter and setter for the property.
- Support for customizing javadocs.
  - Setting description, params, return, throws, see, since and deprecated.
  - Removing param, return, and throws.
