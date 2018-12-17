[![Build Status](https://travis-ci.org/L7R7/hexagon-enforcement.svg?branch=master)](https://travis-ci.org/L7R7/hexagon-enforcement)

# Hexagon Enforcement with ArchUnit
In this repository we played around with [ArchUnit](https://www.archunit.org).  More specifically we assessed the capabilities to enforce the ideas of the hexagonal architecture.
A very good and elaborate explanation of these ideas can be found in an excellent article from Herberto Graca [here](https://herbertograca.com/2017/11/16/explicit-architecture-01-ddd-hexagonal-onion-clean-cqrs-how-i-put-it-all-together/).

The **TL;DR** is: Imagining a hexagon or circle or onion with different layers, dependencies of the modules must always point inwards.

Obviously you can go arbitrarily crazy on modules and layers.  In our scenario we have only four layers and want to enforce the following rules:

```
Primary Adapters --> Application --> Domain <-- Secondary Adapters               
```

## Git Tags

We've prepared git tags to exercise the rule enforcement tests without any hassle.

```
git checkout <tagName>
```

We provide the following tags:
* **clean** A small hexagon application which adheres to the architectural rules.
* **broken** The same application with a few violations added

> Please note, that we will likely not support the git tags for long as the repository evolves

## Run the Tests

You can run the tests with Maven:

```
./mvnw test
```

or your favourite IDE of course.

## Results
We explored different ways to enforce the rules.

### Access
The ArchUnit API provides several functions to describe "access".  It seems that one would have to access a method or field of an object.  Simply including a class as a field or so does not cause a violation in this case.  For all intents and purposes we're after this approach does not seem feasible.

### Layered Architecture
ArchUnit provides a way to specify a layered architecture.  However, it only allows to specify how layers may be used, not what layers may use.  In case of a violation the test will fail, but highlight the layer that was incorrectly accessed, not the layer that caused the violation.

### Class Dependencies
There is also a way to specify dependencies and dependents.  This method takes a bit more effort, but provides greater control as it allows to specify how layers may be used, but also which layers they may use.  We prefer this approach for exactly that reason.

### Framework Dependencies
We also attempted to prohibit the domain layer from accessing the spring framework.  Specifying dependencies does not seem to cover annotations.  However, we can specify a `DescribedPredicate<JavaAnnotation>` which does the trick.
