package cat_exhibition;

import java.io.BufferedReader;
import java.io.File;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class Cat {
	private String name;
	private String food;
	private String breed;
	private int cats_id;
	private static final String catInfoFileName = "/Users/svetlanamoiseenko/eclipse-workspace/programming/cat_info.txt";
	private static final String ownersInfoFileName = "/Users/svetlanamoiseenko/eclipse-workspace/programming/owners.txt";

	public String getFood() {
		return food;
	}

	public void setFood(String food) {
		this.food = food;
	}

	public String getBreed() {
		return breed;
	}

	public void setBreed(String breed) {
		this.breed = breed;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getCats_id() {
		return cats_id;
	}

	public void setCats_id(int cats_id) {
		this.cats_id = cats_id;
	}

	public void printfood() {
		System.out.println("Cat's food: " + this.getFood());
	}

	public void printbreed() {
		System.out.println("Cat's breed: " + this.getBreed());
	}

	public void printname() {
		System.out.println("Cat's name: " + this.getName());
	}

	public void printcat_id() {
		System.out.println("Cat's id: " + this.getCats_id());
	}

	public void printowner() throws IOException {

		String[] owners = Cat.read(ownersInfoFileName).split("\n");
		for (int i = 0; i < owners.length; i++) {
			if (owners[i].contains(Integer.toString(this.getCats_id()))) {
				System.out.println(owners[i]);
			}
		}

	}

	public void printcatinfo() throws IOException {
		this.printcat_id();
		this.printname();
		this.printbreed();
		this.printfood();
		System.out.println("Cat's owners: ");
		this.printowner();

	}

	public static Set<Cat> buildcatlist() throws IOException {
		Set<Cat> catlist = new HashSet<Cat>();

		String[] data = Cat.read(catInfoFileName).split("\n");

		for (int i = 0; i < data.length; i++) {
			catlist.add(new Cat(data[i]));
		}
		return catlist;
	}

	public static String read(String filename) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader(filename));
		String s;
		StringBuilder sb = new StringBuilder();
		while ((s = in.readLine()) != null)
			sb.append(s + "\n");
		in.close();
		return sb.toString();
	}

	public static void write(Set<Cat> catlist) throws IOException {

		FileWriter writer = new FileWriter((new File("cat_info.txt")));
		for (Cat cat : catlist) {
			writer.write(Integer.toString(cat.getCats_id()) + "|");
			writer.write(cat.getName() + "|");
			writer.write(cat.getFood() + "|");
			writer.write(cat.getBreed() + "\n");
			writer.flush();

		}
		writer.close();

	}

	public static void searchbyid(int id) throws IOException {// search cat by cat // exhibition number
		for (Cat cat : Cat.buildcatlist()) {
			if (cat.getCats_id() == id) {
				cat.printcatinfo();
			}
		}
	}

	public static void searchbyname(String name) throws IOException {// search cat info
																		// by it's name
		for (Cat cat : Cat.buildcatlist()) {
			if (cat.getName().equals(name)) {
				System.out.println("Exhibition number: " + cat.getCats_id());
				cat.printcatinfo();
			}
		}
	}

	public static void searchbyowner(String owner) throws IOException {// search
		String[] owners = Cat.read(ownersInfoFileName).split("\n"); // cat
		// by
		for (int i = 0; i < owners.length; i++) { // it's list of owners
			if (owners[i].contains(owner)) {
				String[] owners2 = owners[i].split(" ");
				Cat.searchbyid(Integer.parseInt(owners2[0]));
			}
		}
	}

	public static void searchbybreed(String breed) throws IOException {// search cat info
																		// by breed
		for (Cat cat : Cat.buildcatlist()) {
			if (cat.getBreed().equals(breed)) {
				System.out.println("Exhibition number: " + cat.getCats_id());
				cat.printcatinfo();
			}
		}
	}

	public static void searchbyfood(String food) throws IOException {// search cat info
																		// by food
		for (Cat cat : Cat.buildcatlist()) {
			if (cat.getFood().equals(food)) {
				System.out.println("Exhibition number: " + cat.getCats_id());
				cat.printcatinfo();
			}
		}
	}

	public static void updateCatName(int id, String newCatName) throws IOException {// update cat name by id

		HashSet<Cat> updateCatList = new HashSet<Cat>();
		updateCatList.addAll(Cat.buildcatlist());
		for (Cat cat : updateCatList) {
			if (cat.getCats_id() == id)
				cat.setName(newCatName);

		}

		Cat.write(updateCatList);

	}

	public static void updateCatFood(int id, String newCatFood)// update
																// cat
																// food
																// by id
			throws IOException {
		HashSet<Cat> updateCatList = new HashSet<Cat>();
		updateCatList.addAll(Cat.buildcatlist());
		for (Cat cat : updateCatList) {
			if (cat.getCats_id() == id)
				cat.setFood(newCatFood);

		}
		Cat.write(updateCatList);

	}

	/*
	 * public static HashMap<Integer, Cat> updateCatId(HashMap<Integer, Cat>
	 * catlist, int old_id, int new_id) throws IOException { HashMap<Integer, Cat>
	 * updateCatList = new HashMap<Integer, Cat>(); if(catlist.containsKey(new_id))
	 * { System.out.
	 * println("Cat with this id has already exist. Please change new id number.");
	 * }else { Cat buffer=null; for (Map.Entry<Integer, Cat> cat :
	 * catlist.entrySet()) { if(cat.getKey()==old_id) buffer=new
	 * Cat(cat.getValue().getName(),cat.getValue().getFood(),cat.getValue().getBreed
	 * (),cat.getValue().getOwners());
	 * 
	 * updateCatList.put(new_id, buffer); } catlist.remove(old_id);
	 * 
	 * updateCatList.putAll(catlist); for (Map.Entry<Integer, Cat> cat :
	 * updateCatList.entrySet()) { System.out.println("Exhibition number: " +
	 * cat.getKey()); cat.getValue().printcatinfo();
	 * writeUsingOutputStream("Exhibition number: " + cat.getKey());
	 * writeUsingOutputStream(cat.getValue().getName());
	 * writeUsingOutputStream(cat.getValue().getBreed());
	 * writeUsingOutputStream(cat.getValue().getFood()); for (Human owner :
	 * cat.getValue().getOwners()) { writeUsingOutputStream(owner.getName());
	 * writeUsingOutputStream(owner.getLast_name());
	 * writeUsingOutputStream(owner.getAddress()); } } //return updateCatList; }
	 * return updateCatList; }
	 */
	public static void updateCatOwnersandAddress(int id, String name) throws IOException {// update cat owners by id(if
																							// cat was bought after the
																							// exhibition)
		// HashSet<Cat> updateCatList = new HashSet<Cat>();
		// updateCatList.addAll(Cat.buildcatlist());
		String[] human_data = Cat.read(ownersInfoFileName).split("\n");
		for (int i = 0; i < human_data.length; i++) {
			// for (Cat cat : updateCatList) {
			if (human_data[i].contains(Integer.toString(id))) {
				String buffer = Cat.read(ownersInfoFileName).replace(human_data[i], "");
				buffer = Integer.toString(id) + " " + name + "\n" + buffer;
				FileWriter writer2 = new FileWriter((new File("owners.txt")));
				writer2.write(buffer);
				writer2.flush();
				writer2.close();

			}
		}
	}
	// }

	/*
	 * public static HashMap<Integer, Cat> updateCatAddress(HashMap<Integer, Cat>
	 * catlist, int id, String newAddress) throws IOException {// update // cat //
	 * address // by // cat's // id HashMap<Integer, Cat> updateCatList = new
	 * HashMap<Integer, Cat>(); for (Map.Entry<Integer, Cat> cat :
	 * catlist.entrySet()) { for (Human owner : cat.getValue().getOwners()) { if
	 * (cat.getKey() == id) owner.setAddress(newAddress); } }
	 * updateCatList.putAll(catlist);
	 * 
	 * return updateCatList; }
	 */

	public static void deletecat(int id) throws IOException {// delete cat from list
		Set<Cat> cats = new HashSet<Cat>();
		cats.addAll(Cat.buildcatlist());
		for (Cat cat : cats) {
			if (cat.getCats_id() == id) {
				cats.remove(cat);
			}
		}
//Cat.write(cats);
//for(Cat cat:cats)
//	cat.printcatinfo();
		String[] human_data = Cat.read(ownersInfoFileName).split("\n");

		for (int i = 0; i < human_data.length; i++) {

			if (human_data[i].contains(Integer.toString(id))) {
				String buffer = Cat.read(ownersInfoFileName).replace(human_data[i], "");

				FileWriter writer2 = new FileWriter((new File("owners.txt")));

				writer2.write(buffer);

				writer2.flush();

				writer2.close();

			}

		}

	}

	public Cat(int cats_id, String name, String food, String breed) {
		this.cats_id = cats_id;
		this.name = name;
		this.food = food;
		this.breed = breed;
	}

	public Cat(String cats_data) throws IOException {
		String[] data = cats_data.split("\\|");
		cats_id = Integer.parseInt(data[0]);
		name = data[1];
		food = data[2];
		breed = data[3];
	}
}
