package ramo.klevis.openrental.utils;

import java.util.List;

import org.eclipse.e4.ui.model.application.ui.MUIElement;
import org.eclipse.e4.ui.model.application.ui.advanced.MPerspective;
import org.eclipse.e4.ui.model.application.ui.advanced.MPerspectiveStack;
import org.eclipse.e4.ui.model.application.ui.basic.MTrimmedWindow;
import org.eclipse.e4.ui.model.application.ui.basic.MWindow;
import org.eclipse.e4.ui.model.application.ui.basic.MWindowElement;
import org.eclipse.e4.ui.model.application.ui.menu.MHandledToolItem;
import org.eclipse.e4.ui.workbench.modeling.EModelService;
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

		System.err.println("Tag check in" + tag);
		if (tag != null)
			switchPerspective(mTrimmedWindow, tag, partService);
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

	public static void disposeWindowWithId(EModelService modelService,
			MPerspective mPerspective, String id) {
		MUIElement find = modelService.find(id, mPerspective);

		find.setVisible(false);

	}

	public static void disposeWindowWithId(EModelService modelService,
			MUIElement root, String id) {
		MUIElement find = modelService.find(id, root);

		find.setVisible(false);

	}

	public static void showWindowWithId(EModelService modelService,
			MPerspective mPerspective, String id) {

		MUIElement find = modelService.find(id, mPerspective);

		if (find.isToBeRendered() == false) {
			find.setToBeRendered(true);
		}

		find.setVisible(true);
		
		if (find instanceof MWindow) {

			MWindow mWindow = (MWindow) find;
			List<MWindowElement> children = mWindow.getChildren();
			for (MWindowElement mWindowElement : children) {

				if (mWindowElement.isToBeRendered() == false) {
					mWindowElement.setToBeRendered(true);
					
				}
				
				mWindowElement.setVisible(true);
			}

		}
	}

	public static void showWindowWithId(EModelService modelService,
			MUIElement root, String id) {

		MUIElement find = modelService.find(id, root);

		if (find.isToBeRendered() == false) {
			find.setToBeRendered(true);
		}

		find.setVisible(true);

		if (find instanceof MWindow) {

			MWindow mWindow = (MWindow) find;
			List<MWindowElement> children = mWindow.getChildren();
			for (MWindowElement mWindowElement : children) {

				if (mWindowElement.isToBeRendered() == false) {
					mWindowElement.setToBeRendered(true);
				
				}
				mWindowElement.setVisible(true);
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

					if (mPerspective.getElementId().equals(tag)) {

						partService.switchPerspective(mPerspective);

					}
				}

				break;
			}
		}

	}

}
