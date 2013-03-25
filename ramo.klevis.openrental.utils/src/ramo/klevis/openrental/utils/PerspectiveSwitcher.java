package ramo.klevis.openrental.utils;

import java.util.List;

import org.eclipse.e4.ui.model.application.ui.advanced.MPerspective;
import org.eclipse.e4.ui.model.application.ui.advanced.MPerspectiveStack;
import org.eclipse.e4.ui.model.application.ui.basic.MTrimmedWindow;
import org.eclipse.e4.ui.model.application.ui.basic.MWindowElement;
import org.eclipse.e4.ui.model.application.ui.menu.MHandledToolItem;
import org.eclipse.e4.ui.workbench.modeling.EPartService;

public class PerspectiveSwitcher {

	public static void switchPerspective(MTrimmedWindow mTrimmedWindow,
			MHandledToolItem mHandledToolItem) {
		List<String> tags = mHandledToolItem.getTags();
		String tag = null;

		if (tags.size() > 0)
			tag = tags.get(0);

		System.err.println(tag);

		if (tag != null)
			switchPerspective(mTrimmedWindow, tag);
	}

	public static void switchPerspective(MTrimmedWindow mTrimmedWindow,
			List<String> tags, EPartService partService) {

		String tag = null;

		
		if (tags.size() > 0)
			tag = tags.get(0);

		System.err.println("Tag check in"+tag);
		if (tag != null)
			switchPerspective(mTrimmedWindow, tag,partService);
	}

	private static void switchPerspective(MTrimmedWindow mTrimmedWindow,
			String tag) {
		List<MWindowElement> children = mTrimmedWindow.getChildren();

		for (MWindowElement mWindowElement : children) {

			if (mWindowElement instanceof MPerspectiveStack) {
				MPerspectiveStack mPerspectiveStack = (MPerspectiveStack) mWindowElement;

				List<MPerspective> children2 = mPerspectiveStack.getChildren();

				System.out.println(children2.size());
				for (MPerspective mPerspective : children2) {

					if (mPerspective.isVisible()) {
						mPerspective.setVisible(false);

					}

					if (mPerspective.getElementId().equals(tag)) {

						mPerspective.setVisible(true);
						mPerspectiveStack.setSelectedElement(mPerspective);

					}
				}

				break;
			}
		}

	}

	private static void switchPerspective(MTrimmedWindow mTrimmedWindow,
			String tag, EPartService partService) {
		List<MWindowElement> children = mTrimmedWindow.getChildren();

		for (MWindowElement mWindowElement : children) {

			if (mWindowElement instanceof MPerspectiveStack) {
				MPerspectiveStack mPerspectiveStack = (MPerspectiveStack) mWindowElement;

				List<MPerspective> children2 = mPerspectiveStack.getChildren();

				System.out.println(children2.size());
				for (MPerspective mPerspective : children2) {

					// if (mPerspective.isVisible()) {
					// mPerspective.setVisible(false);
					//
					// }

					if (mPerspective.getElementId().equals(tag)) {

						// mPerspective.setVisible(true);
						// mPerspectiveStack.setSelectedElement(mPerspective);

						partService.switchPerspective(mPerspective);
					}
				}

				break;
			}
		}

	}

}
