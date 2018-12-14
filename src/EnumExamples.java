/* 
 * Example program that shows both simple and complex enumerations
 */

public class EnumExamples {

	// Simple Enumeration
	enum Direction {
		NORTH, SOUTH, EAST, WEST
	}
	
	// Enumeration with private constructor, fields, and methods
	enum Planet {
		MERCURY("Rocky"), VENUS("Rocky"), EARTH("Rocky"), MARS("Rocky"), 
			JUPITER("Gassy"), SATURN("Gassy"), URANUS("Gassy"), NEPTUNE("Gassy");
		
		private String composition;
		
		private Planet(String composition) {
			this.composition = composition;
		}
		
		public String getComposition() {
			return composition;
		}
	}
	
	// Enumeration with abstract method
	enum PlanetStats {
		MERCURY {
			public String getStats() {
				return String.format("%s has %d moons and a %f earth-day year.", this.name(), 
						0, 87.96);
			}
		}, 
		VENUS{
			public String getStats() {
				return String.format("%s has %d moons and a %f earth-day year.", this.name(), 
						0, 224.68);
			}			
		}, 
		EARTH{
			public String getStats() {
				return String.format("%s has %d moons and a %f earth-day year.", this.name(), 
						1, 365.26);
			}			
		}, 
		MARS{
			public String getStats() {
				return String.format("%s has %d moons and a %f earth-day year.", this.name(), 
						2, 365.26 * 686.98);
			}			
		}, 
		JUPITER{
			public String getStats() {
				return String.format("%s has %d moons and a %f earth-day year.", this.name(), 
						67, 365.26 * 11.862);
			}			
		}, 
		SATURN{
			public String getStats() {
				return String.format("%s has %d moons and a %f earth-day year.", this.name(), 
						62, 365.26 * 29.456);
			}			
		}, 
		URANUS{
			public String getStats() {
				return String.format("%s has %d moons and a %f earth-day year.", this.name(), 
						27, 365.26 * 84.07);
			}			
		}, 
		NEPTUNE
		{
			public String getStats() {
				return String.format("%s has %d moons and a %f earth-day year.", this.name(), 
						13, 365.26 * 164.81);
			}			
		};
		
		public abstract String getStats();
	}
	
	public static void main(String[] args) {
		exerciseSimpleEnumeration();
		exerciseComplexEnumeration();
	}

	private static void exerciseSimpleEnumeration() {
		Direction dir = Direction.SOUTH;
		System.out.println(dir);
		
		// Parse with .valueOf() - note that this is case sensitive
		dir = Direction.valueOf("NORTH");
		System.out.println(dir);
		
		// Iterate over an enumeration and print its ordinal values
		for(Direction dirIt: Direction.values()) {
			System.out.println(dirIt.name() + " - " + dirIt.ordinal());
		}

		// Note that switch infers the type in case statements
		switch(dir) {
			case NORTH:
				System.out.println("North-bound");
				break;
			case SOUTH:
				System.out.println("South-bound");
				break;
			default:
				System.out.println("You're going the wrong way!");
		}
	}
	
	private static void exerciseComplexEnumeration() {
		Planet home = Planet.EARTH;
		
		System.out.println(String.format("%s is a %s planet", home, home.getComposition()));
		
		PlanetStats saturn = PlanetStats.SATURN;
		System.out.println(saturn.getStats());
	}
}
