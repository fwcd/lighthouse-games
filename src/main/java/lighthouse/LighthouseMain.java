package lighthouse;

import lighthouse.model.AppModel;
import lighthouse.view.AppFrame;

/**
 * The application's entry point.
 */
public class LighthouseMain {
	public static void main(String[] args) {
		AppModel model = new AppModel();
		AppFrame frame = new AppFrame(model);
		frame.show();
	}
}