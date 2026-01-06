package rajeshsalimath;

import java.util.ArrayList;
import java.util.List;

public class EndToEndTestUserData extends EndToEndTestProducts
{
	//It’s often better to declare the field as List<String> instead of ArrayList<String>
	//
	
	static List<String> values = new ArrayList<String>();
	static
	{
		values.add("Madd"); //0
		values.add("Maxx"); //1
		values.add("madmaxx@hotmail.com"); //2
		values.add("7895645679"); //3
		values.add("Madmaxx@10"); //4
	} //we should initialize in a static block as shown to inherit and access them in another class, not in instance block
	
}

//Why Use the Interface (List)?
//static List<String> values = new ArrayList<String>();
//
//- Flexibility:
//You can easily switch to another List implementation (like LinkedList, CopyOnWriteArrayList, etc.) without changing the rest of your code.
//List<String> values = new LinkedList<>();
//- Only the right-hand side changes, not the rest of your program.

//- Abstraction:
//Code depends on the contract (List methods like add, remove, get) rather than the specific implementation details of ArrayList.

//- Polymorphism:
//Methods can accept List as a parameter, making them work with any kind of list:
//
//public void printList(List<String> list) {
//    for (String s : list) {
//        System.out.println(s);
//    }
//}
//
//- Cleaner API design:
//Libraries and frameworks usually expose interfaces (List, Map, Set) instead of concrete classes, so your code integrates more smoothly.
//
//Rule of Thumb
//- Declare with the interface (List<String> values)
//- Instantiate with the implementation (new ArrayList<>())
//This way, your code is more flexible, maintainable, and future-proof







