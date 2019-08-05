package cat_exhibition;


import java.io.IOException;


public class Check {

	public static void main(String[] args) throws IOException {
Cat.updateCatName(3, "Angie");
Cat.updateCatFood(5, "proplan");
Cat.updateCatOwnersandAddress(4,"Mosina Daria Moscow");
Cat.updateCatFood(1, "kittycat");
Cat.updateCatOwnersandAddress(2, "Abragimov Denis Vladivostok");
Cat.updateCatOwnersandAddress(5, "Borisov Dmitry NN\n5 Borisova Elena NN");
Cat.deletecat(3);
for(Cat cat:Cat.buildcatlist())
	cat.printcatinfo();
		Cat.searchbybreed("siam");
	}
}
