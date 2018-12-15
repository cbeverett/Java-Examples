import java.util.ListResourceBundle;

// Example Resource Bundle Class

public class example_de extends ListResourceBundle {

	@Override
	protected Object[][] getContents() {
		return new Object[][] {
			{"hello", "Hallo"},
			{"goodbye", "Auf Wiedersehen"}};
	}
}
