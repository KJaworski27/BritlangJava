# Britlang - the perfect language for those across the pond
Britlang is an object-oriented programming language based on Lox, created to resemble British slang words, and should be very helpful for British "people" learning to program.
## Documentation
```innit``` - ends a statement. 
```chinwag expression``` - prints an expression to the console.
**Example** - ```chinwag 5 + 8 innit```, ```chinwag "You alright?" innit```
```bigBen()``` - gives the current time in milliseconds.
	
```asString(value)``` - Turns the input into a string.
**Example** - ```asString(5)``` returns ```"5"```,  ```chinwag "My favorite number is " + asString(5) innit``` prints out ```My favorite number is 5``` 

```scrummy``` - value that is true.

```naff``` - value that is false.

```gander (condition) { code } gutted { code 2 }``` - executes ```code``` if ```condition``` is ```scrummy```, executes ```code 2``` if condition is ```naff```.  ```gutted { code 2 } ``` is not necessary.
**Example**-
```
gander (scrummy)
{
	chinwag "this executes" innit
} gutted
{
	chinwag "this does not" innit
}
```


```expression1 ace expression2``` - returns ```scrummy``` if both ```expression1``` and ```expression2``` are both ```scrummy```, otherwise returns ```naff```
**Example** - ```scrummy ace scrummy``` returns ```scrummy```, ```scrummy ace naff``` returns ```naff```.

```expression1 kerfuffle expression2``` - returns ```scrummy``` if either ```expression1``` or ```expresion2``` is ```scrummy```.
**Example** - ```scrummy kerfuffle faff``` returns ```scrummy```, ```faff kerfuffle faff``` returns ```faff```.

```tea name = value``` - assigns a value to a name.
**Example** - 
```
tea value = "This a tea innit?" innit
chinwag value innit
```
prints out ```This a tea innit?```

```codswallop name(arguments) { code }``` -will execute ```code``` when ```name(arguments)``` is called.
**Example** -
```
codswallop function()
{
	chinwag "This is a codswallop with 0 arguments" innit
}

function() innit
```
prints out ```This is a codswallop with 0 arguments```.
```
codswallop add(num1, num2)
{
	chinwag "the sum of these 2 is " + asString(num1 + num2) innit
}

add(5, 6) innit
```
prints out ```the sum of these 2 is 11.0```.

```cuppa name {}``` - creates a ```cuppa``` with selected attributes.

```faff(arguments) { code }``` - runs code inside when a ```cuppa``` is created.
**Example** -
```
cuppa Faffer
{
	faff()
	{
		chinwag "faffing about" innit
	}
}

tea faffer = Faffer() innit
```
prints out ```faffing about```.

```this``` - accesses the cuppa.
```
cuppa IAmACuppa
{
	faff(teaOnCupa)
	{
		this.teaOnCuppa = teaOnCuppa innit
	}
	
	method1()
	{
		chinwag this.teaOnCuppa innit
	}
	method2()
	{
		this.method1() innit
		chinwag "I'm chuffed!" innit
	}
}

tea isACuppa = IAmACuppa("I'm a tea on a cuppa!") innit
isACuppa.method1() innit
isACuppa.method2() innit
```
prints out :
```
I'm a tea on a cuppa!
I'm a tea on a cuppa!
I'm chuffed!
```
```cuppa cuppa2 bobsyouruncle cuppa1 {}``` - creates a ```cuppa``` where ```cuppa2``` inherits everything from ```cuppa1```
```
cuppa Cuppa1
{
	method1()
	{
		chinwag "I have method 1" innit
	}
}

cuppa Cuppa2 bobsyouruncle Cuppa1
{
	method2()
	{
		chinwag "and method 2" innit
	}
}

tea bobsUncle = Cuppa2() innit
bobsUncle.method1() innit
bobsUncle.method2() innit
```
prints out:
```
I have method 1
and method 2
```

```posh``` - when used in a ```cuppa``` declared with ```bobsyouruncle``` will use the version of a method on ```cuppa1```.
**Example**-
```
cuppa Cuppa1
{
	method1()
	{
		chinwag "Cupaa1 method" innit
	}
}

cuppa Cuppa2 bobsyouruncle Cuppa1
{
	method1()
	{
		chinwag "Cuppa2 method" innit
	}
	method2()
	{
		posh.method1() innit
		method1() innit
	}
}

tea t = Cuppa2() innit
t.method2() innit
```
prints out:
```
Cuppa1 method
Cuppa2 method
```
```while (condition) { code }``` - will execute ```code``` while ```condition``` is ```scrummy```
**Example** -
```
tea number = 0 innit
while (number < 5)
{
	chinwag "I'm a while loop mate" innit
	number = number + 1 innit
}
```
prints out:
```
I'm a while loop mate
I'm a while loop mate
I'm a while loop mate
I'm a while loop mate
I'm a while loop mate
```
```for (tea innit condition innit increment) { code }``` - will execute ```code``` while ```condition``` is ```scrummy```.  ```increment``` will run after each iteration.
**Example** -
```
for (tea i = 0 innit i < 5 innit i = i + 1)
{
	chinwag i innit
} 
```
prints out:
```
0
1
2
3
4
``` 
***
# Running Code
Code should be written in a file with the ```.uk``` extension.
Make sure java is installed on your machine.
compile all java files, and run ```Britlang``` with a file that has the ```.uk``` file extension.  Your code should run.

Running ```Britlang``` without arguments will place you in a REPL where you can run code line-by-line.
