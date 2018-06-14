package week6;

public class Boom {

	public static void main(String[] args) {
		
		BKnoop<String> aap = new BKnoop<String>("aap");
		BKnoop<String> noot = new BKnoop<String>("noot");
		BKnoop<String> mies = new BKnoop<String>("mies");

		aap.add(noot);
		aap.add(mies);
		noot.add(new BKnoop<String>("Wim"));
		noot.add(new BKnoop<String>("Jet"));
		mies.add(new BKnoop<String>("Roos"));
//		mies.add(new BKnoop<String>("Fred"));

		System.out.print("PRE : ");
		aap.printPreOrder(aap);
		System.out.print("\nPOST: ");
		aap.printPostOrder(aap);
		System.out.print("\nIN  : ");
		aap.printInOrder(aap);

		System.out.println("\n Aantal knopen : "+aap.aantalKnopen());
		System.out.println("\n Aantal leafs : "+aap.aantalLeafs());

		System.out.println("\n Diepte : "+aap.diepte());
		System.out.println("\n Hoogte : "+aap.hoogte());

	}



}
